package eu.tanov.epf.pv.types.project.ui.pages;

import java.util.List;

import org.eclipse.epf.authoring.ui.AuthoringUIText;
import org.eclipse.epf.library.edit.itemsfilter.FilterConstants;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.ui.forms.editor.FormEditor;

import eu.tanov.epf.pv.service.types.util.AbstractFilteredContentElementOrderList;
import eu.tanov.epf.pv.service.types.util.TypeFilteredContentElementOrderList;
import eu.tanov.epf.pv.types.project.common.util.ProjectHelper;
import eu.tanov.epf.pv.types.project.ui.i18n.ProjectUIResources;
import eu.tanov.epf.pv.types.project.ui.provider.ProjectItemProvider;
import eu.tanov.epf.pv.ui.common.pages.AbstractCustomCategoryPage;
import eu.tanov.epf.pv.ui.common.util.EditorHelper;

public class ProjectWorkProductsPage extends AbstractCustomCategoryPage<WorkProduct> {
	private static final String FORM_PAGE_ID = "projectWorkProductsPage"; //$NON-NLS-1$	

	public ProjectWorkProductsPage(FormEditor editor) {
		super(editor, FORM_PAGE_ID, AuthoringUIText.WORK_PRODUCTS_PAGE_TITLE, WorkProduct.class,
				ProjectUIResources.project_text);
		EditorHelper.updateTitleImage(editor, ProjectItemProvider.getProjectImage());
	}

	@Override
	protected String tabString() {
		return FilterConstants.WORKPRODUCTS;
	}

	@Override
	protected String multipleSelectDescription(int count) {
		return ProjectUIResources.bind(ProjectUIResources.projectWorkProductsPage_multipleSelectDescription, new Integer(
				count));
	}

	@Override
	protected String sectionDescription() {
		return ProjectUIResources.projectWorkProductsPage_sectionDescription;
	}

	@Override
	protected String sectionName() {
		return ProjectUIResources.projectWorkProductsPage_sectionName;
	}

	@Override
	protected String selectedLabel() {
		return ProjectUIResources.projectWorkProductsPage_selectedLabel;
	}

	@Override
	protected String[] modelStructurePath() {
		return ProjectHelper.PROJECTS_PATH;
	}

	@Override
	protected AbstractFilteredContentElementOrderList<WorkProduct> createFilteredContentElementOderList() {
		return new TypeFilteredContentElementOrderList<WorkProduct>(contentElement, getOrderFeature(), clazz) {
			private static final long serialVersionUID = 6454750512209162031L;

			@Override
			protected List<WorkProduct> toFilteredList() {
				// add work products from tasks.mandatoryInput
				ProjectHelper.updateWorkProducts(container);
				return super.toFilteredList();
			}
		};
	}
}
