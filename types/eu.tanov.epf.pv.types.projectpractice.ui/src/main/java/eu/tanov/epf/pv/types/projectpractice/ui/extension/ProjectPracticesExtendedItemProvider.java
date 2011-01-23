package eu.tanov.epf.pv.types.projectpractice.ui.extension;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.epf.library.edit.ILibraryItemProvider;
import org.eclipse.epf.uma.ContentPackage;

import eu.tanov.epf.itemprovider.extension.AbstractExtendedItemProvider;
import eu.tanov.epf.itemprovider.extension.ExtendedItemProvider;
import eu.tanov.epf.pv.types.projectpractice.ui.i18n.ProjectPracticeUIResources;
import eu.tanov.epf.pv.types.projectpractice.ui.provider.ProjectPracticesCategoryItemProvider;

public class ProjectPracticesExtendedItemProvider extends AbstractExtendedItemProvider implements ExtendedItemProvider {

	public ProjectPracticesExtendedItemProvider() {
		super(ProjectPracticesCategoryItemProvider.PROJECT_PRACTICES_PATH);
	}

	@Override
	protected ILibraryItemProvider createProvider(AdapterFactory adapterFactory, ContentPackage contentPkg, String name) {
		return new ProjectPracticesCategoryItemProvider(adapterFactory, contentPkg, name);
	}

	@Override
	protected String getCategoryLocalizedName() {
		return ProjectPracticeUIResources._UI_ProjectPractices_group;
	}

}
