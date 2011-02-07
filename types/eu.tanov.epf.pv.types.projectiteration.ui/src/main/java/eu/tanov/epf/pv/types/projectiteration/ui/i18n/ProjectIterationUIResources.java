package eu.tanov.epf.pv.types.projectiteration.ui.i18n;

import org.eclipse.osgi.util.NLS;

public final class ProjectIterationUIResources extends NLS {

	private static String BUNDLE_NAME = ProjectIterationUIResources.class.getPackage().getName() + ".Resources"; //$NON-NLS-1$

	private ProjectIterationUIResources() {
		// Do not instantiate.
	}

	public static String projectIterationWorkProductsPage_selectedLabel;
	public static String projectIterationWorkProductsPage_sectionName;
	public static String projectIterationWorkProductsPage_sectionDescription;
	public static String projectIterationWorkProductsPage_multipleSelectDescription;

	public static String projectIterationProjectPracticesPage_sectionDescription;
	public static String projectIterationProjectPracticesPage_sectionName;
	public static String projectIterationProjectPracticesPage_multipleSelectDescription;
	public static String projectIterationProjectPracticesPage_selectedLabel;
	public static String projectIterationProjectPracticesPage_title;
	public static String projectIterationProjectPracticesPage_filterTabString;

	public static String projectIterationStandardsPage_sectionDescription;
	public static String projectIterationStandardsPage_sectionName;
	public static String projectIterationStandardsPage_multipleSelectDescription;
	public static String projectIterationStandardsPage_selectedLabel;
	public static String projectIterationStandardsPage_title;
	public static String projectIterationStandardsPage_filterTabString;

	public static String projectIterationToolsPage_sectionDescription;
	public static String projectIterationToolsPage_sectionName;
	public static String projectIterationToolsPage_multipleSelectDescription;
	public static String projectIterationToolsPage_selectedLabel;
	public static String projectIterationToolsPage_title;
	
	public static String projectIterationWorkflowsPage_sectionDescription;
	public static String projectIterationWorkflowsPage_sectionName;
	public static String projectIterationWorkflowsPage_multipleSelectDescription;
	public static String projectIterationWorkflowsPage_selectedLabel;
	public static String projectIterationWorkflowsPage_title;

	public static String projectIteration_detailSection_desc;
	public static String projectIteration_generalInfoSection_desc;
	public static String projectIteration_variabilitySection_desc;
	public static String projectIteration_versionInfoSection_desc;
	public static String projectIteration_IconSection_desc;

	public static String projectIteration_guidancepage_sectiondescription;
	public static String projectIteration_text;

	static {
		NLS.initializeMessages(BUNDLE_NAME, ProjectIterationUIResources.class);
	}

}