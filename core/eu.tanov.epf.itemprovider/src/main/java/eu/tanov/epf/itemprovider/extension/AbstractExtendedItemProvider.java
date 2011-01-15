package eu.tanov.epf.itemprovider.extension;

import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.epf.library.edit.ILibraryItemProvider;
import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.Element;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.util.UmaUtil;

import eu.tanov.epf.itemprovider.util.MethodPluginHelper;

public abstract class AbstractExtendedItemProvider implements ExtendedItemProvider {
	protected final String[] definitionPath;

	public AbstractExtendedItemProvider(String[] definitionPath) {
		this.definitionPath = definitionPath;
	}

	@Override
	public void provide(Object object, List<ILibraryItemProvider> children,
			Map<String, ILibraryItemProvider> groupItemProviderMap, AdapterFactory adapterFactory) {
		final MethodPlugin plugin = UmaUtil.getMethodPlugin((Element) ((ItemProviderAdapter) object).getTarget());

		MethodPluginHelper.createContentPackages(plugin, definitionPath);

		final ContentPackage contentPkg = UmaUtil.findContentPackage(plugin, definitionPath);
		if (contentPkg != null) {
			final String name = getCategoryLocalizedName();
			final ILibraryItemProvider child = createProvider(adapterFactory, contentPkg, name);
			child.setParent(this);
			children.add(child);
			groupItemProviderMap.put(name, child);
		}
	}

	protected abstract ILibraryItemProvider createProvider(AdapterFactory adapterFactory, final ContentPackage contentPkg,
			final String name);

	protected abstract String getCategoryLocalizedName();
}
