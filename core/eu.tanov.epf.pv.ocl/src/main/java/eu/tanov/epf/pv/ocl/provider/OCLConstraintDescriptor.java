package eu.tanov.epf.pv.ocl.provider;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.model.ConstraintSeverity;
import org.eclipse.emf.validation.model.EvaluationMode;
import org.eclipse.emf.validation.service.AbstractConstraintDescriptor;
import org.eclipse.ocl.ecore.Constraint;

import eu.tanov.epf.pv.ocl.CustomTypeHandlersRegistry;

/**
 * Based on org.eclipse.emf.validation.examples.ocl example
 */
public class OCLConstraintDescriptor extends AbstractConstraintDescriptor {
	private final Constraint constraint;
	private final String id;
	private final String name;
	private final String namespace;
	private final int code;

	public OCLConstraintDescriptor(String namespace, Constraint constraint, int code) {
		this.constraint = constraint;

		String name = constraint.getName();
		if (name == null) {
			throw new IllegalArgumentException("Constraint without name: " + constraint);
		}

		id = namespace + '.' + name;
		this.name = name;
		this.namespace = namespace;
		this.code = code;
	}

	final Constraint getConstraint() {
		return constraint;
	}

	public String getBody() {
		return constraint.getSpecification().getBodyExpression().toString();
	}

	public String getDescription() {
		return getBody();
	}

	public EvaluationMode<?> getEvaluationMode() {
		return EvaluationMode.BATCH;
	}

	public String getId() {
		return id;
	}

	public String getMessagePattern() {
		// TODO i18n
		return String.format("Constraint %s violated on {0}", getName()); //$NON-NLS-1$
	}

	public String getName() {
		return name;
	}

	public String getPluginId() {
		return namespace;
	}

	public ConstraintSeverity getSeverity() {
		return ConstraintSeverity.WARNING;
	}

	public int getStatusCode() {
		return code;
	}

	public boolean targetsEvent(Notification notification) {
		return false;
	}

	public boolean targetsTypeOf(EObject eObject) {
		final EClassifier type = constraint.getSpecification().getContextVariable().getType();
		if (type.isInstance(eObject)) {
			// direct match
			return true;
		}

		return CustomTypeHandlersRegistry.getInstance().canWrapTo(eObject, type);
	}

}
