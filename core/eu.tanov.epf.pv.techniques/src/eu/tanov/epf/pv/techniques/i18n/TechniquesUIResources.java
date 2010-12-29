package eu.tanov.epf.pv.techniques.i18n;

import org.eclipse.osgi.util.NLS;

public final class TechniquesUIResources extends NLS {

	private static String BUNDLE_NAME = TechniquesUIResources.class.getPackage().getName() + ".Resources"; //$NON-NLS-1$

	private TechniquesUIResources() {
		// Do not instantiate.
	}

	public static String techniqueTasksPage_sectionDescription;
	public static String techniqueTasksPage_sectionName;
	public static String techniqueTasksPage_multipleSelectDescription;
	public static String techniqueTasksPage_selectedLabel;

	static {
		NLS.initializeMessages(BUNDLE_NAME, TechniquesUIResources.class);
	}

}