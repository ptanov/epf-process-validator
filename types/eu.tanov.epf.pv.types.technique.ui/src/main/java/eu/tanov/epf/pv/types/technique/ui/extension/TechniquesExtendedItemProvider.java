package eu.tanov.epf.pv.types.technique.ui.extension;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.epf.library.edit.ILibraryItemProvider;
import org.eclipse.epf.uma.ContentPackage;

import eu.tanov.epf.itemprovider.extension.AbstractExtendedItemProvider;
import eu.tanov.epf.itemprovider.extension.ExtendedItemProvider;
import eu.tanov.epf.pv.types.technique.common.util.TechniqueHelper;
import eu.tanov.epf.pv.types.technique.ui.i18n.TechniqueUIResources;
import eu.tanov.epf.pv.types.technique.ui.provider.TechniquesCategoryItemProvider;

public class TechniquesExtendedItemProvider extends AbstractExtendedItemProvider implements ExtendedItemProvider {

	public TechniquesExtendedItemProvider() {
		super(TechniqueHelper.TECHNIQUES_PATH);
	}

	@Override
	protected ILibraryItemProvider createProvider(AdapterFactory adapterFactory, ContentPackage contentPkg, String name) {
		return new TechniquesCategoryItemProvider(adapterFactory, contentPkg, name);
	}

	@Override
	protected String getCategoryLocalizedName() {
		return TechniqueUIResources._UI_Techniques_group;
	}

	@Override
	public int position() {
		return 90;
	}
}
