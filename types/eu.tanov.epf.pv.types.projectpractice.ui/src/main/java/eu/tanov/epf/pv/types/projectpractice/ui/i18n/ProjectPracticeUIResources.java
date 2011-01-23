package eu.tanov.epf.pv.types.projectpractice.ui.i18n;

import org.eclipse.osgi.util.NLS;

public final class ProjectPracticeUIResources extends NLS {

	private static String BUNDLE_NAME = ProjectPracticeUIResources.class.getPackage().getName() + ".Resources"; //$NON-NLS-1$

	private ProjectPracticeUIResources() {
		// Do not instantiate.
	}

	public static String projectPracticeToolsPage_sectionDescription;
	public static String projectPracticeToolsPage_sectionName;
	public static String projectPracticeToolsPage_multipleSelectDescription;
	public static String projectPracticeToolsPage_selectedLabel;
	public static String projectPracticeToolsPage_title;
	
	public static String projectPracticeTasksPage_sectionDescription;
	public static String projectPracticeTasksPage_sectionName;
	public static String projectPracticeTasksPage_multipleSelectDescription;
	public static String projectPracticeTasksPage_selectedLabel;

	public static String projectPracticeWorkProductsPage_selectedLabel;
	public static String projectPracticeWorkProductsPage_sectionName;
	public static String projectPracticeWorkProductsPage_sectionDescription;
	public static String projectPracticeWorkProductsPage_multipleSelectDescription;

	public static String projectPracticeRolesPage_sectionDescription;
	public static String projectPracticeRolesPage_sectionName;
	public static String projectPracticeRolesPage_multipleSelectDescription;
	public static String projectPracticeRolesPage_selectedLabel;

	public static String projectPracticeTechniquesPage_sectionDescription;
	public static String projectPracticeTechniquesPage_sectionName;
	public static String projectPracticeTechniquesPage_multipleSelectDescription;
	public static String projectPracticeTechniquesPage_selectedLabel;
	public static String projectPracticeTechniquesPage_title;
	public static String projectPracticeTechniquesPage_filterTabString;

	public static String _UI_ProjectPractices_group;

	public static String projectPractice_detailSection_desc;
	public static String projectPractice_generalInfoSection_desc;
	public static String projectPractice_variabilitySection_desc;
	public static String projectPractice_versionInfoSection_desc;
	public static String projectPractice_IconSection_desc;

	public static String projectPractice_guidancepage_sectiondescription;
	public static String projectPractice_text;
	public static String ProjectPractices_child;

	static {
		NLS.initializeMessages(BUNDLE_NAME, ProjectPracticeUIResources.class);
	}

}