package eu.tanov.epf.pv.service.ocl.parser;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.ocl.EvaluationEnvironment;
import org.eclipse.ocl.ecore.EcoreEvaluationEnvironment;

import eu.tanov.epf.pv.service.ocl.OCLActivator;
import eu.tanov.epf.pv.service.types.service.CustomTypeHandlersService;

public class ExtendedEcoreEvaluationEnvironment extends EcoreEvaluationEnvironment {

	public ExtendedEcoreEvaluationEnvironment(
			EvaluationEnvironment<EClassifier, EOperation, EStructuralFeature, EClass, EObject> parent) {
		super(parent);
	}

	public ExtendedEcoreEvaluationEnvironment() {
		super();
	}

	@Override
	public Object navigateProperty(EStructuralFeature property, List<?> qualifiers, Object target)
			throws IllegalArgumentException {
		if (target instanceof EObject) {
			// wrap first
			final CustomTypeHandlersService service = OCLActivator.getDefault().getService(CustomTypeHandlersService.class);
			target = service.wrapObjectIfNeeded((EObject) target);
		}
		return super.navigateProperty(property, qualifiers, target);
		// XXX or first try super.navigateProperty() and catch exception - if exception - try wrapping?
	}
}
