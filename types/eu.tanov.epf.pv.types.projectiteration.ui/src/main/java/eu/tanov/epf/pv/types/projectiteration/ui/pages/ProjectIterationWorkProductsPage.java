package eu.tanov.epf.pv.types.projectiteration.ui.pages;

import java.util.List;

import org.eclipse.epf.authoring.ui.AuthoringUIText;
import org.eclipse.epf.library.edit.itemsfilter.FilterConstants;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.ui.forms.editor.FormEditor;

import eu.tanov.epf.pv.service.types.util.AbstractFilteredContentElementOrderList;
import eu.tanov.epf.pv.service.types.util.TypeFilteredContentElementOrderList;
import eu.tanov.epf.pv.types.projectiteration.common.util.ProjectIterationHelper;
import eu.tanov.epf.pv.types.projectiteration.ui.i18n.ProjectIterationUIResources;
import eu.tanov.epf.pv.types.projectiteration.ui.provider.ProjectIterationItemProvider;
import eu.tanov.epf.pv.ui.common.pages.AbstractCustomCategoryPage;
import eu.tanov.epf.pv.ui.common.util.EditorHelper;

public class ProjectIterationWorkProductsPage extends AbstractCustomCategoryPage<WorkProduct> {
	private static final String FORM_PAGE_ID = "projectIterationWorkProductsPage"; //$NON-NLS-1$	

	public ProjectIterationWorkProductsPage(FormEditor editor) {
		super(editor, FORM_PAGE_ID, AuthoringUIText.WORK_PRODUCTS_PAGE_TITLE, WorkProduct.class,
				ProjectIterationUIResources.projectIteration_text);
		EditorHelper.updateTitleImage(editor, ProjectIterationItemProvider.getProjectIterationImage());
	}

	@Override
	protected String tabString() {
		return FilterConstants.WORKPRODUCTS;
	}

	@Override
	protected String multipleSelectDescription(int count) {
		return ProjectIterationUIResources.bind(ProjectIterationUIResources.projectIterationWorkProductsPage_multipleSelectDescription, new Integer(
				count));
	}

	@Override
	protected String sectionDescription() {
		return ProjectIterationUIResources.projectIterationWorkProductsPage_sectionDescription;
	}

	@Override
	protected String sectionName() {
		return ProjectIterationUIResources.projectIterationWorkProductsPage_sectionName;
	}

	@Override
	protected String selectedLabel() {
		return ProjectIterationUIResources.projectIterationWorkProductsPage_selectedLabel;
	}

	@Override
	protected String[] modelStructurePath() {
		return ProjectIterationHelper.PROJECT_ITERATIONS_PATH;
	}

	@Override
	protected AbstractFilteredContentElementOrderList<WorkProduct> createFilteredContentElementOderList() {
		return new TypeFilteredContentElementOrderList<WorkProduct>(contentElement, getOrderFeature(), clazz) {
			private static final long serialVersionUID = 6454750512209162031L;

			@Override
			protected List<WorkProduct> toFilteredList() {
				// add work products from tasks.mandatoryInput
				ProjectIterationHelper.updateWorkProducts(container);
				return super.toFilteredList();
			}
		};
	}
}
