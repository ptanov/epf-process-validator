package eu.tanov.epf.pv.ocl.validation;

import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.epf.validation.LibraryEValidator;

import eu.tanov.epf.pv.ocl.extension.CustomTypeHandler;

public class ExtendedLibraryEValidator extends LibraryEValidator {

	private final List<CustomTypeHandler> allCustomTypeHandlers;

	public ExtendedLibraryEValidator(List<CustomTypeHandler> allCustomTypeHandlers) {
		this.allCustomTypeHandlers = allCustomTypeHandlers;
	}

	@Override
	public boolean validate(EClass eClass, EObject eObject, DiagnosticChain diagnostics, @SuppressWarnings("rawtypes") Map context) {
		eObject = wrapObjectIfNeeded(eObject);
		return super.validate(eClass, eObject, diagnostics, context);
	}

	/**
	 * @param eObject
	 * @return same eObject if it is not holder for custom type
	 */
	private EObject wrapObjectIfNeeded(EObject eObject) {
		final CustomTypeHandler handler = getHandlerFor(eObject);
		if (handler == null) {
			// no handler found - return object itself, without modifications
			return eObject;
		}
		return handler.wrap(eObject);
	}

	/**
	 * @param eObject
	 * @return null if no custom type handler returns true
	 * @throws IllegalStateException
	 *             if there are two handlers that matches this eObject
	 */
	private CustomTypeHandler getHandlerFor(EObject eObject) throws IllegalStateException {
		CustomTypeHandler result = null;
		for (CustomTypeHandler handler : allCustomTypeHandlers) {
			if (handler.matches(eObject)) {
				if (result != null) {
					throw new IllegalStateException(String.format("Two handlers found for object: %s, first: %s, second: %s",
							eObject, result, handler));
				}
				result = handler;
			}
		}
		return result;
	}

}
