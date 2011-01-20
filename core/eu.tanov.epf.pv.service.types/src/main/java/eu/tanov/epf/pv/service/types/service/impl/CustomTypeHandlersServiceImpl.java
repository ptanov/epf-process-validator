package eu.tanov.epf.pv.service.types.service.impl;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.epf.library.edit.util.ExtensionManager;

import eu.tanov.epf.pv.service.types.TypesActivator;
import eu.tanov.epf.pv.service.types.handler.CustomTypeHandler;
import eu.tanov.epf.pv.service.types.service.CustomTypeHandlersService;

public class CustomTypeHandlersServiceImpl implements CustomTypeHandlersService {
	private static final String EXTENSION_POINT_NAME = "CustomTypeHandler";

	private final List<CustomTypeHandler> handlers;

	public CustomTypeHandlersServiceImpl() {
		// from extension
		handlers = ExtensionManager.getExtensions(TypesActivator.PLUGIN_ID, EXTENSION_POINT_NAME, CustomTypeHandler.class);
	}

	public List<CustomTypeHandler> getHandlers() {
		return Collections.unmodifiableList(handlers);
	}

	public EObject wrapObjectIfNeeded(EObject eObject) {
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
		for (CustomTypeHandler handler : handlers) {
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

	public boolean canWrapTo(EObject eObject, EClassifier type) {
		final CustomTypeHandler handler = getHandlerFor(eObject);

		if (handler == null) {
			// there is no handler - can't wrap
			return false;
		}

		return handler.getCustomType() == type;
	}
}
