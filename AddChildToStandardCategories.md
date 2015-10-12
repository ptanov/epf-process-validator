# Details #
There is no good way to replace only StandardCategoriesItemProvider, because it is instantiated with hardcoded class name and 'new', not with extension.
The solution is to contribute extension:
```
   <extension point="org.eclipse.epf.library.edit.adapterFactories">
      <factory
            class="eu.tanov.epf.pv.sample.AdapterFactory"
            id="library">
      </factory>
   </extension>
```
AdapterFactory should extend ItemProviderAdapterFactory and override:
```
	public Adapter createMethodPluginAdapter() {
		MethodPluginItemProvider adapter = new MethodPluginItemProvider(this);
		adapter.setModelStructure(ModelStructure.DEFAULT);
		// adapters.add(adapter);
		return adapter;
	}
```

Where MethodPluginItemProvider should extend original MethodPluginItemProvider and override
```
	public Collection getChildren(Object object) {
...
			Object child = new ContentItemProvider(adapterFactory, plugin,
```

Where ContentItemProvider should extend original ContentItemProvider and override
```
	public Collection getChildren(Object object) {
...
					child = new StandardCategoriesItemProvider(adapterFactory,
```
StandardCategoriesItemProvider should extend StandardCategoriesItemProvider and override

```
	public Collection getChildren(Object object) {
```
with code for new child.

# Content Package Path #
Every content package should have its own path. So before adding custom category - new path should be created for it as it is done in `org.eclipse.epf.library.util.ModelStorage.createContentPackages()`:

```
	private static ContentPackage createContentPackages(MethodPlugin model,
			String[] path) {
		List list = model.getMethodPackages();
		ContentPackage pkg = UmaUtil.findContentPackage(list, path[0]);
		if (pkg == null) {
			pkg = UmaFactory.eINSTANCE.createContentPackage();
			pkg.setName(path[0]);
			list.add(pkg);
		}
		for (int i = 1; i < path.length; i++) {
			list = pkg.getChildPackages();
			pkg = UmaUtil.findContentPackage(list, path[i]);
			if (pkg == null) {
				pkg = UmaFactory.eINSTANCE.createContentPackage();
				pkg.setName(path[i]);
				list.add(pkg);
			}
		}
		return pkg;
	}
```
because this method is private - it was copied in helper `eu.tanov.epf.itemprovider.util.MethodPluginHelper.createContentPackages()`.
After this every item provider, page definition, etc. should use its specific path.

# Implementation #
_TODO this is not ready yet_
Every mention class should not create appropriate child class with hardcoded name (as in example above), but use extension points instead (and default implementation).

## StandardCategoriesItemProvider ##
It defines extension point for adding children:
```
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
```

To add new child contribute extension:
```
   <extension
         point="eu.tanov.epf.itemprovider.StandardCategoriesItemProviders">
      <provider
            class="eu.tanov.epf.pv.techniques.extension.TechniquesExtendedItemProvider"
            id="techniques">
      </provider>
   </extension>
```

and implement provider:
```
public class TechniquesExtendedItemProvider implements ExtendedItemProvider {

	@Override
	public void provide(Object object, List<ILibraryItemProvider> children,
			Map<String, ILibraryItemProvider> groupItemProviderMap, AdapterFactory adapterFactory) {
		final MethodPlugin plugin = UmaUtil.getMethodPlugin((Element) ((ItemProviderAdapter) object).getTarget());
		MethodPluginHelper.createContentPackages(plugin, TechniquesCategoryItemProvider.TECHNIQUES_PATH);
		final ContentPackage contentPkg = UmaUtil.findContentPackage(plugin, TechniquesCategoryItemProvider.TECHNIQUES_PATH);
		if (contentPkg != null) {
			final String name = TechniquesCategoryItemProvider.TECHNIQUES_NAME;
			final ILibraryItemProvider child = new TechniquesCategoryItemProvider(adapterFactory, contentPkg, name);
			child.setParent(this);
			children.add(child);
			groupItemProviderMap.put(name, child);
		}
	}

}
```

For now no order or priority is supported.

Base class is added so previous example can be rewritten shortly:
```
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
```