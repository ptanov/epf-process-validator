package eu.tanov.epf.pv.types.project.ui.pages;

import java.util.List;

import org.eclipse.epf.authoring.ui.AuthoringUIText;
import org.eclipse.epf.library.edit.itemsfilter.FilterConstants;
import org.eclipse.epf.uma.Task;
import org.eclipse.ui.forms.editor.FormEditor;

import eu.tanov.epf.pv.service.types.util.AbstractFilteredContentElementOrderList;
import eu.tanov.epf.pv.service.types.util.TypeFilteredContentElementOrderList;
import eu.tanov.epf.pv.types.project.common.util.ProjectHelper;
import eu.tanov.epf.pv.types.project.ui.i18n.ProjectUIResources;
import eu.tanov.epf.pv.types.project.ui.provider.ProjectItemProvider;
import eu.tanov.epf.pv.ui.common.pages.AbstractCustomCategoryPage;
import eu.tanov.epf.pv.ui.common.util.EditorHelper;

public class ProjectTasksPage extends AbstractCustomCategoryPage<Task> {
	private static final String FORM_PAGE_ID = "projectTasksPage"; //$NON-NLS-1$	

	public ProjectTasksPage(FormEditor editor) {
		super(editor, FORM_PAGE_ID, AuthoringUIText.TASKS_PAGE_TITLE, Task.class, ProjectUIResources.project_text);
		EditorHelper.updateTitleImage(editor, ProjectItemProvider.getProjectImage());
	}

	@Override
	protected String tabString() {
		return FilterConstants.TASKS;
	}

	@Override
	protected String multipleSelectDescription(int count) {
		return ProjectUIResources.bind(ProjectUIResources.projectTasksPage_multipleSelectDescription, new Integer(count));
	}

	@Override
	protected String sectionDescription() {
		return ProjectUIResources.projectTasksPage_sectionDescription;
	}

	@Override
	protected String sectionName() {
		return ProjectUIResources.projectTasksPage_sectionName;
	}

	@Override
	protected String selectedLabel() {
		return ProjectUIResources.projectTasksPage_selectedLabel;
	}

	@Override
	protected String[] modelStructurePath() {
		return ProjectHelper.PROJECTS_PATH;
	}

	@Override
	protected AbstractFilteredContentElementOrderList<Task> createFilteredContentElementOderList() {
		return new TypeFilteredContentElementOrderList<Task>(contentElement, getOrderFeature(), clazz) {
			private static final long serialVersionUID = -4411920693746082284L;

			@Override
			protected List<Task> toFilteredList() {
				// add work products from tasks.mandatoryInput
				ProjectHelper.updateWorkProducts(container);
				return super.toFilteredList();
			}
		};
	}

}
