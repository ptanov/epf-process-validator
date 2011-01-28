package eu.tanov.epf.pv.wizards.consistency;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import eu.tanov.epf.pv.service.ocl.extension.OCLConstraintsDefinition;

/**
 * The activator class controls the plug-in life cycle
 */
public class ConsistencyActivator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "eu.tanov.epf.pv.wizards.consistency"; //$NON-NLS-1$

	// The shared instance
	private static ConsistencyActivator plugin;

	private OCLConstraintsDefinition definitionFromPreferences = null;

	private BundleContext context;

	/**
	 * The constructor
	 */
	public ConsistencyActivator() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		
		this.context = context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
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
	public static ConsistencyActivator getDefault() {
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
	public OCLConstraintsDefinition getDefinitionFromPreferences() {
		return definitionFromPreferences;
	}

	public void setDefinitionFromPreferences(OCLConstraintsDefinition definitionFromPreferences) {
		this.definitionFromPreferences = definitionFromPreferences;
	}

}
