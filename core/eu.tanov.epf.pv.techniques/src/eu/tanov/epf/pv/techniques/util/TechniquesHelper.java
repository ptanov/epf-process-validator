package eu.tanov.epf.pv.techniques.util;

import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.CustomCategory;

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
		
		//TODO make constant when Extension points in categories item provider is used
		return "Techniques".equals(contentPackage.getName());
	}
}
