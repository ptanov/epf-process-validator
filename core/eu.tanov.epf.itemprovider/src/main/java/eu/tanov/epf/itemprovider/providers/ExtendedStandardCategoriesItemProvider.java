package eu.tanov.epf.itemprovider.providers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.epf.library.edit.ILibraryItemProvider;
import org.eclipse.epf.library.edit.category.StandardCategoriesItemProvider;
import org.eclipse.epf.library.edit.util.ExtensionManager;

import eu.tanov.epf.itemprovider.extension.ExtendedItemProvider;

public class ExtendedStandardCategoriesItemProvider extends StandardCategoriesItemProvider {
	private static final String EXTENSION_POINT_NAME = "StandardCategoriesItemProviders";
	/**
	 * TODO move to common space or get from somewhere else?
	 */
	private static final String PLUGIN_ID = "eu.tanov.epf.itemprovider";
	
	private static class ContributionsComparator implements Comparator<ExtendedItemProvider> {
		@Override
		public int compare(ExtendedItemProvider arg0, ExtendedItemProvider arg1) {
			return arg0.position() - arg1.position();
		}
	}
	
	private static final ContributionsComparator comparator = new ContributionsComparator();

	private ArrayList<ILibraryItemProvider> children;

	private HashMap<String, ILibraryItemProvider> groupItemProviderMap;
	private List<ExtendedItemProvider> allProviders;

	public ExtendedStandardCategoriesItemProvider(AdapterFactory adapterFactory,
			Notifier parent, String name) {
		super(adapterFactory, parent, name);
	}
	
	@Override
	public Collection<ILibraryItemProvider> getChildren(Object object) {
		if (children == null) {
			children = new ArrayList<ILibraryItemProvider>();
			groupItemProviderMap = new HashMap<String, ILibraryItemProvider>();
			
			final List<ExtendedItemProvider> extensions = getAllExtensions();
			for (ExtendedItemProvider extendedItemProvider : extensions) {
				extendedItemProvider.provide(object, children, groupItemProviderMap, adapterFactory);
			}
		}
		return children;

	}
	private List<ExtendedItemProvider> getAllExtensions() {
		if (allProviders == null) {
			allProviders = ExtensionManager.getExtensions(PLUGIN_ID, EXTENSION_POINT_NAME, ExtendedItemProvider.class);
			//compare contributions
			Collections.sort(allProviders, comparator);
		}
		return allProviders;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.IGroupContainer#getGroupItemProvider(java.lang.String)
	 */
	@Override
	public Object getGroupItemProvider(String name) {
		return groupItemProviderMap.get(name);
	}
}
