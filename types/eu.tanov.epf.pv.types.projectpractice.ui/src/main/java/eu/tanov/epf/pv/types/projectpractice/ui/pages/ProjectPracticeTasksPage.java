package eu.tanov.epf.pv.types.projectpractice.ui.pages;

import java.util.List;

import org.eclipse.epf.authoring.ui.AuthoringUIText;
import org.eclipse.epf.library.edit.itemsfilter.FilterConstants;
import org.eclipse.epf.uma.Task;
import org.eclipse.ui.forms.editor.FormEditor;

import eu.tanov.epf.pv.service.types.util.AbstractFilteredContentElementOrderList;
import eu.tanov.epf.pv.service.types.util.TypeFilteredContentElementOrderList;
import eu.tanov.epf.pv.types.projectpractice.common.util.ProjectPracticeHelper;
import eu.tanov.epf.pv.types.projectpractice.ui.i18n.ProjectPracticeUIResources;
import eu.tanov.epf.pv.types.projectpractice.ui.provider.ProjectPracticeItemProvider;
import eu.tanov.epf.pv.types.projectpractice.ui.provider.ProjectPracticesCategoryItemProvider;
import eu.tanov.epf.pv.ui.common.pages.AbstractCustomCategoryPage;
import eu.tanov.epf.pv.ui.common.util.EditorHelper;

public class ProjectPracticeTasksPage extends AbstractCustomCategoryPage<Task> {
	private static final String FORM_PAGE_ID = "projectPracticeTasksPage"; //$NON-NLS-1$	

	public ProjectPracticeTasksPage(FormEditor editor) {
		super(editor, FORM_PAGE_ID, AuthoringUIText.TASKS_PAGE_TITLE, Task.class, ProjectPracticeUIResources.projectPractice_text);
		EditorHelper.updateTitleImage(editor, ProjectPracticeItemProvider.getProjectPracticeImage());
	}

	@Override
	protected String tabString() {
		return FilterConstants.TASKS;
	}

	@Override
	protected String multipleSelectDescription(int count) {
		return ProjectPracticeUIResources.bind(ProjectPracticeUIResources.projectPracticeTasksPage_multipleSelectDescription,
				new Integer(count));
	}

	@Override
	protected String sectionDescription() {
		return ProjectPracticeUIResources.projectPracticeTasksPage_sectionDescription;
	}

	@Override
	protected String sectionName() {
		return ProjectPracticeUIResources.projectPracticeTasksPage_sectionName;
	}

	@Override
	protected String selectedLabel() {
		return ProjectPracticeUIResources.projectPracticeTasksPage_selectedLabel;
	}

	@Override
	protected String[] modelStructurePath() {
		return ProjectPracticesCategoryItemProvider.PROJECT_PRACTICES_PATH;
	}

	@Override
	protected AbstractFilteredContentElementOrderList<Task> createFilteredContentElementOderList() {
		return new TypeFilteredContentElementOrderList<Task>(contentElement, getOrderFeature(), clazz) {
			private static final long serialVersionUID = -4411920693746082284L;

			@Override
			protected List<Task> toFilteredList() {
				// add work products from tasks.mandatoryInput
				ProjectPracticeHelper.updateWorkProducts(container);
				return super.toFilteredList();
			}
		};
	}

}
