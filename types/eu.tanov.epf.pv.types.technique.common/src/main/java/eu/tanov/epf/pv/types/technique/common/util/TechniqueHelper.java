package eu.tanov.epf.pv.types.technique.common.util;

import java.util.HashSet;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.epf.library.edit.util.ModelStructure;
import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.Task;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.WorkProduct;

import eu.tanov.epf.pv.service.types.util.CustomTypeHelper;
import eu.tanov.epf.pv.service.types.util.TypeFilteredContentElementOrderList;
import eu.tanov.epf.pv.types.technique.common.handler.TechniqueCustomTypeHandler;

public class TechniqueHelper {
	public static final String CATEGORY_NAME = "Techniques"; //$NON-NLS-1$

	public static final String[] TECHNIQUES_PATH;
	static {
		TECHNIQUES_PATH = new String[ModelStructure.DEFAULT_DOMAIN_PATH.length];
		// -1, because last is used for Technique category
		System.arraycopy(ModelStructure.DEFAULT_DOMAIN_PATH, 0, TECHNIQUES_PATH, 0, TECHNIQUES_PATH.length - 1);
		TECHNIQUES_PATH[TECHNIQUES_PATH.length - 1] = TechniqueHelper.CATEGORY_NAME;
	}

	/**
	 * helper
	 */
	private TechniqueHelper() {
	}

	public static boolean isTechnique(Object o) {
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

	/**
	 * (#42) If task contains work products as mandatory input and task is selected in Technique - automatically add its work
	 * products (that are mandatory inputs) to Technique
	 * 
	 * Order of automatically added work products is not guaranteed (HashSet is used)
	 */
	public static void updateWorkProducts(CustomCategory technique) {
		final HashSet<WorkProduct> directWorkProducts = new HashSet<WorkProduct>(
				TypeFilteredContentElementOrderList.<WorkProduct> toFilteredList(technique,
						UmaPackage.eINSTANCE.getCustomCategory_CategorizedElements(), WorkProduct.class));

		final List<Task> tasks = TypeFilteredContentElementOrderList.<Task> toFilteredList(technique,
				UmaPackage.eINSTANCE.getCustomCategory_CategorizedElements(), Task.class);

		// set in order to avoid adding some work product twice
		final HashSet<WorkProduct> workProductsToAdd = new HashSet<WorkProduct>();
		for (Task task : tasks) {
			final List<WorkProduct> mandatoryWorkProducts = task.getMandatoryInput();
			for (WorkProduct workProduct : mandatoryWorkProducts) {
				if (!directWorkProducts.contains(workProduct)) {
					workProductsToAdd.add(workProduct);
				}
			}
		}

		technique.getCategorizedElements().addAll(workProductsToAdd);
	}

	public static EClass getCustomType() {
		return CustomTypeHelper.getCustomType(TechniqueCustomTypeHandler.TYPE_NAME);
	}

	public static boolean isRegistered() {
		return CustomTypeHelper.isRegistered(TechniqueCustomTypeHandler.TYPE_NAME);
	}

}
