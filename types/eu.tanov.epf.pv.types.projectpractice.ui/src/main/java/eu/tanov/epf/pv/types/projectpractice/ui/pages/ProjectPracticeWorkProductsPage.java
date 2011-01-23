package eu.tanov.epf.pv.types.projectpractice.ui.pages;

import java.util.List;

import org.eclipse.epf.authoring.ui.AuthoringUIText;
import org.eclipse.epf.library.edit.itemsfilter.FilterConstants;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.ui.forms.editor.FormEditor;

import eu.tanov.epf.pv.service.types.util.AbstractFilteredContentElementOrderList;
import eu.tanov.epf.pv.service.types.util.TypeFilteredContentElementOrderList;
import eu.tanov.epf.pv.types.projectpractice.common.util.ProjectPracticeHelper;
import eu.tanov.epf.pv.types.projectpractice.ui.i18n.ProjectPracticeUIResources;
import eu.tanov.epf.pv.types.projectpractice.ui.provider.ProjectPracticeItemProvider;
import eu.tanov.epf.pv.types.projectpractice.ui.provider.ProjectPracticesCategoryItemProvider;
import eu.tanov.epf.pv.ui.common.pages.AbstractCustomCategoryPage;
import eu.tanov.epf.pv.ui.common.util.EditorHelper;

public class ProjectPracticeWorkProductsPage extends AbstractCustomCategoryPage<WorkProduct> {
	private static final String FORM_PAGE_ID = "projectPracticeWorkProductsPage"; //$NON-NLS-1$	

	public ProjectPracticeWorkProductsPage(FormEditor editor) {
		super(editor, FORM_PAGE_ID, AuthoringUIText.WORK_PRODUCTS_PAGE_TITLE, WorkProduct.class,
				ProjectPracticeUIResources.projectPractice_text);
		EditorHelper.updateTitleImage(editor, ProjectPracticeItemProvider.getProjectPracticeImage());
	}

	@Override
	protected String tabString() {
		return FilterConstants.WORKPRODUCTS;
	}

	@Override
	protected String multipleSelectDescription(int count) {
		return ProjectPracticeUIResources.bind(
				ProjectPracticeUIResources.projectPracticeWorkProductsPage_multipleSelectDescription, new Integer(count));
	}

	@Override
	protected String sectionDescription() {
		return ProjectPracticeUIResources.projectPracticeWorkProductsPage_sectionDescription;
	}

	@Override
	protected String sectionName() {
		return ProjectPracticeUIResources.projectPracticeWorkProductsPage_sectionName;
	}

	@Override
	protected String selectedLabel() {
		return ProjectPracticeUIResources.projectPracticeWorkProductsPage_selectedLabel;
	}

	@Override
	protected String[] modelStructurePath() {
		return ProjectPracticesCategoryItemProvider.PROJECT_PRACTICES_PATH;
	}

	@Override
	protected AbstractFilteredContentElementOrderList<WorkProduct> createFilteredContentElementOderList() {
		return new TypeFilteredContentElementOrderList<WorkProduct>(contentElement, getOrderFeature(), clazz) {
			private static final long serialVersionUID = 6454750512209162031L;

			@Override
			protected List<WorkProduct> toFilteredList() {
				// add work products from tasks.mandatoryInput
				ProjectPracticeHelper.updateWorkProducts(container);
				return super.toFilteredList();
			}
		};
	}
}
