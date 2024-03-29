package eu.tanov.epf.pv.service.ocl.provider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Set;

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
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.ecore.Constraint;
import org.eclipse.ocl.ecore.OCL;

import eu.tanov.epf.pv.service.ocl.OCLActivator;
import eu.tanov.epf.pv.service.ocl.extension.OCLConstraintsDefinition;
import eu.tanov.epf.pv.service.ocl.parser.OCLParser;
import eu.tanov.epf.pv.service.ocl.service.OCLConstraintsService;
import eu.tanov.epf.pv.service.ocl.service.OCLConstraintsServiceListener;
import eu.tanov.epf.pv.service.types.service.CustomTypeHandlersService;

/**
 * Based on org.eclipse.emf.validation.examples.ocl example
 */
public class OCLConstraintProvider extends AbstractConstraintProvider implements OCLConstraintsServiceListener {
	private static final String EXTENDSION_POINT_NAME_OCL_CONSTRAINTS = "OCLConstraints";

	private static final Category defaultCateogry = CategoryManager.getInstance().getCategory(
			LibraryEValidator.CONSTRAINT_CATEGORY);

	private final IdentityHashMap<OCLConstraintsDefinition, List<OCLConstraint>> definitionToConstraintsMap = new IdentityHashMap<OCLConstraintsDefinition, List<OCLConstraint>>();

	private final OCLParser parser;

	public OCLConstraintProvider() {
		// load types service
		OCLActivator.getDefault().getService(CustomTypeHandlersService.class);

		this.parser = createParser();
	}

	protected OCLParser createParser() {
		return new OCLParser();
	}

	@Override
	public void setInitializationData(IConfigurationElement config, String propertyName, Object data) throws CoreException {
		super.setInitializationData(config, propertyName, data);

		final OCLConstraintsService service = OCLActivator.getDefault().getService(OCLConstraintsService.class);

		service.addListener(this);
		final Collection<OCLConstraintsDefinition> acumulatedDefinitions = service.getConstraintsDefinitions();

		registerAcumulatedDefinitions(acumulatedDefinitions);

		processOCLConstraintsExtensions();

		try {
			registerConstraints(getConstraints());
		} catch (ConstraintExistsException e) {
			// TODO i18n
			throw new CoreException(new Status(IStatus.ERROR, OCLActivator.PLUGIN_ID, 1,
					"Registration of OCL constraints failed", e));
		}
	}

	private void registerAcumulatedDefinitions(Collection<OCLConstraintsDefinition> definitionsBeforeProvider)
			throws CoreException {
		for (OCLConstraintsDefinition definition : definitionsBeforeProvider) {
			if (definitionToConstraintsMap.containsKey(definition)) {
				// already registered
				continue;
			}

			constraintsDefinitionRegistered(definition);
		}
	}

	public void constraintsDefinitionRegistered(OCLConstraintsDefinition definition) {
		try {
			addConstraintsDefinition(definition);
			registerConstraints(definitionToConstraintsMap.get(definition));
		} catch (Exception e) {
			OCLActivator.log("could not add definition: " + definition, e);
		}

	}

	public void constraintsDefinitionRemoved(OCLConstraintsDefinition definition) {
		final List<OCLConstraint> constraintsToRemove = definitionToConstraintsMap.get(definition);
		final List<IModelConstraint> allConstraints = getConstraints();

		for (OCLConstraint constraintToRemove : constraintsToRemove) {
			allConstraints.remove(constraintToRemove);
			ConstraintRegistry.getInstance().unregister(constraintToRemove.getDescriptor());

			// remove from category
			final Set<Category> categories = constraintToRemove.getDescriptor().getCategories();
			// new ArrayList - prevent concurrent modification exception
			for (Category category : new ArrayList<Category>(categories)) {
				category.removeConstraint(constraintToRemove.getDescriptor());
			}
		}
	}

	private void processOCLConstraintsExtensions() {
		final IExtensionRegistry extensionRegistry = Platform.getExtensionRegistry();
		final IExtensionPoint extensionPoint = extensionRegistry.getExtensionPoint(OCLActivator.PLUGIN_ID,
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
				final String content = getContent(configuration);
				final String message = configuration.getAttribute("message"); //$NON-NLS-1$

				final OCLConstraintsDefinition definition = new OCLConstraintsDefinition(pluginId, id, category, mandatory,
						severity, content, message);

				addConstraintsDefinition(definition);
			} catch (Exception e) {
				OCLActivator.log("could not parse configuration: " + configuration, e);
			}
		}
	}

	private String getContent(IConfigurationElement configuration) {
		final IConfigurationElement[] children = configuration.getChildren("content"); //$NON-NLS-1$
		if (children == null || children.length != 1) {
			throw new IllegalArgumentException("could not find content in: " + configuration);
		}
		return children[0].getValue();
	}

	/**
	 * Only parse and add to list, does NOT call registerConstraints()
	 */
	private void addConstraintsDefinition(OCLConstraintsDefinition definition) throws ParserException, IllegalArgumentException {
		if (definitionToConstraintsMap.containsKey(definition)) {
			throw new IllegalArgumentException("Definition already added: " + definition);
		}
		final List<Constraint> invariantConstraints = parser.parseInvariants(definition.getContent());

		definitionToConstraintsMap.put(definition, new ArrayList<OCLConstraint>(invariantConstraints.size()));
		for (Constraint constraint : invariantConstraints) {
			addConstraint(definition, parser.getOCL(), constraint);
		}
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
