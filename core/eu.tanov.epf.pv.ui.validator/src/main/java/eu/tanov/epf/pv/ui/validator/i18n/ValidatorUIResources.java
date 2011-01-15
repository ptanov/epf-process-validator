package eu.tanov.epf.pv.ui.validator.i18n;

import org.eclipse.osgi.util.NLS;

public final class ValidatorUIResources extends NLS {

	private static String BUNDLE_NAME = ValidatorUIResources.class.getPackage().getName() + ".Resources"; //$NON-NLS-1$

	private ValidatorUIResources() {
		// Do not instantiate.
	}

	public static String validatorJob_name;

	static {
		NLS.initializeMessages(BUNDLE_NAME, ValidatorUIResources.class);
	}

}