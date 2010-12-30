package eu.tanov.epf.itemprovider.providers.standardcategories;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.epf.library.edit.ILibraryItemProvider;
import org.eclipse.epf.library.edit.category.WorkProductTypesItemProvider;
import org.eclipse.epf.library.edit.util.ModelStructure;
import org.eclipse.epf.uma.ContentPackage;

import eu.tanov.epf.itemprovider.extension.AbstractExtendedItemProvider;
import eu.tanov.epf.itemprovider.extension.ExtendedItemProvider;

public class WorkProductsExtendedItemProvider extends AbstractExtendedItemProvider implements ExtendedItemProvider {

	public WorkProductsExtendedItemProvider() {
		super(ModelStructure.DEFAULT.workProductTypePath, "_UI_WorkProductTypes_group");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected ILibraryItemProvider createProvider(AdapterFactory adapterFactory, ContentPackage contentPkg, String name) {
		return new WorkProductTypesItemProvider(adapterFactory, contentPkg, name);
	}

}
