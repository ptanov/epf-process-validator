package eu.tanov.epf.pv.ui.ocl.preference;

import org.eclipse.emf.validation.model.ConstraintSeverity;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import eu.tanov.epf.pv.service.ocl.extension.OCLConstraintsDefinition;
import eu.tanov.epf.pv.service.ocl.service.OCLConstraintsService;
import eu.tanov.epf.pv.ui.ocl.OCLActivator;
import eu.tanov.epf.pv.ui.ocl.i18n.OCLUIResources;

public class OCLConstraintsPreference extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	private static final IPreferenceStore PREFERENCE_STORE = OCLActivator.getDefault().getPreferenceStore();
	private static final String CATEGORY = "eu.tanov.epf.pv.validators.category.default/OCL/preferences";
	private static final String NAME_OCL_CONTENT = "oclContent";

	public OCLConstraintsPreference() {
		super(FieldEditorPreferencePage.FLAT);
	}

	@Override
	public void createFieldEditors() {
		addField(new OneColumnMultilineStringFieldEditor(NAME_OCL_CONTENT, OCLUIResources.preferences_ocl_label, getFieldEditorParent()));
	}

	@Override
	public void init(IWorkbench workbench) {
		setPreferenceStore(PREFERENCE_STORE);
	}

	@Override
	public boolean performOk() {
		boolean result = super.performOk();

		if (result) {
			registerOCLContent();
		}
		return result;
	}

	/**
	 * first try to unregister previous
	 */
	public static void registerOCLContent() {
		final OCLConstraintsService service = OCLActivator.getDefault().getService(OCLConstraintsService.class);
		final String oclContent = PREFERENCE_STORE.getString(NAME_OCL_CONTENT);

		//unregister previous if any:
		final OCLConstraintsDefinition definitionFromPreferences = OCLActivator.getDefault().getDefinitionFromPreferences();
		if (definitionFromPreferences != null) {
			service.removeConstraintsDefinition(definitionFromPreferences);
		}
		//register new:
		final OCLConstraintsDefinition definition = new OCLConstraintsDefinition(OCLActivator.PLUGIN_ID, NAME_OCL_CONTENT,
				CATEGORY, false, ConstraintSeverity.ERROR, oclContent, OCLUIResources.ocl_error_message);
		service.registerConstraintsDefinition(definition);
		OCLActivator.getDefault().setDefinitionFromPreferences(definition);
	}

}
