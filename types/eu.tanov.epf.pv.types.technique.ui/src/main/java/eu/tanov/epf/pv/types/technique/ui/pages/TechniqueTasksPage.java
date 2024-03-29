package eu.tanov.epf.pv.types.technique.ui.pages;

import java.util.List;

import org.eclipse.epf.authoring.ui.AuthoringUIText;
import org.eclipse.epf.library.edit.itemsfilter.FilterConstants;
import org.eclipse.epf.uma.Task;
import org.eclipse.ui.forms.editor.FormEditor;

import eu.tanov.epf.pv.service.types.util.AbstractFilteredContentElementOrderList;
import eu.tanov.epf.pv.service.types.util.TypeFilteredContentElementOrderList;
import eu.tanov.epf.pv.types.technique.common.util.TechniqueHelper;
import eu.tanov.epf.pv.types.technique.ui.i18n.TechniqueUIResources;
import eu.tanov.epf.pv.types.technique.ui.provider.TechniqueItemProvider;
import eu.tanov.epf.pv.ui.common.pages.AbstractCustomCategoryPage;
import eu.tanov.epf.pv.ui.common.util.EditorHelper;

public class TechniqueTasksPage extends AbstractCustomCategoryPage<Task> {
	private static final String FORM_PAGE_ID = "techniqueTasksPage"; //$NON-NLS-1$	

	public TechniqueTasksPage(FormEditor editor) {
		super(editor, FORM_PAGE_ID, AuthoringUIText.TASKS_PAGE_TITLE, Task.class, TechniqueUIResources.technique_text);
		EditorHelper.updateTitleImage(editor, TechniqueItemProvider.getTechniqueImage());
	}

	@Override
	protected String tabString() {
		return FilterConstants.TASKS;
	}

	@Override
	protected String multipleSelectDescription(int count) {
		return TechniqueUIResources.bind(TechniqueUIResources.techniqueTasksPage_multipleSelectDescription, new Integer(count));
	}

	@Override
	protected String sectionDescription() {
		return TechniqueUIResources.techniqueTasksPage_sectionDescription;
	}

	@Override
	protected String sectionName() {
		return TechniqueUIResources.techniqueTasksPage_sectionName;
	}

	@Override
	protected String selectedLabel() {
		return TechniqueUIResources.techniqueTasksPage_selectedLabel;
	}

	@Override
	protected String[] modelStructurePath() {
		return TechniqueHelper.TECHNIQUES_PATH;
	}

	@Override
	protected AbstractFilteredContentElementOrderList<Task> createFilteredContentElementOderList() {
		return new TypeFilteredContentElementOrderList<Task>(contentElement, getOrderFeature(), clazz) {
			private static final long serialVersionUID = -4411920693746082284L;

			@Override
			protected List<Task> toFilteredList() {
				// add work products from tasks.mandatoryInput
				TechniqueHelper.updateWorkProducts(container);
				return super.toFilteredList();
			}
		};
	}

}
