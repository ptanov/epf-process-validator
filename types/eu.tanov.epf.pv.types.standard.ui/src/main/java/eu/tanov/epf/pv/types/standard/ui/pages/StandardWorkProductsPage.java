package eu.tanov.epf.pv.types.standard.ui.pages;

import java.util.List;

import org.eclipse.epf.authoring.ui.AuthoringUIText;
import org.eclipse.epf.library.edit.itemsfilter.FilterConstants;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.ui.forms.editor.FormEditor;

import eu.tanov.epf.pv.service.types.util.AbstractFilteredContentElementOrderList;
import eu.tanov.epf.pv.service.types.util.TypeFilteredContentElementOrderList;
import eu.tanov.epf.pv.types.standard.common.util.StandardHelper;
import eu.tanov.epf.pv.types.standard.ui.i18n.StandardUIResources;
import eu.tanov.epf.pv.types.standard.ui.provider.StandardItemProvider;
import eu.tanov.epf.pv.ui.common.pages.AbstractCustomCategoryPage;
import eu.tanov.epf.pv.ui.common.util.EditorHelper;

public class StandardWorkProductsPage extends AbstractCustomCategoryPage<WorkProduct> {
	private static final String FORM_PAGE_ID = "standardWorkProductsPage"; //$NON-NLS-1$	

	public StandardWorkProductsPage(FormEditor editor) {
		super(editor, FORM_PAGE_ID, AuthoringUIText.WORK_PRODUCTS_PAGE_TITLE, WorkProduct.class,
				StandardUIResources.standard_text);
		EditorHelper.updateTitleImage(editor, StandardItemProvider.getStandardImage());
	}

	@Override
	protected String tabString() {
		return FilterConstants.WORKPRODUCTS;
	}

	@Override
	protected String multipleSelectDescription(int count) {
		return StandardUIResources.bind(StandardUIResources.standardWorkProductsPage_multipleSelectDescription, new Integer(
				count));
	}

	@Override
	protected String sectionDescription() {
		return StandardUIResources.standardWorkProductsPage_sectionDescription;
	}

	@Override
	protected String sectionName() {
		return StandardUIResources.standardWorkProductsPage_sectionName;
	}

	@Override
	protected String selectedLabel() {
		return StandardUIResources.standardWorkProductsPage_selectedLabel;
	}

	@Override
	protected String[] modelStructurePath() {
		return StandardHelper.STANDARDS_PATH;
	}

	@Override
	protected AbstractFilteredContentElementOrderList<WorkProduct> createFilteredContentElementOderList() {
		return new TypeFilteredContentElementOrderList<WorkProduct>(contentElement, getOrderFeature(), clazz) {
			private static final long serialVersionUID = 6454750512209162031L;

			@Override
			protected List<WorkProduct> toFilteredList() {
				// add work products from tasks.mandatoryInput
				StandardHelper.updateWorkProducts(container);
				return super.toFilteredList();
			}
		};
	}
}
