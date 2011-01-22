package eu.tanov.epf.pv.types.technique.ui;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class TechniquesActivator extends AbstractUIPlugin {
	private static final String EXTENSION_ICON = ".png";

	// The relative path to the icons.
	private static final String ICON_PATH = "icons/"; //$NON-NLS-1$;

	// The shared image hash map.
	protected final Map<String, Image> images = new HashMap<String, Image>();

	// The plug-in ID
	public static final String PLUGIN_ID = "eu.tanov.epf.pv.types.technique.ui"; //$NON-NLS-1$

	// The shared instance
	private static TechniquesActivator plugin;

	/**
	 * The constructor
	 */
	public TechniquesActivator() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static TechniquesActivator getDefault() {
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

}