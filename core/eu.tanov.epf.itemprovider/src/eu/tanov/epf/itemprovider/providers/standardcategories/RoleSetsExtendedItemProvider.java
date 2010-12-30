package eu.tanov.epf.itemprovider.providers.standardcategories;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.epf.library.edit.ILibraryItemProvider;
import org.eclipse.epf.library.edit.category.RoleSetsItemProvider;
import org.eclipse.epf.library.edit.util.ModelStructure;
import org.eclipse.epf.uma.ContentPackage;

import eu.tanov.epf.itemprovider.extension.AbstractExtendedItemProvider;
import eu.tanov.epf.itemprovider.extension.ExtendedItemProvider;

public class RoleSetsExtendedItemProvider extends AbstractExtendedItemProvider implements ExtendedItemProvider {

	public RoleSetsExtendedItemProvider() {
		super(ModelStructure.DEFAULT.roleSetPath, "_UI_Role_Sets_group");
	}

	@Override
	protected ILibraryItemProvider createProvider(AdapterFactory adapterFactory, ContentPackage contentPkg, String name) {
		return new RoleSetsItemProvider(adapterFactory, contentPkg, name);
	}

}
