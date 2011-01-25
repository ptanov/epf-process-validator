package eu.tanov.epf.pv.types.projectiteration.ui.extension;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.epf.library.edit.ILibraryItemProvider;
import org.eclipse.epf.uma.ContentPackage;

import eu.tanov.epf.itemprovider.extension.AbstractExtendedItemProvider;
import eu.tanov.epf.itemprovider.extension.ExtendedItemProvider;
import eu.tanov.epf.pv.types.projectiteration.common.util.ProjectIterationHelper;
import eu.tanov.epf.pv.types.projectiteration.ui.i18n.ProjectIterationUIResources;
import eu.tanov.epf.pv.types.projectiteration.ui.provider.ProjectIterationsCategoryItemProvider;

public class ProjectIterationsExtendedItemProvider extends AbstractExtendedItemProvider implements ExtendedItemProvider {

	public ProjectIterationsExtendedItemProvider() {
		super(ProjectIterationHelper.PROJECT_ITERATIONS_PATH);
	}

	@Override
	protected ILibraryItemProvider createProvider(AdapterFactory adapterFactory, ContentPackage contentPkg, String name) {
		return new ProjectIterationsCategoryItemProvider(adapterFactory, contentPkg, name);
	}

	@Override
	protected String getCategoryLocalizedName() {
		return ProjectIterationUIResources._UI_ProjectIterations_group;
	}

}
