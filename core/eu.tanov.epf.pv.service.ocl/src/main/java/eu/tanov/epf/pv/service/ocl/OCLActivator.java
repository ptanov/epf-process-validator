package eu.tanov.epf.pv.service.ocl;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import eu.tanov.epf.pv.service.ocl.service.OCLConstraintsService;
import eu.tanov.epf.pv.service.ocl.service.impl.OCLConstraintsServiceImpl;

public class OCLActivator extends Plugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "eu.tanov.epf.pv.service.ocl"; //$NON-NLS-1$

	// The shared instance
	private static OCLActivator plugin;

	private BundleContext context;

	private ServiceRegistration oclConstraintsServiceRegistration;

	/**
	 * The constructor
	 */
	public OCLActivator() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		this.context = context;

		registerServices();
	}

	private void registerServices() {
		oclConstraintsServiceRegistration = context.registerService(OCLConstraintsService.class.getName(),
				new OCLConstraintsServiceImpl(), null);
	}

	private void unregisterServices() {
		if (oclConstraintsServiceRegistration != null) {
			oclConstraintsServiceRegistration.unregister();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
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
	public static OCLActivator getDefault() {
		return plugin;
	}

	public static void log(String message, Throwable t) {
		getDefault().getLog().log(new Status(IStatus.ERROR, PLUGIN_ID, 1, message, t));
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
