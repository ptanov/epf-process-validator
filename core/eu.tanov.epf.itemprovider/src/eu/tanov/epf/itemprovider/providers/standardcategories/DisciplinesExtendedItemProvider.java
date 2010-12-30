package eu.tanov.epf.itemprovider.providers.standardcategories;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.epf.library.edit.ILibraryItemProvider;
import org.eclipse.epf.library.edit.category.DisciplineCategoriesItemProvider;
import org.eclipse.epf.library.edit.util.ModelStructure;
import org.eclipse.epf.uma.ContentPackage;

import eu.tanov.epf.itemprovider.extension.AbstractExtendedItemProvider;
import eu.tanov.epf.itemprovider.extension.ExtendedItemProvider;

public class DisciplinesExtendedItemProvider extends AbstractExtendedItemProvider implements ExtendedItemProvider {

	public DisciplinesExtendedItemProvider() {
		super(ModelStructure.DEFAULT.disciplineDefinitionPath, "_UI_Disciplines_group");
	}

	@Override
	protected ILibraryItemProvider createProvider(AdapterFactory adapterFactory, ContentPackage contentPkg, String name) {
		return new DisciplineCategoriesItemProvider(adapterFactory, contentPkg, name);
	}

}
