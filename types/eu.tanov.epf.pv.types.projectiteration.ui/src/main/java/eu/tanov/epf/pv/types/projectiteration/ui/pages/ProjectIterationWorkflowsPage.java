package eu.tanov.epf.pv.types.projectiteration.ui.pages;

import org.eclipse.epf.authoring.ui.filters.ReferenceWorkFlowFilter;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.itemsfilter.FilterConstants;
import org.eclipse.epf.uma.WorkBreakdownElement;
import org.eclipse.ui.forms.editor.FormEditor;

import eu.tanov.epf.pv.types.projectiteration.ui.i18n.ProjectIterationUIResources;
import eu.tanov.epf.pv.types.projectiteration.ui.provider.ProjectIterationItemProvider;
import eu.tanov.epf.pv.types.standard.common.util.StandardHelper;
import eu.tanov.epf.pv.ui.common.pages.AbstractCustomCategoryPage;
import eu.tanov.epf.pv.ui.common.util.EditorHelper;

public class ProjectIterationWorkflowsPage extends AbstractCustomCategoryPage<WorkBreakdownElement> {
	private static final String FORM_PAGE_ID = "projectIterationWorkflowsPage"; //$NON-NLS-1$	

	public ProjectIterationWorkflowsPage(FormEditor editor) {
		super(editor, FORM_PAGE_ID, ProjectIterationUIResources.projectIterationWorkflowsPage_title, WorkBreakdownElement.class,
				ProjectIterationUIResources.projectIteration_text);
		EditorHelper.updateTitleImage(editor, ProjectIterationItemProvider.getProjectIterationImage());
	}

	@Override
	protected String tabString() {
		return FilterConstants.PROCESSES;
	}

	@Override
	protected String multipleSelectDescription(int count) {
		return ProjectIterationUIResources.bind(
				ProjectIterationUIResources.projectIterationWorkflowsPage_multipleSelectDescription, new Integer(count));
	}

	@Override
	protected String sectionDescription() {
		return ProjectIterationUIResources.projectIterationWorkflowsPage_sectionDescription;
	}

	@Override
	protected String sectionName() {
		return ProjectIterationUIResources.projectIterationWorkflowsPage_sectionName;
	}

	@Override
	protected String selectedLabel() {
		return ProjectIterationUIResources.projectIterationWorkflowsPage_selectedLabel;
	}

	/*
	 * Based on DisciplineReferenceWorkflowPage.getFilter()
	 */
	@Override
	protected IFilter createFilter() {
		return new ReferenceWorkFlowFilter();
	}

	@Override
	protected String[] modelStructurePath() {
		return StandardHelper.STANDARDS_PATH;
	}

}
