package eu.tanov.epf.pv.types.projectiteration.ui.pages;

import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.ui.forms.editor.FormEditor;

import eu.tanov.epf.pv.service.types.handler.CustomTypeHandler;
import eu.tanov.epf.pv.service.types.service.CustomTypeHandlersService;
import eu.tanov.epf.pv.types.projectiteration.common.util.ProjectIterationHelper;
import eu.tanov.epf.pv.types.projectiteration.ui.ProjectIterationActivator;
import eu.tanov.epf.pv.types.projectiteration.ui.i18n.ProjectIterationUIResources;
import eu.tanov.epf.pv.types.projectiteration.ui.provider.ProjectIterationItemProvider;
import eu.tanov.epf.pv.types.standard.common.util.StandardHelper;
import eu.tanov.epf.pv.ui.common.pages.AbstractCustomTypeCategoryPage;
import eu.tanov.epf.pv.ui.common.util.EditorHelper;

public class ProjectIterationStandardPage extends AbstractCustomTypeCategoryPage<CustomCategory> {
	private static final String FORM_PAGE_ID = "projectIterationStandardsPage"; //$NON-NLS-1$	

	public ProjectIterationStandardPage(FormEditor editor) {
		super(editor, FORM_PAGE_ID, ProjectIterationUIResources.projectIterationStandardsPage_title, getTypeHelper(),
				ProjectIterationUIResources.projectIteration_text);
		EditorHelper.updateTitleImage(editor, ProjectIterationItemProvider.getProjectIterationImage());
	}

	private static CustomTypeHandler<CustomCategory> getTypeHelper() {
		final CustomTypeHandlersService service = ProjectIterationActivator.getDefault().getService(
				CustomTypeHandlersService.class);
		return service.getHandlerForType(StandardHelper.getCustomType(), CustomCategory.class);
	}

	@Override
	protected String tabString() {
		// FIXME: filter techniques
		return ProjectIterationUIResources.projectIterationStandardsPage_filterTabString;
	}

	@Override
	protected String multipleSelectDescription(int count) {
		return ProjectIterationUIResources.bind(
				ProjectIterationUIResources.projectIterationStandardsPage_multipleSelectDescription, new Integer(count));
	}

	@Override
	protected String sectionDescription() {
		return ProjectIterationUIResources.projectIterationStandardsPage_sectionDescription;
	}

	@Override
	protected String sectionName() {
		return ProjectIterationUIResources.projectIterationStandardsPage_sectionName;
	}

	@Override
	protected String selectedLabel() {
		return ProjectIterationUIResources.projectIterationStandardsPage_selectedLabel;
	}

	@Override
	protected String[] modelStructurePath() {
		return ProjectIterationHelper.PROJECT_ITERATIONS_PATH;
	}

}
