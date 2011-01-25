package eu.tanov.epf.pv.types.project.ui.pages;

import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.ui.forms.editor.FormEditor;

import eu.tanov.epf.pv.service.types.handler.CustomTypeHandler;
import eu.tanov.epf.pv.service.types.service.CustomTypeHandlersService;
import eu.tanov.epf.pv.types.project.common.util.ProjectHelper;
import eu.tanov.epf.pv.types.project.ui.ProjectActivator;
import eu.tanov.epf.pv.types.project.ui.i18n.ProjectUIResources;
import eu.tanov.epf.pv.types.project.ui.provider.ProjectItemProvider;
import eu.tanov.epf.pv.types.projectiteration.common.util.ProjectIterationHelper;
import eu.tanov.epf.pv.ui.common.pages.AbstractCustomTypeCategoryPage;
import eu.tanov.epf.pv.ui.common.util.EditorHelper;

public class ProjectProjectIterationPage extends AbstractCustomTypeCategoryPage<CustomCategory> {
	private static final String FORM_PAGE_ID = "projectProjectIterationsPage"; //$NON-NLS-1$	

	public ProjectProjectIterationPage(FormEditor editor) {
		super(editor, FORM_PAGE_ID, ProjectUIResources.projectProjectIterationsPage_title, getTypeHelper(),
				ProjectUIResources.project_text);
		EditorHelper.updateTitleImage(editor, ProjectItemProvider.getProjectImage());
	}

	private static CustomTypeHandler<CustomCategory> getTypeHelper() {
		final CustomTypeHandlersService service = ProjectActivator.getDefault().getService(CustomTypeHandlersService.class);
		return service.getHandlerForType(ProjectIterationHelper.getCustomType(), CustomCategory.class);
	}

	@Override
	protected String tabString() {
		// FIXME: filter techniques
		return ProjectUIResources.projectProjectIterationsPage_filterTabString;
	}

	@Override
	protected String multipleSelectDescription(int count) {
		return ProjectUIResources.bind(ProjectUIResources.projectProjectIterationsPage_multipleSelectDescription, new Integer(
				count));
	}

	@Override
	protected String sectionDescription() {
		return ProjectUIResources.projectProjectIterationsPage_sectionDescription;
	}

	@Override
	protected String sectionName() {
		return ProjectUIResources.projectProjectIterationsPage_sectionName;
	}

	@Override
	protected String selectedLabel() {
		return ProjectUIResources.projectProjectIterationsPage_selectedLabel;
	}

	@Override
	protected String[] modelStructurePath() {
		return ProjectHelper.PROJECTS_PATH;
	}

}
