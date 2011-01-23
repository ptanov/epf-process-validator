package eu.tanov.epf.pv.types.projectpractice.ui.pages;

import org.eclipse.epf.authoring.ui.forms.ContentElementGuidancePage;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;

import eu.tanov.epf.pv.types.projectpractice.ui.i18n.ProjectPracticeUIResources;
import eu.tanov.epf.pv.types.projectpractice.ui.provider.ProjectPracticeItemProvider;
import eu.tanov.epf.pv.ui.common.util.EditorHelper;
import eu.tanov.epf.pv.ui.common.util.FormHelper;

public class ProjectPracticeGuidancePage extends ContentElementGuidancePage {
	private static final String classNameForFormTitle = ProjectPracticeUIResources.projectPractice_text;

	public ProjectPracticeGuidancePage(FormEditor editor) {
		super(editor);
		EditorHelper.updateTitleImage(editor, ProjectPracticeItemProvider.getProjectPracticeImage());
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
		return ProjectPracticeUIResources.projectPractice_guidancepage_sectiondescription;
	}
}
