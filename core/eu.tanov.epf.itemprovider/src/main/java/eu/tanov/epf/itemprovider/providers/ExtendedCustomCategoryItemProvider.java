package eu.tanov.epf.itemprovider.providers;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.epf.library.edit.util.ExtensionManager;

import eu.tanov.epf.itemprovider.extension.CustomItemProvider;
import eu.tanov.epf.itemprovider.factory.ExtendedItemProviderAdapterFactory;

public class ExtendedCustomCategoryItemProvider extends org.eclipse.epf.library.edit.category.CustomCategoryItemProvider {
	private static final String EXTENSION_POINT_NAME = "CustomCategoriesItemProviders";

	/**
	 * //XXX made static?! for optimization - do not get extension points every
	 * time
	 */
	private List<CustomItemProvider> allProviders;
	
	public ExtendedCustomCategoryItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	// TODO implement all methods like getChildren() and getImage() are
	// implemented here
	@Override
	public Collection<?> getChildren(Object object) {
		final CustomItemProvider handler = getItemProviderForObject(object);
		if (handler == null) {
			// there is no custom custom category item provider
			return super.getChildren(object);
		} else {
			// use custom custom category item provider
			return handler.getChildren(object);
		}
	}

	@Override
	public Object getImage(Object object) {
		final CustomItemProvider handler = getItemProviderForObject(object);
		if (handler == null) {
			// there is no custom custom category item provider
			return super.getImage(object);
		} else {
			// use custom custom category item provider
			return handler.getImage(object);
		}
	}

	@Override
	protected void collectNewChildDescriptors(@SuppressWarnings("rawtypes") Collection newChildDescriptors, Object object) {
		final CustomItemProvider handler = getItemProviderForObject(object);
		if (handler == null) {
			// there is no custom custom category item provider
			super.collectNewChildDescriptors(newChildDescriptors, object);
		} else {
			// use custom custom category item provider
			@SuppressWarnings("unchecked")
			final Collection<Object> uncheckedCast = newChildDescriptors;
			handler.collectNewChildDescriptors(uncheckedCast, object);
		}
	}
	private CustomItemProvider getItemProviderForObject(Object object) throws IllegalStateException {
		CustomItemProvider result = null;
		for (CustomItemProvider itemProvider : getAllExtensions()) {
			if (itemProvider.matches(object)) {
				if (result != null) {
					throw new IllegalStateException(String.format("Two itemProviders found for object: %s, first: %s, second: %s",
							object, result, itemProvider));
				}
				result = itemProvider;
			}
		}
		return result;
	}

	private List<CustomItemProvider> getAllExtensions() {
		if (allProviders == null) {
			allProviders = ExtensionManager.getExtensions(ExtendedItemProviderAdapterFactory.PLUGIN_ID, EXTENSION_POINT_NAME, CustomItemProvider.class);
			for (CustomItemProvider itemProvider : allProviders) {
				itemProvider.setAdapterFactory(adapterFactory);
			}
		}
		return allProviders;
	}
}
