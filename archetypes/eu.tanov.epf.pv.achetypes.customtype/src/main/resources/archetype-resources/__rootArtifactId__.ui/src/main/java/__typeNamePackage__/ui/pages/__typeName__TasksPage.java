package eu.tanov.epf.pv.types.${typeNamePackage}.ui.pages;

import java.util.List;

import org.eclipse.epf.authoring.ui.AuthoringUIText;
import org.eclipse.epf.library.edit.itemsfilter.FilterConstants;
import org.eclipse.epf.uma.Task;
import org.eclipse.ui.forms.editor.FormEditor;

import eu.tanov.epf.pv.service.types.util.AbstractFilteredContentElementOrderList;
import eu.tanov.epf.pv.service.types.util.TypeFilteredContentElementOrderList;
import eu.tanov.epf.pv.types.${typeNamePackage}.common.util.${typeName}Helper;
import eu.tanov.epf.pv.types.${typeNamePackage}.ui.i18n.${typeName}UIResources;
import eu.tanov.epf.pv.types.${typeNamePackage}.ui.provider.${typeName}ItemProvider;
import eu.tanov.epf.pv.ui.common.pages.AbstractCustomCategoryPage;
import eu.tanov.epf.pv.ui.common.util.EditorHelper;

public class ${typeName}TasksPage extends AbstractCustomCategoryPage<Task> {
	private static final String FORM_PAGE_ID = "${typeNameVariable}TasksPage"; //$NON-NLS-1$	

	public ${typeName}TasksPage(FormEditor editor) {
		super(editor, FORM_PAGE_ID, AuthoringUIText.TASKS_PAGE_TITLE, Task.class, ${typeName}UIResources.${typeNameVariable}_text);
		EditorHelper.updateTitleImage(editor, ${typeName}ItemProvider.get${typeName}Image());
	}

	@Override
	protected String tabString() {
		return FilterConstants.TASKS;
	}

	@Override
	protected String multipleSelectDescription(int count) {
		return ${typeName}UIResources.bind(${typeName}UIResources.${typeNameVariable}TasksPage_multipleSelectDescription, new Integer(count));
	}

	@Override
	protected String sectionDescription() {
		return ${typeName}UIResources.${typeNameVariable}TasksPage_sectionDescription;
	}

	@Override
	protected String sectionName() {
		return ${typeName}UIResources.${typeNameVariable}TasksPage_sectionName;
	}

	@Override
	protected String selectedLabel() {
		return ${typeName}UIResources.${typeNameVariable}TasksPage_selectedLabel;
	}

	@Override
	protected String[] modelStructurePath() {
		return ${typeName}Helper.${typeNamePluralConst}_PATH;
	}

	@Override
	protected AbstractFilteredContentElementOrderList<Task> createFilteredContentElementOderList() {
		return new TypeFilteredContentElementOrderList<Task>(contentElement, getOrderFeature(), clazz) {
			private static final long serialVersionUID = -4411920693746082284L;

			@Override
			protected List<Task> toFilteredList() {
				// add work products from tasks.mandatoryInput
				${typeName}Helper.updateWorkProducts(container);
				return super.toFilteredList();
			}
		};
	}

}
