package eu.tanov.epf.pv.service.ocl;

import java.util.List;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.ui.IStartup;

import eu.tanov.epf.pv.service.ocl.extension.CustomTypeHandler;
import eu.tanov.epf.pv.service.ocl.service.impl.CustomTypeHandlersServiceImpl;

public class InitExtendedUmaPackageAtStartup implements IStartup {
	private static final String NS_URI_EXTENDED_UMA = "http://www.tanov.eu/epf/pv/uma/extended/1.0.0/extendeduma.ecore";

	public void earlyStartup() {
		final List<CustomTypeHandler> customTypeHandlers = CustomTypeHandlersServiceImpl.getInstance().getHandlers();

		final EPackage extendedUmaPackage = createExtendedUmaPackage();

		createCustomTypes(extendedUmaPackage, customTypeHandlers);
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

}
