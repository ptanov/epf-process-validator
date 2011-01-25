package eu.tanov.epf.pv.types.project.ui.pages;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.util.UmaUtil;
import org.eclipse.ui.forms.editor.FormEditor;

import eu.tanov.epf.itemprovider.util.MethodPluginHelper;
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
	private static final String NEW_NAME_ITERATION = "iteration"; //$NON-NLS-1$

	public ProjectProjectIterationPage(FormEditor editor) {
		super(editor, FORM_PAGE_ID, ProjectUIResources.projectProjectIterationsPage_title, getTypeHelper(),
				ProjectUIResources.project_text);
		EditorHelper.updateTitleImage(editor, ProjectItemProvider.getProjectImage());
	}

	private static CustomTypeHandler<CustomCategory> getTypeHelper() {
		final CustomTypeHandlersService service = ProjectActivator.getDefault().getService(CustomTypeHandlersService.class);
		return service.getHandlerForType(ProjectIterationHelper.getCustomType(), CustomCategory.class);
	}

	/*
	 * Deletion of iteration, not only "remove of reference"
	 */
	@Override
	protected boolean removeFromCategory(IActionManager actionMgr, CustomCategory categoryToModify, CustomCategory objectToRemove) {
		final boolean result = super.removeFromCategory(actionMgr, categoryToModify, objectToRemove);

		final EObject eContainer = objectToRemove.eContainer();
		if (!(eContainer instanceof ContentPackage)) {
			throw new IllegalStateException(String.format("Parent of iteration %s is not ContentPackage, but: %s",
					objectToRemove, eContainer));
		}
		final ContentPackage contentPackage = (ContentPackage) eContainer;
		final boolean remove = contentPackage.getContentElements().remove(objectToRemove);
		if (remove == false) {
			throw new IllegalStateException(String.format("Iteration %s is not contained in its parent?!?!?!?: %s",
					objectToRemove, eContainer));
		}
		return result;
	}

	/*
	 * Very very BAD code!
	 * getFilter() is called only when ADD button is clicked so return null - preventing dialog to open, and create and add one
	 * iteration
	 */
	@Override
	protected IFilter getFilter() {
		createNewIteration();
		return null;
	}

	/**
	 * Copied from AbstractExtendedItemProvider.provide()
	 * 
	 * @return ContentPackage that holds Project Iterations
	 */
	private ContentPackage findIterationsContentPackage() {
		final MethodPlugin plugin = UmaUtil.getMethodPlugin(container);

		MethodPluginHelper.createContentPackages(plugin, ProjectIterationHelper.PROJECT_ITERATIONS_PATH);

		final ContentPackage result = UmaUtil.findContentPackage(plugin, ProjectIterationHelper.PROJECT_ITERATIONS_PATH);
		if (result == null) {
			throw new IllegalStateException("Can't find ContentPackage that holds Project Iterations");
		}
		return result;
	}

	private void createNewIteration() {
		final CustomCategory iteration = UmaFactory.eINSTANCE.createCustomCategory();
		final ContentPackage iterationsContentPackage = findIterationsContentPackage();

		TngUtil.setDefaultName(container.getCategorizedElements(), iteration, NEW_NAME_ITERATION);

		iterationsContentPackage.getContentElements().add(iteration);
		addToCategory(getActionManager(), container, iteration);

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
