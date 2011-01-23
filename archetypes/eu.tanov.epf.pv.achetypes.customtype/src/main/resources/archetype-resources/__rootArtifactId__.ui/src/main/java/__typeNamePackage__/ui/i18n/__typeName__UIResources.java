package eu.tanov.epf.pv.types.${typeNamePackage}.ui.i18n;

import org.eclipse.osgi.util.NLS;

public final class ${typeName}UIResources extends NLS {

	private static String BUNDLE_NAME = ${typeName}UIResources.class.getPackage().getName() + ".Resources"; //$NON-NLS-1$

	private ${typeName}UIResources() {
		// Do not instantiate.
	}

	public static String ${typeNameVariable}TasksPage_sectionDescription;
	public static String ${typeNameVariable}TasksPage_sectionName;
	public static String ${typeNameVariable}TasksPage_multipleSelectDescription;
	public static String ${typeNameVariable}TasksPage_selectedLabel;

	public static String ${typeNameVariable}WorkProductsPage_selectedLabel;
	public static String ${typeNameVariable}WorkProductsPage_sectionName;
	public static String ${typeNameVariable}WorkProductsPage_sectionDescription;
	public static String ${typeNameVariable}WorkProductsPage_multipleSelectDescription;

	public static String _UI_${typeNamePlural}_group;

	public static String ${typeNameVariable}_detailSection_desc;
	public static String ${typeNameVariable}_generalInfoSection_desc;
	public static String ${typeNameVariable}_variabilitySection_desc;
	public static String ${typeNameVariable}_versionInfoSection_desc;
	public static String ${typeNameVariable}_IconSection_desc;

	public static String ${typeNameVariable}_guidancepage_sectiondescription;
	public static String ${typeNameVariable}_text;
	public static String ${typeNamePlural}_child;

	static {
		NLS.initializeMessages(BUNDLE_NAME, ${typeName}UIResources.class);
	}

}