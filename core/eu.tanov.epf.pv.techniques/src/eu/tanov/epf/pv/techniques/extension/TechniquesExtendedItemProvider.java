package eu.tanov.epf.pv.techniques.extension;

import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.epf.library.edit.ILibraryItemProvider;
import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.Element;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.util.UmaUtil;

import eu.tanov.epf.itemprovider.extension.ExtendedItemProvider;
import eu.tanov.epf.itemprovider.util.MethodPluginHelper;
import eu.tanov.epf.pv.techniques.provider.TechniquesCategoryItemProvider;

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
