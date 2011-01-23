package eu.tanov.epf.pv.service.types;

import java.util.Collection;

import org.eclipse.core.runtime.Plugin;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import eu.tanov.epf.pv.service.types.handler.CustomTypeHandler;
import eu.tanov.epf.pv.service.types.service.CustomTypeHandlersService;
import eu.tanov.epf.pv.service.types.service.impl.CustomTypeHandlersServiceImpl;

public class TypesActivator extends Plugin {
	/**
	 * XXX if used outside - move to CustomTypeHelper
	 */
	public static final String NS_URI_EXTENDED_UMA = "http://www.tanov.eu/epf/pv/uma/extended/1.0.0/extendeduma.ecore";

	// The plug-in ID
	public static final String PLUGIN_ID = "eu.tanov.epf.pv.service.types"; //$NON-NLS-1$

	// The shared instance
	private static TypesActivator plugin;

	private BundleContext context;
	private ServiceRegistration customTypeHandlersServiceRegistration;

	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;

		this.context = context;

		registerServices();

		initExtendedUmaPackage();
	}

	public void stop(BundleContext context) throws Exception {
		unregisterServices();
		this.context = null;
		plugin = null;

		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static TypesActivator getDefault() {
		return plugin;
	}

	private void registerServices() {
		customTypeHandlersServiceRegistration = context.registerService(CustomTypeHandlersService.class.getName(),
				new CustomTypeHandlersServiceImpl(), null);
	}

	private void unregisterServices() {
		if (customTypeHandlersServiceRegistration != null) {
			customTypeHandlersServiceRegistration.unregister();
		}
	}

	public <T> T getService(Class<T> serviceClass) {
		final ServiceReference serviceReference = context.getServiceReference(serviceClass.getName());
		if (serviceReference != null) {
			@SuppressWarnings("unchecked")
			final T result = (T) context.getService(serviceReference);

			if (result != null) {
				return result;
			}
		}

		throw new IllegalArgumentException("Service not found: " + serviceClass.getName());
	}

	// TODO what if called twice?
	public void initExtendedUmaPackage() {
		final EPackage extendedUmaPackage = createExtendedUmaPackage();

		final CustomTypeHandlersService service = TypesActivator.getDefault().getService(CustomTypeHandlersService.class);
		final Collection<CustomTypeHandler<?>> customTypeHandlers = service.getHandlers();

		createCustomTypes(extendedUmaPackage, customTypeHandlers);
	}

	private void createCustomTypes(EPackage extendedUmaPackage, Collection<CustomTypeHandler<?>> customTypeHandlers) {
		for (CustomTypeHandler<?> customTypeHandler : customTypeHandlers) {
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
