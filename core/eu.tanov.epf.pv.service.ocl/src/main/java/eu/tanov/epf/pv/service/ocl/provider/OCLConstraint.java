package eu.tanov.epf.pv.service.ocl.provider;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.ocl.AbstractOCLModelConstraint;
import org.eclipse.emf.validation.service.IConstraintDescriptor;
import org.eclipse.ocl.EnvironmentFactory;
import org.eclipse.ocl.Query;
import org.eclipse.ocl.ecore.Constraint;
import org.eclipse.ocl.ecore.OCL;

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
}
