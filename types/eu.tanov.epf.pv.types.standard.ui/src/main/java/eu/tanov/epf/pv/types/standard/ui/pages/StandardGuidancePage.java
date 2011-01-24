package eu.tanov.epf.pv.types.standard.ui.pages;

import org.eclipse.epf.authoring.ui.forms.ContentElementGuidancePage;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;

import eu.tanov.epf.pv.types.standard.ui.i18n.StandardUIResources;
import eu.tanov.epf.pv.types.standard.ui.provider.StandardItemProvider;
import eu.tanov.epf.pv.ui.common.util.EditorHelper;
import eu.tanov.epf.pv.ui.common.util.FormHelper;

public class StandardGuidancePage extends ContentElementGuidancePage {
	private static final String classNameForFormTitle = StandardUIResources.standard_text;

	public StandardGuidancePage(FormEditor editor) {
		super(editor);
		EditorHelper.updateTitleImage(editor, StandardItemProvider.getStandardImage());
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
		return StandardUIResources.standard_guidancepage_sectiondescription;
	}
}
