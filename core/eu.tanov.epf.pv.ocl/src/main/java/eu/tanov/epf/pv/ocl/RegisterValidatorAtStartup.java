package eu.tanov.epf.pv.ocl;

import java.util.List;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.epf.library.edit.util.ExtensionManager;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.ui.IStartup;

import eu.tanov.epf.pv.ocl.extension.CustomTypeHandler;
import eu.tanov.epf.pv.ocl.validation.ExtendedLibraryEValidator;

public class RegisterValidatorAtStartup implements IStartup {
	/**
	 * TODO move somewhere else
	 */
	private static final String EXTENSION_POINT_NAME = "CustomTypeHandler";

	private static final String NS_URI_EXTENDED_UMA = "http://www.tanov.eu/epf/pv/uma/extended/1.0.0/extendeduma.ecore";

	public void earlyStartup() {
		final List<CustomTypeHandler> customTypeHandlers = getCustomTypeHandlers();

		// TODO here? or somewhere else?
		final EPackage extendedUmaPackage = createExtendedUmaPackage();
		createCustomTypes(extendedUmaPackage, customTypeHandlers);

		EValidator.Registry.INSTANCE.put(UmaPackage.eINSTANCE, new ExtendedLibraryEValidator(customTypeHandlers));
	}

	private void createCustomTypes(EPackage extendedUmaPackage, List<CustomTypeHandler> customTypeHandlers) {
		for (CustomTypeHandler customTypeHandler : customTypeHandlers) {
			customTypeHandler.registerType(extendedUmaPackage);
		}
	}

	/**
	 * based on http://www.ibm.com/developerworks/library/os-eclipse-dynamicemf/
	 * 
	 * @return created package
	 */
	private EPackage createExtendedUmaPackage() {
		final EPackage extendedUmaPackage = EcoreFactory.eINSTANCE.createEPackage();
		extendedUmaPackage.setName("ExtendedUma");
		extendedUmaPackage.setNsPrefix("eUma");
		extendedUmaPackage.setNsURI(NS_URI_EXTENDED_UMA);

		EPackage.Registry.INSTANCE.put(NS_URI_EXTENDED_UMA, extendedUmaPackage);

		return extendedUmaPackage;
	}

	/**
	 * from extension
	 */
	private List<CustomTypeHandler> getCustomTypeHandlers() {
		return ExtensionManager.getExtensions(Activator.PLUGIN_ID, EXTENSION_POINT_NAME, CustomTypeHandler.class);
	}
}
