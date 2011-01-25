package eu.tanov.epf.pv.service.ocl.provider;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.model.ConstraintSeverity;
import org.eclipse.emf.validation.model.EvaluationMode;
import org.eclipse.emf.validation.service.AbstractConstraintDescriptor;
import org.eclipse.ocl.ecore.Constraint;

import eu.tanov.epf.pv.service.ocl.OCLActivator;
import eu.tanov.epf.pv.service.ocl.extension.OCLConstraintsDefinition;
import eu.tanov.epf.pv.service.types.service.CustomTypeHandlersService;

/**
 * Based on org.eclipse.emf.validation.examples.ocl example
 */
public class OCLConstraintDescriptor extends AbstractConstraintDescriptor {
	private final OCLConstraintsDefinition definition;
	private final Constraint constraint;
	private final String id;
	private final String name;
	private final int code;

	public OCLConstraintDescriptor(OCLConstraintsDefinition definition, Constraint constraint, int code) {
		this.definition = definition;
		this.constraint = constraint;

		if (constraint.getName() == null) {
			// auto generated name from constraint content
			this.name = constraint.toString();
		} else {
			this.name = constraint.getName();
		}

		id = this.definition.getId() + '.' + name;
		this.code = code;
	}

	@Override
	public String getBody() {
		return constraint.getSpecification().getBodyExpression().toString();
	}

	@Override
	public String getDescription() {
		return getBody();
	}

	@Override
	public EvaluationMode<?> getEvaluationMode() {
		return EvaluationMode.BATCH;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getMessagePattern() {
		return String.format(definition.getMessage(), getName());
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getPluginId() {
		return definition.getPluginId();
	}

	@Override
	public ConstraintSeverity getSeverity() {
		return definition.getSeverity();
	}

	@Override
	public int getStatusCode() {
		return code;
	}

	@Override
	public boolean targetsEvent(Notification notification) {
		return false;
	}

	@Override
	public boolean targetsTypeOf(EObject eObject) {
		final EClassifier type = constraint.getSpecification().getContextVariable().getType();
		if (type.isInstance(eObject)) {
			// direct match
			return true;
		}

		final CustomTypeHandlersService service = OCLActivator.getDefault().getService(CustomTypeHandlersService.class);
		return service.canWrapTo(eObject, type);
	}

}
