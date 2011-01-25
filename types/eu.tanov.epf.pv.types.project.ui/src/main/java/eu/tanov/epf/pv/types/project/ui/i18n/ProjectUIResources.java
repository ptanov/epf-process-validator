package eu.tanov.epf.pv.types.project.ui.i18n;

import org.eclipse.osgi.util.NLS;

public final class ProjectUIResources extends NLS {

	private static String BUNDLE_NAME = ProjectUIResources.class.getPackage().getName() + ".Resources"; //$NON-NLS-1$

	private ProjectUIResources() {
		// Do not instantiate.
	}

	public static String projectProjectIterationsPage_sectionDescription;
	public static String projectProjectIterationsPage_sectionName;
	public static String projectProjectIterationsPage_multipleSelectDescription;
	public static String projectProjectIterationsPage_selectedLabel;
	public static String projectProjectIterationsPage_title;
	public static String projectProjectIterationsPage_filterTabString;

	public static String _UI_Projects_group;

	public static String project_detailSection_desc;
	public static String project_generalInfoSection_desc;
	public static String project_variabilitySection_desc;
	public static String project_versionInfoSection_desc;
	public static String project_IconSection_desc;

	public static String project_guidancepage_sectiondescription;
	public static String project_text;
	public static String Projects_child;

	static {
		NLS.initializeMessages(BUNDLE_NAME, ProjectUIResources.class);
	}

}