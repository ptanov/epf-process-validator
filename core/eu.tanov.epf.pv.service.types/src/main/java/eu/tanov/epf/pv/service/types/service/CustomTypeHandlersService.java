package eu.tanov.epf.pv.service.types.service;

import java.util.Collection;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;

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
	 * @param customType
	 * @return handler for customType
	 * @throws IllegalArgumentException
	 *             if there is no registered handler for customType
	 */
	public CustomTypeHandler<?> getHandlerForType(EClass customType) throws IllegalArgumentException;
}
