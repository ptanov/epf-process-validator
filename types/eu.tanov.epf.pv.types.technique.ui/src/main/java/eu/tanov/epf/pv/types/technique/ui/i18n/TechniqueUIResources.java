package eu.tanov.epf.pv.types.technique.ui.i18n;

import org.eclipse.osgi.util.NLS;

public final class TechniqueUIResources extends NLS {

	private static String BUNDLE_NAME = TechniqueUIResources.class.getPackage().getName() + ".Resources"; //$NON-NLS-1$

	private TechniqueUIResources() {
		// Do not instantiate.
	}

	public static String techniqueTasksPage_sectionDescription;
	public static String techniqueTasksPage_sectionName;
	public static String techniqueTasksPage_multipleSelectDescription;
	public static String techniqueTasksPage_selectedLabel;

	public static String techniqueWorkProductsPage_selectedLabel;
	public static String techniqueWorkProductsPage_sectionName;
	public static String techniqueWorkProductsPage_sectionDescription;
	public static String techniqueWorkProductsPage_multipleSelectDescription;

	public static String _UI_Techniques_group;

	public static String technique_detailSection_desc;
	public static String technique_generalInfoSection_desc;
	public static String technique_variabilitySection_desc;
	public static String technique_versionInfoSection_desc;
	public static String technique_IconSection_desc;

	public static String technique_guidancepage_sectiondescription;
	public static String technique_text;
	public static String Techniques_child;

	static {
		NLS.initializeMessages(BUNDLE_NAME, TechniqueUIResources.class);
	}

}