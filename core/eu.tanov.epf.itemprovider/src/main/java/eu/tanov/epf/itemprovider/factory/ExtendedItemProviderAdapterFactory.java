package eu.tanov.epf.itemprovider.factory;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.epf.library.edit.category.CustomCategoryItemProvider;
import org.eclipse.epf.library.edit.navigator.ItemProviderAdapterFactory;
import org.eclipse.epf.library.edit.navigator.MethodPluginItemProvider;
import org.eclipse.epf.library.edit.util.ModelStructure;

import eu.tanov.epf.itemprovider.providers.ExtendedCustomCategoryItemProvider;
import eu.tanov.epf.itemprovider.providers.ExtendedMethodPluginItemProvider;

public class ExtendedItemProviderAdapterFactory extends ItemProviderAdapterFactory {
	/**
	 * TODO move to common space or get from somewhere else?
	 */
	public static final String PLUGIN_ID = "eu.tanov.epf.itemprovider";
	
	@Override
	public Adapter createMethodPluginAdapter() {
		MethodPluginItemProvider adapter = new ExtendedMethodPluginItemProvider(this);
		adapter.setModelStructure(ModelStructure.DEFAULT);
		// adapters.add(adapter);
		return adapter;
	}

	// TODO for other create***Adapter(), too:
	@Override
	public Adapter createCustomCategoryAdapter() {
		if (customCategoryItemProvider == null) {
			customCategoryItemProvider = new ExtendedCustomCategoryItemProvider(this);
		}

		return customCategoryItemProvider;
	}
}
