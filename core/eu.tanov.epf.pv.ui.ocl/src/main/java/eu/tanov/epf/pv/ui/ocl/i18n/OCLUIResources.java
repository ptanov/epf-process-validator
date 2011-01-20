package eu.tanov.epf.pv.ui.ocl.i18n;

import org.eclipse.osgi.util.NLS;

public final class OCLUIResources extends NLS {

	private static String BUNDLE_NAME = OCLUIResources.class.getPackage().getName() + ".Resources"; //$NON-NLS-1$

	private OCLUIResources() {
		// Do not instantiate.
	}

	public static String preferences_ocl_label;
	public static String ocl_error_message;

	static {
		NLS.initializeMessages(BUNDLE_NAME, OCLUIResources.class);
	}

}