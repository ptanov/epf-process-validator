package eu.tanov.epf.itemprovider.factory;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.epf.library.edit.navigator.ItemProviderAdapterFactory;
import org.eclipse.epf.library.edit.navigator.MethodPluginItemProvider;
import org.eclipse.epf.library.edit.util.ModelStructure;

import eu.tanov.epf.itemprovider.providers.ExtendedMethodPluginItemProvider;

public class ExtendedItemProviderAdapterFactory extends ItemProviderAdapterFactory {

	@Override
	public Adapter createMethodPluginAdapter() {
		MethodPluginItemProvider adapter = new ExtendedMethodPluginItemProvider(this);
		adapter.setModelStructure(ModelStructure.DEFAULT);
		// adapters.add(adapter);
		return adapter;
	}
}
