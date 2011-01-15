package eu.tanov.epf.itemprovider.providers;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.navigator.MethodPluginItemProvider;
import org.eclipse.epf.library.edit.navigator.ProcessesItemProvider;
import org.eclipse.epf.uma.MethodPlugin;
public class ExtendedMethodPluginItemProvider extends MethodPluginItemProvider{
	public ExtendedMethodPluginItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	@Override
	public Collection getChildren(Object object) {
		if (children == null) {
			MethodPlugin plugin = (MethodPlugin) object;

			children = new ArrayList();
			groupItemProviderMap = new HashMap();

			String name = LibraryEditPlugin.INSTANCE
					.getString("_UI_Content_group"); //$NON-NLS-1$
			Object child = new ExtendedContentItemProvider(adapterFactory, plugin,
					getModelStructure());
			children.add(child);
			groupItemProviderMap.put(name, child);

			name = LibraryEditPlugin.INSTANCE.getString("_UI_Processes_group"); //$NON-NLS-1$
			child = new ProcessesItemProvider(adapterFactory, plugin,
					getModelStructure());
			children.add(child);
			groupItemProviderMap.put(name, child);
		}

		return children;
	}
}
