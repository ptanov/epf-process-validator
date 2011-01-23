package eu.tanov.epf.pv.types.projectpractice.ui.pages;

import org.eclipse.epf.library.edit.itemsfilter.FilterConstants;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.ui.forms.editor.FormEditor;

import eu.tanov.epf.pv.service.types.handler.CustomTypeHandler;
import eu.tanov.epf.pv.service.types.service.CustomTypeHandlersService;
import eu.tanov.epf.pv.types.projectpractice.ui.ProjectPracticeActivator;
import eu.tanov.epf.pv.types.projectpractice.ui.i18n.ProjectPracticeUIResources;
import eu.tanov.epf.pv.types.projectpractice.ui.provider.ProjectPracticeItemProvider;
import eu.tanov.epf.pv.types.projectpractice.ui.provider.ProjectPracticesCategoryItemProvider;
import eu.tanov.epf.pv.types.technique.common.util.TechniqueHelper;
import eu.tanov.epf.pv.ui.common.pages.AbstractCustomTypeCategoryPage;
import eu.tanov.epf.pv.ui.common.util.EditorHelper;

public class ProjectPracticeTechniquesPage extends AbstractCustomTypeCategoryPage<CustomCategory> {
	private static final String FORM_PAGE_ID = "projectPracticeTechniquesPage"; //$NON-NLS-1$	

	public ProjectPracticeTechniquesPage(FormEditor editor) {
		super(editor, FORM_PAGE_ID, ProjectPracticeUIResources.projectPracticeTechniquesPage_title, getTypeHelper(),
				ProjectPracticeUIResources.projectPractice_text);
		EditorHelper.updateTitleImage(editor, ProjectPracticeItemProvider.getProjectPracticeImage());
	}

	private static CustomTypeHandler<CustomCategory> getTypeHelper() {
		final CustomTypeHandlersService service = ProjectPracticeActivator.getDefault().getService(
				CustomTypeHandlersService.class);
		return service.getHandlerForType(TechniqueHelper.getCustomType(), CustomCategory.class);
	}

	@Override
	protected String tabString() {
		// FIXME: filter techniques
		return FilterConstants.TASKS;
	}

	@Override
	protected String multipleSelectDescription(int count) {
		return ProjectPracticeUIResources.bind(
				ProjectPracticeUIResources.projectPracticeTechniquesPage_multipleSelectDescription, new Integer(count));
	}

	@Override
	protected String sectionDescription() {
		return ProjectPracticeUIResources.projectPracticeTechniquesPage_sectionDescription;
	}

	@Override
	protected String sectionName() {
		return ProjectPracticeUIResources.projectPracticeTechniquesPage_sectionName;
	}

	@Override
	protected String selectedLabel() {
		return ProjectPracticeUIResources.projectPracticeTechniquesPage_selectedLabel;
	}

	@Override
	protected String[] modelStructurePath() {
		return ProjectPracticesCategoryItemProvider.PROJECT_PRACTICES_PATH;
	}

}
