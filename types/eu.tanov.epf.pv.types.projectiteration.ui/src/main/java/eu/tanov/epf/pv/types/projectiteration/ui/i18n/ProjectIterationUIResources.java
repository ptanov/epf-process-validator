package eu.tanov.epf.pv.types.projectiteration.ui.i18n;

import org.eclipse.osgi.util.NLS;

public final class ProjectIterationUIResources extends NLS {

	private static String BUNDLE_NAME = ProjectIterationUIResources.class.getPackage().getName() + ".Resources"; //$NON-NLS-1$

	private ProjectIterationUIResources() {
		// Do not instantiate.
	}

	public static String projectIterationTasksPage_sectionDescription;
	public static String projectIterationTasksPage_sectionName;
	public static String projectIterationTasksPage_multipleSelectDescription;
	public static String projectIterationTasksPage_selectedLabel;

	public static String projectIterationWorkProductsPage_selectedLabel;
	public static String projectIterationWorkProductsPage_sectionName;
	public static String projectIterationWorkProductsPage_sectionDescription;
	public static String projectIterationWorkProductsPage_multipleSelectDescription;

	public static String _UI_ProjectIterations_group;

	public static String projectIteration_detailSection_desc;
	public static String projectIteration_generalInfoSection_desc;
	public static String projectIteration_variabilitySection_desc;
	public static String projectIteration_versionInfoSection_desc;
	public static String projectIteration_IconSection_desc;

	public static String projectIteration_guidancepage_sectiondescription;
	public static String projectIteration_text;
	public static String ProjectIterations_child;

	static {
		NLS.initializeMessages(BUNDLE_NAME, ProjectIterationUIResources.class);
	}

}