package eu.tanov.epf.pv.ui.techniques.util;

import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.CustomCategory;

import eu.tanov.epf.pv.ui.techniques.provider.TechniquesCategoryItemProvider;

public class TechniquesHelper {
	/**
	 * helper
	 */
	private TechniquesHelper() {
	}
	
	public static boolean isTechnique(Object o) {
		if (!(o instanceof CustomCategory)) {
			return false;
		}
		final CustomCategory customCategory = (CustomCategory) o;

		if (!(customCategory.eContainer() instanceof ContentPackage)) {
			return false;
		}
		final ContentPackage contentPackage = (ContentPackage)customCategory.eContainer();
		
		return TechniquesCategoryItemProvider.TECHNIQUES_NAME.equals(contentPackage.getName());
	}
}
