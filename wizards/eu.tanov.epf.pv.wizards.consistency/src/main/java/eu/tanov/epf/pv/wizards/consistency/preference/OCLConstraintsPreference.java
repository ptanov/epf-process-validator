package eu.tanov.epf.pv.wizards.consistency.preference;

import org.eclipse.emf.validation.model.ConstraintSeverity;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.SemanticException;
import org.eclipse.ocl.SyntaxException;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import eu.tanov.epf.pv.service.ocl.extension.OCLConstraintsDefinition;
import eu.tanov.epf.pv.service.ocl.service.OCLConstraintsService;
import eu.tanov.epf.pv.ui.common.widgets.OneColumnMultilineStringFieldEditor;
import eu.tanov.epf.pv.ui.common.widgets.OneColumnMultilineStringFieldEditor.StringFieldValidator;
import eu.tanov.epf.pv.wizards.consistency.ConsistencyActivator;

/**
 * TODO move to common!
 */
public class OCLConstraintsPreference extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	private static final String CATEGORY = "eu.tanov.epf.pv.validators.category.default/Wizards/Consistency";
	//TODO not public!
	public static final IPreferenceStore PREFERENCE_STORE = ConsistencyActivator.getDefault().getPreferenceStore();
	public static final String NAME_OCL_CONTENT = "oclContent";

	public OCLConstraintsPreference() {
		super(FieldEditorPreferencePage.FLAT);
	}

	@Override
	public void createFieldEditors() {
		//TODO i18n
		final OneColumnMultilineStringFieldEditor content = new OneColumnMultilineStringFieldEditor(NAME_OCL_CONTENT,
				"OCL &Content", getFieldEditorParent());
		final OCLConstraintsService service = ConsistencyActivator.getDefault().getService(OCLConstraintsService.class);
		content.setContentValidator(new StringFieldValidator() {
			@Override
			public String validate(final String content) {
				try {
					service.checkOCL(content);
				} catch (SyntaxException e) {
					return NLS.bind("Syntax error at {0}", e.getMessage());
				} catch (SemanticException e) {
					return NLS.bind("Semantic error: {0}", e.getMessage());
				} catch (ParserException e) {
					return NLS.bind("Error in OCL: {0}", e.getMessage());
				}
				return null;
			}
		});
		addField(content);
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
	 * Registers OCL constraint specified in preferences page. First try to unregister previous
	 */
	public static void registerOCLContent() {
		final OCLConstraintsService service = ConsistencyActivator.getDefault().getService(OCLConstraintsService.class);
		final String oclContent = PREFERENCE_STORE.getString(NAME_OCL_CONTENT);

		// unregister previous if any:
		final OCLConstraintsDefinition definitionFromPreferences = ConsistencyActivator.getDefault().getDefinitionFromPreferences();
		if (definitionFromPreferences != null) {
			service.removeConstraintsDefinition(definitionFromPreferences);
		}
		// register new:
		final OCLConstraintsDefinition definition = new OCLConstraintsDefinition(ConsistencyActivator.PLUGIN_ID, NAME_OCL_CONTENT,
				CATEGORY, false, ConstraintSeverity.ERROR, oclContent, "Consistency %s violated on {0}");
		service.registerConstraintsDefinition(definition);
		ConsistencyActivator.getDefault().setDefinitionFromPreferences(definition);
	}

}
