package eu.tanov.epf.pv.service.ocl.service;

import java.util.List;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;

import eu.tanov.epf.pv.service.ocl.extension.CustomTypeHandler;

public interface CustomTypeHandlersService {

	public List<CustomTypeHandler> getHandlers();

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

}
