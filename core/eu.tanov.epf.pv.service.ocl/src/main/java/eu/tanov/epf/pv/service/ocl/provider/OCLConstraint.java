package eu.tanov.epf.pv.service.ocl.provider;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.ocl.AbstractOCLModelConstraint;
import org.eclipse.emf.validation.service.IConstraintDescriptor;
import org.eclipse.ocl.EnvironmentFactory;
import org.eclipse.ocl.Query;
import org.eclipse.ocl.ecore.Constraint;
import org.eclipse.ocl.ecore.OCL;

import eu.tanov.epf.pv.service.ocl.OCLActivator;
import eu.tanov.epf.pv.service.types.service.CustomTypeHandlersService;

/**
 * Based on org.eclipse.emf.validation.examples.ocl example
 */
public class OCLConstraint extends AbstractOCLModelConstraint<EClassifier, Constraint, EClass, EObject> {
	private final OCL.Query query;

	public OCLConstraint(IConstraintDescriptor desc, Constraint constraint, OCL ocl) {
		super(desc);

		this.query = ocl.createQuery(constraint);
	}

	@Override
	protected EnvironmentFactory<?, EClassifier, ?, ?, ?, ?, ?, ?, ?, Constraint, EClass, EObject> createOCLEnvironmentFactory() {
		return query.getOCL().getEnvironment().getFactory();
	}

	@Override
	public Query<EClassifier, EClass, EObject> getConstraintCondition(EObject target) {
		return query;
	}

	/**
	 * Copied from super.validate() but with custom error message, because TextUtils.formatMessage() is used in default
	 * implementation which does not respect custom types and wrong label of object is returned
	 * 
	 * @param ctx
	 * @return
	 */
	@Override
	public IStatus validate(IValidationContext ctx) {

		EObject target = ctx.getTarget();

		if (query.check(target)) {
			return ctx.createSuccessStatus();
		} else {
			// OCL constraints only support the target object as an extraction
			// variable and result locus, as OCL has no way to provide
			// additional extractions. Also, there is no way for the OCL
			// to access the context object
			final CustomTypeHandlersService service = OCLActivator.getDefault().getService(CustomTypeHandlersService.class);

			return ctx.createFailureStatus(service.wrapObjectIfNeeded(target));
		}
	}

}
