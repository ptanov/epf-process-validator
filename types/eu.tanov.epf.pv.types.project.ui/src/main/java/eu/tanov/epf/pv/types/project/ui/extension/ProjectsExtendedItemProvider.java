package eu.tanov.epf.pv.types.project.ui.extension;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.epf.library.edit.ILibraryItemProvider;
import org.eclipse.epf.uma.ContentPackage;

import eu.tanov.epf.itemprovider.extension.AbstractExtendedItemProvider;
import eu.tanov.epf.itemprovider.extension.ExtendedItemProvider;
import eu.tanov.epf.pv.types.project.common.util.ProjectHelper;
import eu.tanov.epf.pv.types.project.ui.i18n.ProjectUIResources;
import eu.tanov.epf.pv.types.project.ui.provider.ProjectsCategoryItemProvider;

public class ProjectsExtendedItemProvider extends AbstractExtendedItemProvider implements ExtendedItemProvider {

	public ProjectsExtendedItemProvider() {
		super(ProjectHelper.PROJECTS_PATH);
	}

	@Override
	protected ILibraryItemProvider createProvider(AdapterFactory adapterFactory, ContentPackage contentPkg, String name) {
		return new ProjectsCategoryItemProvider(adapterFactory, contentPkg, name);
	}

	@Override
	protected String getCategoryLocalizedName() {
		return ProjectUIResources._UI_Projects_group;
	}

	@Override
	public int position() {
		return 60;
	}
}
