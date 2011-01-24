package eu.tanov.epf.pv.types.standard.common;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class StandardActivator extends Plugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "eu.tanov.epf.pv.types.standard.common"; //$NON-NLS-1$

	// The shared instance
	private static StandardActivator plugin;

	private BundleContext context;

	/**
	 * The constructor
	 */
	public StandardActivator() {
	}

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;

		this.context = context;
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		this.context = null;

		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static StandardActivator getDefault() {
		return plugin;
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
