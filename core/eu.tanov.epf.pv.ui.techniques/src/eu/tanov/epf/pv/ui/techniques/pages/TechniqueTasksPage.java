package eu.tanov.epf.pv.ui.techniques.pages;

import org.eclipse.epf.authoring.ui.AuthoringUIText;
import org.eclipse.epf.library.edit.itemsfilter.FilterConstants;
import org.eclipse.epf.uma.Task;
import org.eclipse.ui.forms.editor.FormEditor;

import eu.tanov.epf.pv.ui.common.pages.AbstractCustomCategoryPage;
import eu.tanov.epf.pv.ui.techniques.i18n.TechniquesUIResources;
import eu.tanov.epf.pv.ui.techniques.provider.TechniquesCategoryItemProvider;

public class TechniqueTasksPage extends AbstractCustomCategoryPage<Task> {
	private static final String FORM_PAGE_ID = "techniqueTasksPage"; //$NON-NLS-1$	

	public TechniqueTasksPage(FormEditor editor) {
		super(editor, FORM_PAGE_ID, AuthoringUIText.TASKS_PAGE_TITLE, Task.class, TechniquesUIResources.technique_text);
	}

	@Override
	protected String tabString() {
		return FilterConstants.TASKS;
	}

	@Override
	protected String multipleSelectDescription(int count) {
		return TechniquesUIResources.bind(TechniquesUIResources.techniqueTasksPage_multipleSelectDescription, new Integer(count));
	}

	@Override
	protected String sectionDescription() {
		return TechniquesUIResources.techniqueTasksPage_sectionDescription;
	}

	@Override
	protected String sectionName() {
		return TechniquesUIResources.techniqueTasksPage_sectionName;
	}

	@Override
	protected String selectedLabel() {
		return TechniquesUIResources.techniqueTasksPage_selectedLabel;
	}

	@Override
	protected String[] modelStructurePath() {
		return TechniquesCategoryItemProvider.TECHNIQUES_PATH;
	}
}