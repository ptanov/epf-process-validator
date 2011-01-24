package eu.tanov.epf.pv.types.standard.common.util;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.epf.library.edit.util.ModelStructure;
import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.CustomCategory;

import eu.tanov.epf.pv.service.types.util.CustomTypeHelper;
import eu.tanov.epf.pv.types.standard.common.handler.StandardCustomTypeHandler;

public class StandardHelper {
	public static final String CATEGORY_NAME = "Standards"; //$NON-NLS-1$

	public static final String[] STANDARDS_PATH;
	static {
		STANDARDS_PATH = new String[ModelStructure.DEFAULT_DOMAIN_PATH.length];
		// -1, because last is used for Standard category
		System.arraycopy(ModelStructure.DEFAULT_DOMAIN_PATH, 0, STANDARDS_PATH, 0, STANDARDS_PATH.length - 1);
		STANDARDS_PATH[STANDARDS_PATH.length - 1] = StandardHelper.CATEGORY_NAME;
	}

	/**
	 * helper
	 */
	private StandardHelper() {
	}

	public static boolean isStandard(Object o) {
		if (!(o instanceof CustomCategory)) {
			return false;
		}
		final CustomCategory customCategory = (CustomCategory) o;

		if (!(customCategory.eContainer() instanceof ContentPackage)) {
			return false;
		}
		final ContentPackage contentPackage = (ContentPackage) customCategory.eContainer();

		return CATEGORY_NAME.equals(contentPackage.getName());
	}

	public static EClass getCustomType() {
		return CustomTypeHelper.getCustomType(StandardCustomTypeHandler.TYPE_NAME);
	}

	public static boolean isRegistered() {
		return CustomTypeHelper.isRegistered(StandardCustomTypeHandler.TYPE_NAME);
	}

}
