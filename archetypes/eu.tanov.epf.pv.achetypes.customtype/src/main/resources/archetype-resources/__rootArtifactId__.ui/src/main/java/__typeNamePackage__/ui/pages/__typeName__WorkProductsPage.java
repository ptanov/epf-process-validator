package eu.tanov.epf.pv.types.${typeNamePackage}.ui.pages;

import java.util.List;

import org.eclipse.epf.authoring.ui.AuthoringUIText;
import org.eclipse.epf.library.edit.itemsfilter.FilterConstants;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.ui.forms.editor.FormEditor;

import eu.tanov.epf.pv.service.types.util.FilteredContentElementOrderList;
import eu.tanov.epf.pv.types.${typeNamePackage}.common.util.${typeName}Helper;
import eu.tanov.epf.pv.types.${typeNamePackage}.ui.i18n.${typeName}UIResources;
import eu.tanov.epf.pv.types.${typeNamePackage}.ui.provider.${typeName}ItemProvider;
import eu.tanov.epf.pv.types.${typeNamePackage}.ui.provider.${typeNamePlural}CategoryItemProvider;
import eu.tanov.epf.pv.ui.common.pages.AbstractCustomCategoryPage;
import eu.tanov.epf.pv.ui.common.util.EditorHelper;

public class ${typeName}WorkProductsPage extends AbstractCustomCategoryPage<WorkProduct> {
	private static final String FORM_PAGE_ID = "${typeNameVariable}WorkProductsPage"; //$NON-NLS-1$	

	public ${typeName}WorkProductsPage(FormEditor editor) {
		super(editor, FORM_PAGE_ID, AuthoringUIText.WORK_PRODUCTS_PAGE_TITLE, WorkProduct.class,
				${typeName}UIResources.${typeNameVariable}_text);
		EditorHelper.updateTitleImage(editor, ${typeName}ItemProvider.get${typeName}Image());
	}

	@Override
	protected String tabString() {
		return FilterConstants.WORKPRODUCTS;
	}

	@Override
	protected String multipleSelectDescription(int count) {
		return ${typeName}UIResources.bind(${typeName}UIResources.${typeNameVariable}WorkProductsPage_multipleSelectDescription, new Integer(
				count));
	}

	@Override
	protected String sectionDescription() {
		return ${typeName}UIResources.${typeNameVariable}WorkProductsPage_sectionDescription;
	}

	@Override
	protected String sectionName() {
		return ${typeName}UIResources.${typeNameVariable}WorkProductsPage_sectionName;
	}

	@Override
	protected String selectedLabel() {
		return ${typeName}UIResources.${typeNameVariable}WorkProductsPage_selectedLabel;
	}

	@Override
	protected String[] modelStructurePath() {
		return ${typeNamePlural}CategoryItemProvider.${typeNamePluralConst}_PATH;
	}

	@Override
	protected FilteredContentElementOrderList<WorkProduct> createFilteredContentElementOderList() {
		return new FilteredContentElementOrderList<WorkProduct>(contentElement, getOrderFeature(), clazz) {
			private static final long serialVersionUID = 6454750512209162031L;

			@Override
			protected List<WorkProduct> toFilteredList() {
				// add work products from tasks.mandatoryInput
				${typeName}Helper.updateWorkProducts(container);
				return super.toFilteredList();
			}
		};
	}
}
