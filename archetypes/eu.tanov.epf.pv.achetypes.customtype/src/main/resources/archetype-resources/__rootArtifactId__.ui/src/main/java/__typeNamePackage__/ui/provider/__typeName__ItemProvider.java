package eu.tanov.epf.pv.types.${typeNamePackage}.ui.provider;

import java.util.Collection;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.epf.library.edit.IDefaultNameSetter;
import org.eclipse.epf.library.edit.ILibraryItemProvider;
import org.eclipse.epf.library.edit.IStatefulItemProvider;
import org.eclipse.epf.library.edit.internal.IListenerProvider;
import org.eclipse.swt.graphics.Image;

import eu.tanov.epf.itemprovider.extension.CustomItemProvider;
import eu.tanov.epf.pv.types.${typeNamePackage}.common.util.${typeName}Helper;
import eu.tanov.epf.pv.types.${typeNamePackage}.ui.${typeName}Activator;

public class ${typeName}ItemProvider extends org.eclipse.epf.library.edit.category.CustomCategoryItemProvider implements
		ILibraryItemProvider, IStatefulItemProvider, IDefaultNameSetter, IListenerProvider, CustomItemProvider {
	public ${typeName}ItemProvider() {
		this(null);
	}

	public ${typeName}ItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	@Override
	public Collection<?> getChildren(Object object) {
		// XXX Set the childrenStoreMap to null, to get the updated content, how to listen (add adapter) on custom category for changes?!
		childrenStoreMap = null;
		return super.getChildren(object);
	}

	@Override
	public Image getImage(Object object) {
		return get${typeName}Image();
	}

	public static Image get${typeName}Image() {
		return ${typeName}Activator.getDefault().getImage("full/obj16/${typeName}"); //$NON-NLS-1$
	}

	@Override
	public void collectNewChildDescriptors(@SuppressWarnings("rawtypes") Collection newChildDescriptors, Object object) {
		// no children
	}

	@Override
	public boolean matches(Object object) {
			return ${typeName}Helper.is${typeName}(object);
	}

	@Override
	public void setAdapterFactory(AdapterFactory adapterFactory) {
			if (adapterFactory == null) {
					throw new NullPointerException("Adapter factory should not be null");
			}
			if (this.adapterFactory!=null) {
					throw new IllegalStateException(String.format("Adapter factory already set: %s, new: %s", this.adapterFactory, adapterFactory));
			}
			this.adapterFactory = adapterFactory;
	}
}
