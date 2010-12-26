package eu.tanov.epf.itemprovider.providers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.epf.library.edit.ILibraryItemProvider;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.category.StandardCategoriesItemProvider;
import org.eclipse.epf.library.edit.navigator.ContentItemProvider;
import org.eclipse.epf.library.edit.navigator.MethodPackagesItemProvider;
import org.eclipse.epf.library.edit.util.ModelStructure;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.util.UmaUtil;

public class ExtendedContentItemProvider extends ContentItemProvider {

	/**
	 * Creates a new instance.
	 */
	public ExtendedContentItemProvider(AdapterFactory adapterFactory,
			MethodPlugin plugin, ModelStructure modelStruct) {
		super(adapterFactory, plugin, modelStruct);
	}
	
	@Override
	public Collection getChildren(Object object) {
		if (children == null) {
			children = new ArrayList();
			groupItemProviderMap = new HashMap();
			String name;
			ILibraryItemProvider child;

			// find core content package
			ContentPackage contentPkg = UmaUtil.findContentPackage(plugin,
					modelStruct.coreContentPath);
			coreContentPkg = contentPkg;
			if (contentPkg != null) {
				name = LibraryEditPlugin.INSTANCE
						.getString("_UI_MethodContent_group"); //$NON-NLS-1$
				child = new MethodPackagesItemProvider(adapterFactory,
						contentPkg, name);
				child.setParent(this);
				children.add(child);
				groupItemProviderMap.put(name, child);

				// contentPkg = UmaUtil.findContentPackage(plugin,
				// modelStruct.disciplineDefinitionPath);
				// if(contentPkg != null) {
				// name =
				// LibraryEditPlugin.INSTANCE.getString("_UI_Disciplines_group");
				// child = new DisciplineCategoriesItemProvider(adapterFactory,
				// contentPkg, name);
				// child.setParent(this);
				// children.add(child);
				// groupItemProviderMap.put(name, child);
				// }
				//	        
				// contentPkg = UmaUtil.findContentPackage(plugin,
				// modelStruct.domainPath);
				// if(contentPkg != null) {
				// name =
				// LibraryEditPlugin.INSTANCE.getString("_UI_Domains_group");
				// child = new DomainsItemProvider(adapterFactory, contentPkg,
				// name);
				// child.setParent(this);
				// children.add(child);
				// groupItemProviderMap.put(name, child);
				// }
				//	
				// contentPkg = UmaUtil.findContentPackage(plugin,
				// modelStruct.workProductTypePath);
				// if(contentPkg != null) {
				// name =
				// LibraryEditPlugin.INSTANCE.getString("_UI_WorkProductTypes_group");
				// child = new WorkProductTypesItemProvider(adapterFactory,
				// contentPkg, name);
				// child.setParent(this);
				// children.add(child);
				// groupItemProviderMap.put(name, child);
				// }
				//	        
				// contentPkg = UmaUtil.findContentPackage(plugin,
				// modelStruct.roleSetPath);
				// if(contentPkg != null) {
				// name =
				// LibraryEditPlugin.INSTANCE.getString("_UI_Role_Sets_group");
				// child = new RoleSetsItemProvider(adapterFactory, contentPkg,
				// name);
				// child.setParent(this);
				// children.add(child);
				// groupItemProviderMap.put(name, child);
				// }
				//	        
				// contentPkg = UmaUtil.findContentPackage(plugin,
				// modelStruct.toolPath);
				// if(contentPkg != null) {
				// name =
				// LibraryEditPlugin.INSTANCE.getString("_UI_Tools_group");
				// child = new ToolsItemProvider(adapterFactory, contentPkg,
				// name);
				// child.setParent(this);
				// children.add(child);
				// groupItemProviderMap.put(name, child);
				// }

				// create the standard categories folder
				contentPkg = UmaUtil.findContentPackage(plugin,
						modelStruct.standardCategoryPath);
				if (contentPkg != null) {
					name = LibraryEditPlugin.INSTANCE
							.getString("_UI_Standard_Categories_group"); //$NON-NLS-1$
					child = new ExtendedStandardCategoriesItemProvider(adapterFactory,
							contentPkg, name);
					child.setParent(this);
					children.add(child);
					groupItemProviderMap.put(name, child);
				}

				// create the custome categories folder
				// contentPkg = UmaUtil.findContentPackage(plugin,
				// modelStruct.customCategoryPath);
				// if(contentPkg != null) {
				// name =
				// LibraryEditPlugin.INSTANCE.getString("_UI_Custom_Categories_group");
				// child = new CustomCategoriesItemProvider(adapterFactory,
				// contentPkg, name);
				// child.setParent(this);
				// children.add(child);
				// groupItemProviderMap.put(name, child);
				// }

				CustomCategory rootCustomCategory = TngUtil
						.getRootCustomCategory(plugin);
				if (rootCustomCategory != null) {
					boolean notify = rootCustomCategory.eDeliver();
					try {
						rootCustomCategory.eSetDeliver(false);
						rootCustomCategory.setName(LibraryEditPlugin.INSTANCE
								.getString("_UI_Custom_Categories_group")); //$NON-NLS-1$
					} finally {
						rootCustomCategory.eSetDeliver(notify);
					}
					children.add(rootCustomCategory);
				}

				// IConfigurable adapter = (IConfigurable)
				// TngUtil.getBestAdapterFactory(adapterFactory).adapt(contentPkg,
				// ITreeItemContentProvider.class);
				// adapter.setLabel(LibraryEditPlugin.INSTANCE.getString("_UI_MethodContent_group"));
				// children.add(contentPkg);
			}
		}

		return children;
	}
}
