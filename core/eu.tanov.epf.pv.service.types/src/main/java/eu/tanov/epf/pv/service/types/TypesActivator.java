package eu.tanov.epf.pv.service.types;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import eu.tanov.epf.pv.service.types.service.CustomTypeHandlersService;
import eu.tanov.epf.pv.service.types.service.impl.CustomTypeHandlersServiceImpl;

public class TypesActivator extends Plugin {
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
		final CustomTypeHandlersServiceImpl service = new CustomTypeHandlersServiceImpl();
		customTypeHandlersServiceRegistration = context.registerService(CustomTypeHandlersService.class.getName(), service, null);

		service.initTypeContributions();
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

}
