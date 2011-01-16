package eu.tanov.epf.pv.ocl.provider;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Collection;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.validation.model.Category;
import org.eclipse.emf.validation.model.CategoryManager;
import org.eclipse.emf.validation.model.IModelConstraint;
import org.eclipse.emf.validation.service.AbstractConstraintProvider;
import org.eclipse.emf.validation.service.ConstraintExistsException;
import org.eclipse.epf.validation.LibraryEValidator;
import org.eclipse.ocl.OCLInput;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.ecore.Constraint;
import org.eclipse.ocl.ecore.OCL;
import org.eclipse.ocl.utilities.UMLReflection;
import org.osgi.framework.Bundle;

import eu.tanov.epf.pv.ocl.Activator;

/**
 * Based on org.eclipse.emf.validation.examples.ocl example
 */
public class OCLConstraintProvider extends AbstractConstraintProvider {
	private static final String E_OCL = "ocl"; //$NON-NLS-1$
	private static final String A_PATH = "path"; //$NON-NLS-1$
	private static final String A_CATEGORY = "category"; //$NON-NLS-1$

	private static final Category defaultCateogry = CategoryManager.getInstance().getCategory(
			LibraryEValidator.CONSTRAINT_CATEGORY);

	@Override
	public void setInitializationData(IConfigurationElement config, String propertyName, Object data) throws CoreException {
		super.setInitializationData(config, propertyName, data);

		// create the constraint category
		String categoryID = config.getDeclaringExtension().getUniqueIdentifier();
		if (categoryID == null) {
			categoryID = "OCLProvider@" + Long.toHexString(System.identityHashCode(this)); //$NON-NLS-1$
		}

		categoryID = "OCL/" + categoryID; //$NON-NLS-1$

		final Category category = CategoryManager.getInstance().getCategory(categoryID);
		category.setName(config.getAttribute(A_CATEGORY));

		final Bundle contributor = Platform.getBundle(config.getDeclaringExtension().getNamespaceIdentifier());

		final IConfigurationElement[] ocls = config.getChildren(E_OCL);
		for (int i = 0; i < ocls.length; i++) {
			final String path = ocls[i].getAttribute(A_PATH);

			if ((path != null) && (path.length() > 0)) {
				// categorize by OCL document name
				IPath ipath = new Path(path);
				parseConstraints(CategoryManager.getInstance().getCategory(category, ipath.lastSegment()), contributor, path);
			}
		}

		try {
			registerConstraints(getConstraints());
		} catch (ConstraintExistsException e) {
			// TODO i18n
			throw new CoreException(
					new Status(IStatus.ERROR, Activator.PLUGIN_ID, 1, "Registration of OCL constraints failed", e));
		}
	}

	private void parseConstraints(Category category, Bundle bundle, String path) {
		final URL url = bundle.getEntry(path);

		if (url != null) {
			try {
				final InputStream input = url.openStream();

				try {
					parseConstraints(category, bundle.getSymbolicName(), input);
				} catch (ParserException e) {
					// TODO i18n
					final String msg = String.format("Failed to parse OCL constraints in %s:%s", bundle.getSymbolicName(), path);
					Activator.log(msg, e);
				} finally {
					input.close();
				}
			} catch (IOException e) {
				// TODO i18n
				final String msg = String.format("Failed to load OCL constraints from %s:%s", bundle.getSymbolicName(), path);
				Activator.log(msg, e);
			}
		}
	}

	private void parseConstraints(Category category, String namespace, InputStream input) throws ParserException {

		final OCLInput oclInput = new OCLInput(input);
		final OCL ocl = OCL.newInstance();

		for (Constraint constraint : ocl.parse(oclInput)) {
			if (isInvariant(constraint)) {
				// only add invariant constraints for validation
				addConstraint(category, namespace, ocl, constraint);
			}
		}
	}

	private boolean isInvariant(Constraint constraint) {
		return UMLReflection.INVARIANT.equals(constraint.getStereotype());
	}

	private void addConstraint(Category category, String namespace, OCL ocl, Constraint constraint) {
		final Collection<IModelConstraint> constraints = getConstraints();

		final OCLConstraintDescriptor desc = new OCLConstraintDescriptor(namespace, constraint, constraints.size() + 1);
		if (category != null) {
			category.addConstraint(desc);
		}

		defaultCateogry.addConstraint(desc);

		constraints.add(new OCLConstraint(desc, desc.getConstraint(), ocl));
	}

}
