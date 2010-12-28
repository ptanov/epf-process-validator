package eu.tanov.epf.itemprovider.util;

import java.util.List;

import org.eclipse.epf.library.edit.util.ModelStructure;
import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.MethodPackage;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.util.UmaUtil;

public class MethodPluginHelper {
	//TODO move to separate plugin
	public static final String[] TECHNIQUES_PATH;
	static {
		TECHNIQUES_PATH = new String[ModelStructure.DEFAULT_DOMAIN_PATH.length];
		// -1, because last is used for Techniques
		System.arraycopy(ModelStructure.DEFAULT_DOMAIN_PATH, 0, TECHNIQUES_PATH, 0, TECHNIQUES_PATH.length - 1);
		TECHNIQUES_PATH[TECHNIQUES_PATH.length - 1] = "Techniques"; //$NON-NLS-1$
	}
	/**
	 * helper
	 */
	private MethodPluginHelper() {
	}
	

	/**
	 * Copied from ModelStorage.createContentPackages(MethodPlugin, String[])
	 * 
	 * @param path
	 * @param emptyModel
	 */
	public static ContentPackage createContentPackages(MethodPlugin model,
			String[] path) {
		List<MethodPackage> list = model.getMethodPackages();
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

}
