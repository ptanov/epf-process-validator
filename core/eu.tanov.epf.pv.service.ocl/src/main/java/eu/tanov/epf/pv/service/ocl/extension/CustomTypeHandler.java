package eu.tanov.epf.pv.service.ocl.extension;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

public interface CustomTypeHandler {

	/**
	 * Registers custom type in extedendedUmaPackage.
	 * Should be called only once, before any other call of object.
	 * 
	 * @param extendedUmaPackage
	 */
	public void registerType(EPackage extendedUmaPackage);

	/**
	 * @param object
	 * @return object wrapped in custom type
	 */
	public EObject wrap(EObject object);

	/**
	 * @param object
	 * @return true if object is holder for this custom type and can be wrapped
	 */
	public boolean matches(EObject object);
	
	/**
	 * @return type of custom type
	 */
	public EClass getCustomType();
}
