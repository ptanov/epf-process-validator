package eu.tanov.epf.itemprovider.providers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.epf.library.edit.ILibraryItemProvider;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.category.DisciplineCategoriesItemProvider;
import org.eclipse.epf.library.edit.category.DomainsItemProvider;
import org.eclipse.epf.library.edit.category.RoleSetsItemProvider;
import org.eclipse.epf.library.edit.category.StandardCategoriesItemProvider;
import org.eclipse.epf.library.edit.category.ToolsItemProvider;
import org.eclipse.epf.library.edit.category.WorkProductTypesItemProvider;
import org.eclipse.epf.library.edit.util.ModelStructure;
import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.Element;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.util.UmaUtil;

public class ExtendedStandardCategoriesItemProvider extends StandardCategoriesItemProvider{
	private ArrayList children;

	private Map groupItemProviderMap;

	public ExtendedStandardCategoriesItemProvider(AdapterFactory adapterFactory,
			Notifier parent, String name) {
		super(adapterFactory, parent, name);
	}
	
	@Override
	public Collection getChildren(Object object) {
		if (children == null) {
			children = new ArrayList();
			groupItemProviderMap = new HashMap();
			String name;
			ILibraryItemProvider child;

			// create the disciplines categories folder
			MethodPlugin plugin = UmaUtil
					.getMethodPlugin((Element) ((ItemProviderAdapter) object)
							.getTarget());
			ContentPackage contentPkg = UmaUtil.findContentPackage(plugin,
					ModelStructure.DEFAULT.disciplineDefinitionPath);
			if (contentPkg != null) {
				name = LibraryEditPlugin.INSTANCE
						.getString("_UI_Disciplines_group"); //$NON-NLS-1$
				child = new DisciplineCategoriesItemProvider(adapterFactory,
						contentPkg, name);
				child.setParent(this);
				children.add(child);
				groupItemProviderMap.put(name, child);

			}

			contentPkg = UmaUtil.findContentPackage(plugin,
					ModelStructure.DEFAULT.domainPath);
			if (contentPkg != null) {
				name = "Discipline2"; //$NON-NLS-1$
				child = new TechniquesCategoryItemProvider(adapterFactory,
						contentPkg, name);
				child.setParent(this);
				children.add(child);
				groupItemProviderMap.put(name, child);
			}

			
			
			// create domain categories folder
			contentPkg = UmaUtil.findContentPackage(plugin,
					ModelStructure.DEFAULT.domainPath);
			if (contentPkg != null) {
				name = LibraryEditPlugin.INSTANCE
						.getString("_UI_Domains_group"); //$NON-NLS-1$
				child = new DomainsItemProvider(adapterFactory, contentPkg,
						name);
				child.setParent(this);
				children.add(child);
				groupItemProviderMap.put(name, child);
			}

			// create work product types folder
			contentPkg = UmaUtil.findContentPackage(plugin,
					ModelStructure.DEFAULT.workProductTypePath);
			if (contentPkg != null) {
				name = LibraryEditPlugin.INSTANCE
						.getString("_UI_WorkProductTypes_group"); //$NON-NLS-1$
				child = new WorkProductTypesItemProvider(adapterFactory,
						contentPkg, name);
				child.setParent(this);
				children.add(child);
				groupItemProviderMap.put(name, child);
			}

			// create role set folder
			contentPkg = UmaUtil.findContentPackage(plugin,
					ModelStructure.DEFAULT.roleSetPath);
			if (contentPkg != null) {
				name = LibraryEditPlugin.INSTANCE
						.getString("_UI_Role_Sets_group"); //$NON-NLS-1$
				child = new RoleSetsItemProvider(adapterFactory, contentPkg,
						name);
				child.setParent(this);
				children.add(child);
				groupItemProviderMap.put(name, child);
			}

			// create tool folder
			contentPkg = UmaUtil.findContentPackage(plugin,
					ModelStructure.DEFAULT.toolPath);
			if (contentPkg != null) {
				name = LibraryEditPlugin.INSTANCE.getString("_UI_Tools_group"); //$NON-NLS-1$
				child = new ToolsItemProvider(adapterFactory, contentPkg, name);
				child.setParent(this);
				children.add(child);
				groupItemProviderMap.put(name, child);
			}
		}
		return children;

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
