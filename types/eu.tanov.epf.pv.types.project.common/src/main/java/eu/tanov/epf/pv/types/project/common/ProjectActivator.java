package eu.tanov.epf.pv.types.project.common;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class ProjectActivator extends Plugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "eu.tanov.epf.pv.types.project.common"; //$NON-NLS-1$

	// The shared instance
	private static ProjectActivator plugin;

	private BundleContext context;

	/**
	 * The constructor
	 */
	public ProjectActivator() {
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
	public static ProjectActivator getDefault() {
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
