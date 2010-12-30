package eu.tanov.epf.itemprovider.providers.standardcategories;

import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.epf.library.edit.ILibraryItemProvider;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.category.DisciplineCategoriesItemProvider;
import org.eclipse.epf.library.edit.util.ModelStructure;
import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.Element;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.util.UmaUtil;

import eu.tanov.epf.itemprovider.extension.ExtendedItemProvider;

public class DisciplinesExtendedItemProvider implements ExtendedItemProvider {

	@Override
	public void provide(Object object, List<ILibraryItemProvider> children,
			Map<String, ILibraryItemProvider> groupItemProviderMap, AdapterFactory adapterFactory) {
		final MethodPlugin plugin = UmaUtil.getMethodPlugin((Element) ((ItemProviderAdapter) object).getTarget());
		// create the disciplines categories folder
		final ContentPackage contentPkg = UmaUtil.findContentPackage(plugin, ModelStructure.DEFAULT.disciplineDefinitionPath);
		if (contentPkg != null) {
			final String name = LibraryEditPlugin.INSTANCE.getString("_UI_Disciplines_group"); //$NON-NLS-1$
			final ILibraryItemProvider child = new DisciplineCategoriesItemProvider(adapterFactory, contentPkg, name);
			child.setParent(this);
			children.add(child);
			groupItemProviderMap.put(name, child);
		}
	}

}
