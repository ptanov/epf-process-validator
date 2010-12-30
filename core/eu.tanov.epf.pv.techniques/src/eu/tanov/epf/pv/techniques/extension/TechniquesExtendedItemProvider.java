package eu.tanov.epf.pv.techniques.extension;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.epf.library.edit.ILibraryItemProvider;
import org.eclipse.epf.uma.ContentPackage;

import eu.tanov.epf.itemprovider.extension.AbstractExtendedItemProvider;
import eu.tanov.epf.itemprovider.extension.ExtendedItemProvider;
import eu.tanov.epf.pv.techniques.provider.TechniquesCategoryItemProvider;

public class TechniquesExtendedItemProvider extends AbstractExtendedItemProvider implements ExtendedItemProvider {

	public TechniquesExtendedItemProvider() {
		super(TechniquesCategoryItemProvider.TECHNIQUES_PATH, TechniquesCategoryItemProvider.TECHNIQUES_NAME);
	}

	@Override
	protected ILibraryItemProvider createProvider(AdapterFactory adapterFactory, ContentPackage contentPkg, String name) {
		return new TechniquesCategoryItemProvider(adapterFactory, contentPkg, name);
	}

}
