package eu.tanov.epf.pv.types.standard.ui.pages;

import java.util.List;

import org.eclipse.epf.authoring.ui.AuthoringUIText;
import org.eclipse.epf.library.edit.itemsfilter.FilterConstants;
import org.eclipse.epf.uma.Task;
import org.eclipse.ui.forms.editor.FormEditor;

import eu.tanov.epf.pv.service.types.util.AbstractFilteredContentElementOrderList;
import eu.tanov.epf.pv.service.types.util.TypeFilteredContentElementOrderList;
import eu.tanov.epf.pv.types.standard.common.util.StandardHelper;
import eu.tanov.epf.pv.types.standard.ui.i18n.StandardUIResources;
import eu.tanov.epf.pv.types.standard.ui.provider.StandardItemProvider;
import eu.tanov.epf.pv.ui.common.pages.AbstractCustomCategoryPage;
import eu.tanov.epf.pv.ui.common.util.EditorHelper;

public class StandardTasksPage extends AbstractCustomCategoryPage<Task> {
	private static final String FORM_PAGE_ID = "standardTasksPage"; //$NON-NLS-1$	

	public StandardTasksPage(FormEditor editor) {
		super(editor, FORM_PAGE_ID, AuthoringUIText.TASKS_PAGE_TITLE, Task.class, StandardUIResources.standard_text);
		EditorHelper.updateTitleImage(editor, StandardItemProvider.getStandardImage());
	}

	@Override
	protected String tabString() {
		return FilterConstants.TASKS;
	}

	@Override
	protected String multipleSelectDescription(int count) {
		return StandardUIResources.bind(StandardUIResources.standardTasksPage_multipleSelectDescription, new Integer(count));
	}

	@Override
	protected String sectionDescription() {
		return StandardUIResources.standardTasksPage_sectionDescription;
	}

	@Override
	protected String sectionName() {
		return StandardUIResources.standardTasksPage_sectionName;
	}

	@Override
	protected String selectedLabel() {
		return StandardUIResources.standardTasksPage_selectedLabel;
	}

	@Override
	protected String[] modelStructurePath() {
		return StandardHelper.STANDARDS_PATH;
	}

	@Override
	protected AbstractFilteredContentElementOrderList<Task> createFilteredContentElementOderList() {
		return new TypeFilteredContentElementOrderList<Task>(contentElement, getOrderFeature(), clazz) {
			private static final long serialVersionUID = -4411920693746082284L;

			@Override
			protected List<Task> toFilteredList() {
				// add work products from tasks.mandatoryInput
				StandardHelper.updateWorkProducts(container);
				return super.toFilteredList();
			}
		};
	}

}
