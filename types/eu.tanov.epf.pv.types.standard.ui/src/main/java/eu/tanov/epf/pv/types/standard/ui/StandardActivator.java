package eu.tanov.epf.pv.types.standard.ui;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

/**
 * The activator class controls the plug-in life cycle
 */
public class StandardActivator extends AbstractUIPlugin {
	private static final String EXTENSION_ICON = ".png";

	// The relative path to the icons.
	private static final String ICON_PATH = "icons/"; //$NON-NLS-1$;

	// The shared image hash map.
	protected final Map<String, Image> images = new HashMap<String, Image>();

	private BundleContext context;

	// The plug-in ID
	public static final String PLUGIN_ID = "eu.tanov.epf.pv.types.standard.ui"; //$NON-NLS-1$

	// The shared instance
	private static StandardActivator plugin;

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

	public Image getImage(String key) {
		final Image cachedResult = images.get(key);
		if (cachedResult != null) {
			// use cached
			return cachedResult;
		}

		// fetch
		final ImageDescriptor imageDescriptor = imageDescriptorFromPlugin(PLUGIN_ID, ICON_PATH + key + EXTENSION_ICON);
		final Image result = imageDescriptor.createImage();
		images.put(key, result);
		return result;
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
