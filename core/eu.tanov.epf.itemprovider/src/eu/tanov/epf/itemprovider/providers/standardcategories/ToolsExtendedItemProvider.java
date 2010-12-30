package eu.tanov.epf.itemprovider.providers.standardcategories;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.epf.library.edit.ILibraryItemProvider;
import org.eclipse.epf.library.edit.category.ToolsItemProvider;
import org.eclipse.epf.library.edit.util.ModelStructure;
import org.eclipse.epf.uma.ContentPackage;

import eu.tanov.epf.itemprovider.extension.AbstractExtendedItemProvider;
import eu.tanov.epf.itemprovider.extension.ExtendedItemProvider;

public class ToolsExtendedItemProvider extends AbstractExtendedItemProvider implements ExtendedItemProvider {

	public ToolsExtendedItemProvider() {
		super(ModelStructure.DEFAULT.toolPath, "_UI_Tools_group");
	}

	@Override
	protected ILibraryItemProvider createProvider(AdapterFactory adapterFactory, ContentPackage contentPkg, String name) {
		return new ToolsItemProvider(adapterFactory, contentPkg, name);
	}

}
