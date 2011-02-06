package eu.tanov.epf.itemprovider.providers.standardcategories;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.epf.library.edit.ILibraryItemProvider;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.category.ToolsItemProvider;
import org.eclipse.epf.library.edit.util.ModelStructure;
import org.eclipse.epf.uma.ContentPackage;

import eu.tanov.epf.itemprovider.extension.AbstractExtendedItemProvider;
import eu.tanov.epf.itemprovider.extension.ExtendedItemProvider;

public class ToolsExtendedItemProvider extends AbstractExtendedItemProvider implements ExtendedItemProvider {

	public ToolsExtendedItemProvider() {
		super(ModelStructure.DEFAULT.toolPath);
	}

	@Override
	protected ILibraryItemProvider createProvider(AdapterFactory adapterFactory, ContentPackage contentPkg, String name) {
		return new ToolsItemProvider(adapterFactory, contentPkg, name);
	}

	@Override
	protected String getCategoryLocalizedName() {
		return LibraryEditPlugin.INSTANCE.getString("_UI_Tools_group");
	}

	@Override
	public int position() {
		return 50;
	}
}
