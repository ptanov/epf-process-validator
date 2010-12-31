package eu.tanov.epf.pv.ui.techniques.pages;

import org.eclipse.epf.authoring.ui.AuthoringUIText;
import org.eclipse.epf.library.edit.itemsfilter.FilterConstants;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.ui.forms.editor.FormEditor;

import eu.tanov.epf.pv.ui.common.pages.AbstractCustomCategoryPage;
import eu.tanov.epf.pv.ui.techniques.i18n.TechniquesUIResources;
import eu.tanov.epf.pv.ui.techniques.provider.TechniquesCategoryItemProvider;

public class TechniqueWorkProductsPage extends AbstractCustomCategoryPage<WorkProduct> {
	private static final String FORM_PAGE_ID = "techniqueWorkProductsPage"; //$NON-NLS-1$	

	public TechniqueWorkProductsPage(FormEditor editor) {
		super(editor, FORM_PAGE_ID, AuthoringUIText.WORK_PRODUCTS_PAGE_TITLE, WorkProduct.class,
				TechniquesUIResources.technique_text);
	}

	@Override
	protected String tabString() {
		return FilterConstants.WORKPRODUCTS;
	}

	@Override
	protected String multipleSelectDescription(int count) {
		return TechniquesUIResources.bind(TechniquesUIResources.techniqueWorkProductsPage_multipleSelectDescription, new Integer(
				count));
	}

	@Override
	protected String sectionDescription() {
		return TechniquesUIResources.techniqueWorkProductsPage_sectionDescription;
	}

	@Override
	protected String sectionName() {
		return TechniquesUIResources.techniqueWorkProductsPage_sectionName;
	}

	@Override
	protected String selectedLabel() {
		return TechniquesUIResources.techniqueWorkProductsPage_selectedLabel;
	}

	@Override
	protected String[] modelStructurePath() {
		return TechniquesCategoryItemProvider.TECHNIQUES_PATH;
	}
}