package eu.tanov.epf.pv.types.standard.ui.pages;

import org.eclipse.epf.authoring.ui.filters.ReferenceWorkFlowFilter;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.itemsfilter.FilterConstants;
import org.eclipse.epf.uma.WorkBreakdownElement;
import org.eclipse.ui.forms.editor.FormEditor;

import eu.tanov.epf.pv.types.standard.common.util.StandardHelper;
import eu.tanov.epf.pv.types.standard.ui.i18n.StandardUIResources;
import eu.tanov.epf.pv.types.standard.ui.provider.StandardItemProvider;
import eu.tanov.epf.pv.ui.common.pages.AbstractCustomCategoryPage;
import eu.tanov.epf.pv.ui.common.util.EditorHelper;

public class StandardWorkflowsPage extends AbstractCustomCategoryPage<WorkBreakdownElement> {
	private static final String FORM_PAGE_ID = "standardWorkflowsPage"; //$NON-NLS-1$	

	public StandardWorkflowsPage(FormEditor editor) {
		super(editor, FORM_PAGE_ID, StandardUIResources.standardWorkflowsPage_title, WorkBreakdownElement.class,
				StandardUIResources.standard_text);
		EditorHelper.updateTitleImage(editor, StandardItemProvider.getStandardImage());
	}

	@Override
	protected String tabString() {
		return FilterConstants.PROCESSES;
	}

	@Override
	protected String multipleSelectDescription(int count) {
		return StandardUIResources.bind(StandardUIResources.standardWorkflowsPage_multipleSelectDescription, new Integer(count));
	}

	@Override
	protected String sectionDescription() {
		return StandardUIResources.standardWorkflowsPage_sectionDescription;
	}

	@Override
	protected String sectionName() {
		return StandardUIResources.standardWorkflowsPage_sectionName;
	}

	@Override
	protected String selectedLabel() {
		return StandardUIResources.standardWorkflowsPage_selectedLabel;
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
