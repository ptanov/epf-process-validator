package eu.tanov.epf.pv.ui.techniques.extension;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.epf.library.edit.ILibraryItemProvider;
import org.eclipse.epf.uma.ContentPackage;

import eu.tanov.epf.itemprovider.extension.AbstractExtendedItemProvider;
import eu.tanov.epf.itemprovider.extension.ExtendedItemProvider;
import eu.tanov.epf.pv.ui.techniques.i18n.TechniquesUIResources;
import eu.tanov.epf.pv.ui.techniques.provider.TechniquesCategoryItemProvider;

public class TechniquesExtendedItemProvider extends AbstractExtendedItemProvider implements ExtendedItemProvider {

	public TechniquesExtendedItemProvider() {
		super(TechniquesCategoryItemProvider.TECHNIQUES_PATH);
	}

	@Override
	protected ILibraryItemProvider createProvider(AdapterFactory adapterFactory, ContentPackage contentPkg, String name) {
		return new TechniquesCategoryItemProvider(adapterFactory, contentPkg, name);
	}
	
	@Override
	protected String getCategoryLocalizedName() {
		return TechniquesUIResources._UI_Techniques_group;
	}

}
