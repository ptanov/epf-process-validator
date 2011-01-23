package eu.tanov.epf.pv.types.${typeNamePackage}.ui.extension;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.epf.library.edit.ILibraryItemProvider;
import org.eclipse.epf.uma.ContentPackage;

import eu.tanov.epf.itemprovider.extension.AbstractExtendedItemProvider;
import eu.tanov.epf.itemprovider.extension.ExtendedItemProvider;
import eu.tanov.epf.pv.types.${typeNamePackage}.common.util.${typeName}Helper;
import eu.tanov.epf.pv.types.${typeNamePackage}.ui.i18n.${typeName}UIResources;
import eu.tanov.epf.pv.types.${typeNamePackage}.ui.provider.${typeNamePlural}CategoryItemProvider;

public class ${typeNamePlural}ExtendedItemProvider extends AbstractExtendedItemProvider implements ExtendedItemProvider {

	public ${typeNamePlural}ExtendedItemProvider() {
		super(${typeName}Helper.${typeNamePluralConst}_PATH);
	}

	@Override
	protected ILibraryItemProvider createProvider(AdapterFactory adapterFactory, ContentPackage contentPkg, String name) {
		return new ${typeNamePlural}CategoryItemProvider(adapterFactory, contentPkg, name);
	}

	@Override
	protected String getCategoryLocalizedName() {
		return ${typeName}UIResources._UI_${typeNamePlural}_group;
	}

}
