package eu.tanov.epf.pv.types.projectpractice.ui.pages;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.epf.authoring.ui.editors.MethodElementEditor;
import org.eclipse.epf.authoring.ui.forms.CustomCategoryDescriptionPage;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.util.ContentDescriptionFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;

import eu.tanov.epf.pv.types.projectpractice.ui.i18n.ProjectPracticeUIResources;
import eu.tanov.epf.pv.types.projectpractice.ui.provider.ProjectPracticeItemProvider;
import eu.tanov.epf.pv.ui.common.util.EditorHelper;
import eu.tanov.epf.pv.ui.common.util.FormHelper;

public class ProjectPracticeDescriptionPage extends CustomCategoryDescriptionPage {
	private static final String classNameForFormTitle = ProjectPracticeUIResources.projectPractice_text;

	/**
	 * because disposed is private in parent
	 */
	private boolean disposed;

	public ProjectPracticeDescriptionPage(FormEditor editor) {
		super(editor);
		EditorHelper.updateTitleImage(editor, ProjectPracticeItemProvider.getProjectPracticeImage());
	}

	@Override
	public void init(IEditorSite site, IEditorInput input) {
		super.init(site, input);

		// without variability - if on - it searches in custom categories, not in ProjectPractices
		// but it is not used - so disable
		this.variabilitySectionOn = false;
	}

	@Override
	public void dispose() {
		super.dispose();
		disposed = true;
	}

	@Override
	protected void createFormContent(IManagedForm managedForm) {
		super.createFormContent(managedForm);

		FormHelper.updateFormText(form, classNameForFormTitle, contentElement);
	}

	@Override
	protected void addListeners() {
		super.addListeners();

		// because setFormTextWithVariableInfo() is private
		FormHelper.replaceLastListener(form.getParent(), SWT.Activate, new Listener() {
			public void handleEvent(Event event) {
				if (disposed) {
					return;
				}

				refresh(!TngUtil.isLocked(methodElement));
				FormHelper.updateFormText(form, classNameForFormTitle, contentElement);

				// do refresh variability and copyright viewer
				if (variabilitySectionOn)
					base_viewer.refresh();
				if (versionSectionOn)
					copyright_viewer.refresh();
			}
		}, "org.eclipse.epf.authoring.ui.forms.DescriptionFormPage");

	}

	/*
	 * Copy/pasted in order to save custom title
	 * because setFormTextWithVariableInfo() is private
	 */
	@Override
	protected boolean changeElementName(String name) {
		boolean success = actionMgr.doAction(IActionManager.SET, methodElement, UmaPackage.eINSTANCE.getNamedElement_Name(),
				name, -1);
		if (!success) {
			return false;
		}
		if (methodElement instanceof MethodConfiguration) {
			Resource resource = methodElement.eResource();
			if (resource != null) {
				((MethodElementEditor) getEditor()).addResourceToAdjustLocation(resource);
			}
		}
		if (ContentDescriptionFactory.hasPresentation(methodElement)) {
			Resource contentResource = contentElement.getPresentation().eResource();
			if (contentResource != null) {
				((MethodElementEditor) getEditor()).addResourceToAdjustLocation(contentResource);
			}
		}
		FormHelper.updateFormText(form, classNameForFormTitle, contentElement);
		ctrl_name.setText(name);

		return true;
	}

	/*
	 * Copy/pasted in order to save custom title
	 * because setFormTextWithVariableInfo() is private
	 */
	@Override
	public void refreshName(String newName) {
		if (newName != null) {
			if ((ctrl_name != null) && !(ctrl_name.isDisposed())) {
				if (modelModifyListener != null) {
					ctrl_name.removeModifyListener(modelModifyListener);
					ctrl_name.setText(newName);
					ctrl_name.addModifyListener(modelModifyListener);
				} else {
					ctrl_name.setText(newName);
				}
				FormHelper.updateFormText(form, classNameForFormTitle, contentElement);
			}
		}
	}

	@Override
	public void loadSectionDescription() {
		this.generalSectionDescription = ProjectPracticeUIResources.projectPractice_generalInfoSection_desc;
		this.detailSectionDescription = ProjectPracticeUIResources.projectPractice_detailSection_desc;
		this.variabilitySectionDescription = ProjectPracticeUIResources.projectPractice_variabilitySection_desc;
		this.versionSectionDescription = ProjectPracticeUIResources.projectPractice_versionInfoSection_desc;
		this.iconSectionDescription = ProjectPracticeUIResources.projectPractice_IconSection_desc;
	}

}
