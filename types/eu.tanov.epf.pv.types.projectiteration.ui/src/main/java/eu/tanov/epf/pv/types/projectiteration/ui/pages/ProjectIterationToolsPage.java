package eu.tanov.epf.pv.types.projectiteration.ui.pages;

import org.eclipse.epf.library.edit.itemsfilter.FilterConstants;
import org.eclipse.epf.uma.Tool;
import org.eclipse.ui.forms.editor.FormEditor;

import eu.tanov.epf.pv.types.projectiteration.ui.i18n.ProjectIterationUIResources;
import eu.tanov.epf.pv.types.projectiteration.ui.provider.ProjectIterationItemProvider;
import eu.tanov.epf.pv.types.projectpractice.common.util.ProjectPracticeHelper;
import eu.tanov.epf.pv.ui.common.pages.AbstractCustomCategoryPage;
import eu.tanov.epf.pv.ui.common.util.EditorHelper;

public class ProjectIterationToolsPage extends AbstractCustomCategoryPage<Tool> {
	private static final String FORM_PAGE_ID = "projectIterationToolsPage"; //$NON-NLS-1$	

	public ProjectIterationToolsPage(FormEditor editor) {
		super(editor, FORM_PAGE_ID, ProjectIterationUIResources.projectIterationToolsPage_title, Tool.class,
				ProjectIterationUIResources.projectIteration_text);
		EditorHelper.updateTitleImage(editor, ProjectIterationItemProvider.getProjectIterationImage());
	}

	@Override
	protected String tabString() {
		return FilterConstants.TOOLS;
	}

	@Override
	protected String multipleSelectDescription(int count) {
		return ProjectIterationUIResources.bind(ProjectIterationUIResources.projectIterationToolsPage_multipleSelectDescription,
				new Integer(count));
	}

	@Override
	protected String sectionDescription() {
		return ProjectIterationUIResources.projectIterationToolsPage_sectionDescription;
	}

	@Override
	protected String sectionName() {
		return ProjectIterationUIResources.projectIterationToolsPage_sectionName;
	}

	@Override
	protected String selectedLabel() {
		return ProjectIterationUIResources.projectIterationToolsPage_selectedLabel;
	}

	@Override
	protected String[] modelStructurePath() {
		return ProjectPracticeHelper.PROJECT_PRACTICES_PATH;
	}

}
