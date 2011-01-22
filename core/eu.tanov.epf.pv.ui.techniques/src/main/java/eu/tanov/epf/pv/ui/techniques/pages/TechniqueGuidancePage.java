package eu.tanov.epf.pv.ui.techniques.pages;

import org.eclipse.epf.authoring.ui.forms.ContentElementGuidancePage;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;

import eu.tanov.epf.pv.ui.common.util.EditorHelper;
import eu.tanov.epf.pv.ui.common.util.FormHelper;
import eu.tanov.epf.pv.ui.techniques.i18n.TechniquesUIResources;
import eu.tanov.epf.pv.ui.techniques.provider.TechniqueItemProvider;

public class TechniqueGuidancePage extends ContentElementGuidancePage {
	private static final String classNameForFormTitle = TechniquesUIResources.technique_text;

	public TechniqueGuidancePage(FormEditor editor) {
		super(editor);
		EditorHelper.updateTitleImage(editor, TechniqueItemProvider.getTechniqueImage());
	}

	@Override
	protected void createFormContent(IManagedForm managedForm) {
		super.createFormContent(managedForm);

		FormHelper.updateFormText(form, classNameForFormTitle, contentElement);
	}

	@Override
	protected void addListeners() {
		super.addListeners();

		FormHelper.replaceLastListener(form, SWT.Activate, new Listener() {
			public void handleEvent(Event e) {
				FormHelper.updateFormText(form, classNameForFormTitle, contentElement);

				refreshViewers();
				if (TngUtil.isLocked(contentElement)) {
					enableControls(false);
				} else {
					enableControls(true);
				}
			}
		}, "org.eclipse.epf.authoring.ui.forms.AssociationFormPage");

		FormHelper.replaceLastListener(form, SWT.Deactivate, new Listener() {
			public void handleEvent(Event e) {
				FormHelper.updateFormText(form, classNameForFormTitle, contentElement);

				refreshViewers();
				if (TngUtil.isLocked(contentElement)) {
					enableControls(false);
				} else {
					enableControls(true);
				}
			}
		}, "org.eclipse.epf.authoring.ui.forms.AssociationFormPage");
	}

	@Override
	protected String getSectionDescription() {
		return TechniquesUIResources.technique_guidancepage_sectiondescription;
	}
}
