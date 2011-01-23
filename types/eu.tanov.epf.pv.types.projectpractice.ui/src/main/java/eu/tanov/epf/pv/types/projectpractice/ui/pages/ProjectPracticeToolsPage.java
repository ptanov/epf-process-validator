package eu.tanov.epf.pv.types.projectpractice.ui.pages;

import org.eclipse.epf.library.edit.itemsfilter.FilterConstants;
import org.eclipse.epf.uma.Tool;
import org.eclipse.ui.forms.editor.FormEditor;

import eu.tanov.epf.pv.service.types.util.FilteredContentElementOrderList;
import eu.tanov.epf.pv.types.projectpractice.ui.i18n.ProjectPracticeUIResources;
import eu.tanov.epf.pv.types.projectpractice.ui.provider.ProjectPracticeItemProvider;
import eu.tanov.epf.pv.types.projectpractice.ui.provider.ProjectPracticesCategoryItemProvider;
import eu.tanov.epf.pv.ui.common.pages.AbstractCustomCategoryPage;
import eu.tanov.epf.pv.ui.common.util.EditorHelper;

public class ProjectPracticeToolsPage extends AbstractCustomCategoryPage<Tool> {
	private static final String FORM_PAGE_ID = "projectPracticeToolsPage"; //$NON-NLS-1$	

	public ProjectPracticeToolsPage(FormEditor editor) {
		super(editor, FORM_PAGE_ID, ProjectPracticeUIResources.projectPracticeToolsPage_title, Tool.class,
				ProjectPracticeUIResources.projectPractice_text);
		EditorHelper.updateTitleImage(editor, ProjectPracticeItemProvider.getProjectPracticeImage());
	}

	@Override
	protected String tabString() {
		return FilterConstants.TOOLS;
	}

	@Override
	protected String multipleSelectDescription(int count) {
		return ProjectPracticeUIResources.bind(ProjectPracticeUIResources.projectPracticeToolsPage_multipleSelectDescription,
				new Integer(count));
	}

	@Override
	protected String sectionDescription() {
		return ProjectPracticeUIResources.projectPracticeToolsPage_sectionDescription;
	}

	@Override
	protected String sectionName() {
		return ProjectPracticeUIResources.projectPracticeToolsPage_sectionName;
	}

	@Override
	protected String selectedLabel() {
		return ProjectPracticeUIResources.projectPracticeToolsPage_selectedLabel;
	}

	@Override
	protected String[] modelStructurePath() {
		return ProjectPracticesCategoryItemProvider.PROJECT_PRACTICES_PATH;
	}

	@Override
	protected FilteredContentElementOrderList<Tool> createFilteredContentElementOderList() {
		return new FilteredContentElementOrderList<Tool>(contentElement, getOrderFeature(), clazz);
	}

}
