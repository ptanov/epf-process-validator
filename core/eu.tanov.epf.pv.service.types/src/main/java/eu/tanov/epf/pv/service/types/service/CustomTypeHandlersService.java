package eu.tanov.epf.pv.service.types.service;

import java.util.Collection;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.epf.uma.DescribableElement;

import eu.tanov.epf.pv.service.types.handler.CustomTypeHandler;

public interface CustomTypeHandlersService {

	public Collection<CustomTypeHandler<?>> getHandlers();

	/**
	 * @param eObject
	 * @return same eObject if it is not holder for custom type
	 */
	public EObject wrapObjectIfNeeded(EObject eObject);

	/**
	 * @param eObject
	 * @param type
	 * @return true if eObject can be wrapped to type using registered custom type handler
	 */
	public boolean canWrapTo(EObject eObject, EClassifier type);

	/**
	 * @param <T>
	 *            generic type of returned CustomTypeHandler
	 * @param customType
	 *            type that handler should handle
	 * @param expectedHolderType
	 *            expected generic type of CustomTypeHandler
	 * @return handler for customType
	 * @throws IllegalArgumentException
	 *             if there is no registered handler for customType
	 */
	public <T extends DescribableElement> CustomTypeHandler<T> getHandlerForType(EClass customType, Class<T> expectedHolderType)
			throws IllegalArgumentException;
}
