package eu.tanov.epf.pv.types.technique.ui.pages;

import java.util.List;

import org.eclipse.epf.authoring.ui.AuthoringUIText;
import org.eclipse.epf.library.edit.itemsfilter.FilterConstants;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.ui.forms.editor.FormEditor;

import eu.tanov.epf.pv.service.types.util.FilteredContentElementOrderList;
import eu.tanov.epf.pv.types.technique.common.util.TechniqueHelper;
import eu.tanov.epf.pv.types.technique.ui.i18n.TechniqueUIResources;
import eu.tanov.epf.pv.types.technique.ui.provider.TechniqueItemProvider;
import eu.tanov.epf.pv.types.technique.ui.provider.TechniquesCategoryItemProvider;
import eu.tanov.epf.pv.ui.common.pages.AbstractCustomCategoryPage;
import eu.tanov.epf.pv.ui.common.util.EditorHelper;

public class TechniqueWorkProductsPage extends AbstractCustomCategoryPage<WorkProduct> {
	private static final String FORM_PAGE_ID = "techniqueWorkProductsPage"; //$NON-NLS-1$	

	public TechniqueWorkProductsPage(FormEditor editor) {
		super(editor, FORM_PAGE_ID, AuthoringUIText.WORK_PRODUCTS_PAGE_TITLE, WorkProduct.class,
				TechniqueUIResources.technique_text);
		EditorHelper.updateTitleImage(editor, TechniqueItemProvider.getTechniqueImage());
	}

	@Override
	protected String tabString() {
		return FilterConstants.WORKPRODUCTS;
	}

	@Override
	protected String multipleSelectDescription(int count) {
		return TechniqueUIResources.bind(TechniqueUIResources.techniqueWorkProductsPage_multipleSelectDescription, new Integer(
				count));
	}

	@Override
	protected String sectionDescription() {
		return TechniqueUIResources.techniqueWorkProductsPage_sectionDescription;
	}

	@Override
	protected String sectionName() {
		return TechniqueUIResources.techniqueWorkProductsPage_sectionName;
	}

	@Override
	protected String selectedLabel() {
		return TechniqueUIResources.techniqueWorkProductsPage_selectedLabel;
	}

	@Override
	protected String[] modelStructurePath() {
		return TechniquesCategoryItemProvider.TECHNIQUES_PATH;
	}

	@Override
	protected FilteredContentElementOrderList<WorkProduct> createFilteredContentElementOderList() {
		return new FilteredContentElementOrderList<WorkProduct>(contentElement, getOrderFeature(), clazz) {
			private static final long serialVersionUID = 6454750512209162031L;

			@Override
			protected List<WorkProduct> toFilteredList() {
				// add work products from tasks.mandatoryInput
				TechniqueHelper.updateWorkProducts(container);
				return super.toFilteredList();
			}
		};
	}
}
