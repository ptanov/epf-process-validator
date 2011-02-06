package eu.tanov.epf.pv.types.standard.ui.extension;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.epf.library.edit.ILibraryItemProvider;
import org.eclipse.epf.uma.ContentPackage;

import eu.tanov.epf.itemprovider.extension.AbstractExtendedItemProvider;
import eu.tanov.epf.itemprovider.extension.ExtendedItemProvider;
import eu.tanov.epf.pv.types.standard.common.util.StandardHelper;
import eu.tanov.epf.pv.types.standard.ui.i18n.StandardUIResources;
import eu.tanov.epf.pv.types.standard.ui.provider.StandardsCategoryItemProvider;

public class StandardsExtendedItemProvider extends AbstractExtendedItemProvider implements ExtendedItemProvider {

	public StandardsExtendedItemProvider() {
		super(StandardHelper.STANDARDS_PATH);
	}

	@Override
	protected ILibraryItemProvider createProvider(AdapterFactory adapterFactory, ContentPackage contentPkg, String name) {
		return new StandardsCategoryItemProvider(adapterFactory, contentPkg, name);
	}

	@Override
	protected String getCategoryLocalizedName() {
		return StandardUIResources._UI_Standards_group;
	}

	@Override
	public int position() {
		return 70;
	}
}
