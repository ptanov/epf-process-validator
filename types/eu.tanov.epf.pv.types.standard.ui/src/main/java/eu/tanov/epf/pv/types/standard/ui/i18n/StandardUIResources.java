package eu.tanov.epf.pv.types.standard.ui.i18n;

import org.eclipse.osgi.util.NLS;

public final class StandardUIResources extends NLS {

	private static String BUNDLE_NAME = StandardUIResources.class.getPackage().getName() + ".Resources"; //$NON-NLS-1$

	private StandardUIResources() {
		// Do not instantiate.
	}

	public static String standardWorkProductsPage_selectedLabel;
	public static String standardWorkProductsPage_sectionName;
	public static String standardWorkProductsPage_sectionDescription;
	public static String standardWorkProductsPage_multipleSelectDescription;

	public static String standardRolesPage_sectionDescription;
	public static String standardRolesPage_sectionName;
	public static String standardRolesPage_multipleSelectDescription;
	public static String standardRolesPage_selectedLabel;

	public static String standardProjectPracticesPage_sectionDescription;
	public static String standardProjectPracticesPage_sectionName;
	public static String standardProjectPracticesPage_multipleSelectDescription;
	public static String standardProjectPracticesPage_selectedLabel;
	public static String standardProjectPracticesPage_title;
	public static String standardProjectPracticesPage_filterTabString;

	public static String standardWorkflowsPage_sectionDescription;
	public static String standardWorkflowsPage_sectionName;
	public static String standardWorkflowsPage_multipleSelectDescription;
	public static String standardWorkflowsPage_selectedLabel;
	public static String standardWorkflowsPage_title;

	public static String _UI_Standards_group;

	public static String standard_detailSection_desc;
	public static String standard_generalInfoSection_desc;
	public static String standard_variabilitySection_desc;
	public static String standard_versionInfoSection_desc;
	public static String standard_IconSection_desc;

	public static String standard_guidancepage_sectiondescription;
	public static String standard_text;
	public static String Standards_child;

	static {
		NLS.initializeMessages(BUNDLE_NAME, StandardUIResources.class);
	}

}