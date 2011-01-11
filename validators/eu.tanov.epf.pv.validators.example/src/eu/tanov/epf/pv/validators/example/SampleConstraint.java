package eu.tanov.epf.pv.validators.example;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;

public class SampleConstraint extends AbstractModelConstraint {

	@Override
	public IStatus validate(IValidationContext ctx) {
		final EObject target = ctx.getTarget();
		if (!(target instanceof org.eclipse.epf.uma.ProcessComponent)) {
			throw new IllegalStateException("SampleConstraint expects only ProcessComponent, not: " + target);
		}
		final String name = ((org.eclipse.epf.uma.ProcessComponent) target).getName();
		if ("goodName".equals(name)) {
			return ctx.createSuccessStatus();
		} else {
			return ctx.createFailureStatus(name);
		}
	}

}
