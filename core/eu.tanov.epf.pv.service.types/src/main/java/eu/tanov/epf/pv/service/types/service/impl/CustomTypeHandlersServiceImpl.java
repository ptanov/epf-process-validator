package eu.tanov.epf.pv.service.types.service.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.epf.library.edit.util.ExtensionManager;
import org.eclipse.epf.uma.DescribableElement;

import eu.tanov.epf.pv.service.types.TypesActivator;
import eu.tanov.epf.pv.service.types.handler.CustomTypeHandler;
import eu.tanov.epf.pv.service.types.service.CustomTypeHandlersService;

public class CustomTypeHandlersServiceImpl implements CustomTypeHandlersService {
	private static final String EXTENSION_POINT_NAME = "CustomTypeHandler";

	/**
	 * Custom type to corresponding handler
	 */
	private final Map<EClass, CustomTypeHandler<?>> customTypeToHandlerMap;

	public CustomTypeHandlersServiceImpl() {
		// get from extension
		@SuppressWarnings("unchecked")
		final List<? extends CustomTypeHandler<?>> handlers = (List<? extends CustomTypeHandler<?>>) ExtensionManager
				.getExtensions(TypesActivator.PLUGIN_ID, EXTENSION_POINT_NAME, CustomTypeHandler.class);

		customTypeToHandlerMap = new HashMap<EClass, CustomTypeHandler<?>>(handlers.size());

		for (CustomTypeHandler<?> handler : handlers) {
			final CustomTypeHandler<?> old = customTypeToHandlerMap.put(handler.getCustomType(), handler);

			if (old != null) {
				throw new IllegalStateException(String.format("Two handlers for type %s, first: %s, second: %s",
						handler.getCustomType(), old, handler));
			}
		}
	}

	@Override
	public Collection<CustomTypeHandler<?>> getHandlers() {
		return Collections.unmodifiableCollection(customTypeToHandlerMap.values());
	}

	@Override
	public EObject wrapObjectIfNeeded(EObject eObject) {
		final CustomTypeHandler<?> handler = getHandlerForObject(eObject);
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
	private CustomTypeHandler<?> getHandlerForObject(EObject eObject) throws IllegalStateException {
		CustomTypeHandler<?> result = null;
		for (CustomTypeHandler<?> handler : customTypeToHandlerMap.values()) {
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

	@Override
	public boolean canWrapTo(EObject eObject, EClassifier type) {
		final CustomTypeHandler<?> handler = getHandlerForObject(eObject);

		if (handler == null) {
			// there is no handler - can't wrap
			return false;
		}

		return handler.getCustomType() == type;
	}

	@Override
	public <T extends DescribableElement> CustomTypeHandler<T> getHandlerForType(EClass customType, Class<T> expectedHolderType)
			throws IllegalArgumentException, ClassCastException {
		final CustomTypeHandler<?> result = customTypeToHandlerMap.get(customType);
		if (result == null) {
			throw new IllegalArgumentException("No handler for type " + customType);
		}

		final Class<?> actualHolderType = result.getHolderType();
		if (actualHolderType != expectedHolderType) {
			throw new ClassCastException(String.format("Handler for type %s works with %s, not with %s: %s", customType,
					actualHolderType, expectedHolderType, result));
		}
		@SuppressWarnings("unchecked")
		final CustomTypeHandler<T> casted = (CustomTypeHandler<T>) result;
		return casted;
	}

}
