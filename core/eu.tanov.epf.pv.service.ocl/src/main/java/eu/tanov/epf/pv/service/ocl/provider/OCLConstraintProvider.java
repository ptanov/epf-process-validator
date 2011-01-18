package eu.tanov.epf.pv.service.ocl.provider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.validation.model.Category;
import org.eclipse.emf.validation.model.CategoryManager;
import org.eclipse.emf.validation.model.ConstraintSeverity;
import org.eclipse.emf.validation.model.IModelConstraint;
import org.eclipse.emf.validation.service.AbstractConstraintProvider;
import org.eclipse.emf.validation.service.ConstraintExistsException;
import org.eclipse.emf.validation.service.ConstraintRegistry;
import org.eclipse.epf.validation.LibraryEValidator;
import org.eclipse.ocl.OCLInput;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.ecore.Constraint;
import org.eclipse.ocl.ecore.OCL;
import org.eclipse.ocl.utilities.UMLReflection;

import eu.tanov.epf.pv.service.ocl.Activator;
import eu.tanov.epf.pv.service.ocl.extension.OCLConstraintsDefinition;
import eu.tanov.epf.pv.service.ocl.factory.ExtendedEcoreEnvironmentFactory;

/**
 * Based on org.eclipse.emf.validation.examples.ocl example
 */
public class OCLConstraintProvider extends AbstractConstraintProvider {
	private static final String EXTENDSION_POINT_NAME_OCL_CONSTRAINTS = "OCLConstraints";

	private static final Category defaultCateogry = CategoryManager.getInstance().getCategory(
			LibraryEValidator.CONSTRAINT_CATEGORY);

	private final IdentityHashMap<OCLConstraintsDefinition, List<OCLConstraint>> definitionToConstraintsMap = new IdentityHashMap<OCLConstraintsDefinition, List<OCLConstraint>>();

	@Override
	public void setInitializationData(IConfigurationElement config, String propertyName, Object data) throws CoreException {
		super.setInitializationData(config, propertyName, data);

		// XXX this is not good, but how to accumulate definitions before provider is created?
		final Collection<OCLConstraintsDefinition> acumulatedDefinitions = OCLConstraintRegistry.getInstance().setProvider(this);
		registerAcumulatedDefinitions(acumulatedDefinitions);

		processOCLConstraintsExtensions();

		try {
			registerConstraints(getConstraints());
		} catch (ConstraintExistsException e) {
			// TODO i18n
			throw new CoreException(
					new Status(IStatus.ERROR, Activator.PLUGIN_ID, 1, "Registration of OCL constraints failed", e));
		}
	}

	private void registerAcumulatedDefinitions(Collection<OCLConstraintsDefinition> definitionsBeforeProvider)
			throws CoreException {
		// XXX if exception - it will be not handled properly :(
		for (OCLConstraintsDefinition definition : definitionsBeforeProvider) {
			try {
				registerConstraintsDefinition(definition);
			} catch (Exception e) {
				throw new CoreException(new Status(IStatus.ERROR, Activator.PLUGIN_ID, 1,
						"Registration of acumulated OCL constraints failed", e));

			}

		}
	}

	/**
	 * Should be used only by OCLConstraintRegistry or from this class
	 * 
	 * @param definition
	 * @throws ParserException
	 *             on failure to parse, either because of a syntactic or semantic problem or because of an I/O failure
	 * @throws ConstraintExistsException
	 *             in case any of the constraints has an ID that is already registered for a different constraint
	 */
	public void registerConstraintsDefinition(OCLConstraintsDefinition definition) throws ParserException,
			ConstraintExistsException {
		addConstraintsDefinition(definition);

		registerConstraints(definitionToConstraintsMap.get(definition));
	}

	/**
	 * Should be used only by OCLConstraintRegistry
	 * 
	 * @param definition
	 */
	public void removeConstraintsDefinition(OCLConstraintsDefinition definition) {
		final List<OCLConstraint> constraints = definitionToConstraintsMap.get(definition);

		for (OCLConstraint oclConstraint : constraints) {
			ConstraintRegistry.getInstance().unregister(oclConstraint.getDescriptor());
		}
	}

	private void processOCLConstraintsExtensions() {
		final IExtensionRegistry extensionRegistry = Platform.getExtensionRegistry();
		final IExtensionPoint extensionPoint = extensionRegistry.getExtensionPoint(Activator.PLUGIN_ID,
				EXTENDSION_POINT_NAME_OCL_CONSTRAINTS);

		for (IExtension extension : extensionPoint.getExtensions()) {
			processOCLConstraintsExtension(extension);
		}
	}

	private void processOCLConstraintsExtension(IExtension extension) {
		final String pluginId = extension.getNamespaceIdentifier();
		final IConfigurationElement[] configElements = extension.getConfigurationElements();

		for (IConfigurationElement configuration : configElements) {
			try {
				final String id = configuration.getAttribute("id"); //$NON-NLS-1$
				final String category = configuration.getAttribute("category"); //$NON-NLS-1$
				final boolean mandatory = Boolean.getBoolean(configuration.getAttribute("mandatory")); //$NON-NLS-1$
				final ConstraintSeverity severity = ConstraintSeverity.valueOf(configuration.getAttribute("severity")); //$NON-NLS-1$
				final String content = configuration.getAttribute("content"); //$NON-NLS-1$
				final String message = configuration.getAttribute("message"); //$NON-NLS-1$

				final OCLConstraintsDefinition definition = new OCLConstraintsDefinition(pluginId, id, category, mandatory,
						severity, content, message);

				addConstraintsDefinition(definition);
			} catch (Exception e) {
				Activator.log("could not parse configuration: " + configuration, e);
			}
		}
	}

	/**
	 * Only parse and add to list, does NOT call registerConstraints()
	 */
	private void addConstraintsDefinition(OCLConstraintsDefinition definition) throws ParserException {
		final OCLInput oclInput = new OCLInput(definition.getContent());
		final OCL ocl = createOCL();

		if (definitionToConstraintsMap.containsKey(definition)) {
			throw new IllegalArgumentException("Definition already added: " + definition);
		}

		final List<Constraint> constraints = ocl.parse(oclInput);

		definitionToConstraintsMap.put(definition, new ArrayList<OCLConstraint>(constraints.size()));
		for (Constraint constraint : constraints) {
			if (isInvariant(constraint)) {
				// only add invariant constraints for validation
				addConstraint(definition, ocl, constraint);
			}
		}
	}

	private static OCL createOCL() {
		return OCL.newInstance(new ExtendedEcoreEnvironmentFactory());
	}

	private static boolean isInvariant(Constraint constraint) {
		return UMLReflection.INVARIANT.equals(constraint.getStereotype());
	}

	private void addConstraint(OCLConstraintsDefinition definition, OCL ocl, Constraint constraint) {
		final Collection<IModelConstraint> constraints = getConstraints();

		final OCLConstraintDescriptor descriptor = new OCLConstraintDescriptor(definition, constraint, constraints.size() + 1);

		CategoryManager.getInstance().getCategory(definition.getCategory()).addConstraint(descriptor);
		defaultCateogry.addConstraint(descriptor);

		final OCLConstraint result = new OCLConstraint(descriptor, constraint, ocl);
		constraints.add(result);
		definitionToConstraintsMap.get(definition).add(result);
	}

}
