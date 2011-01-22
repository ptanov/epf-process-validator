package eu.tanov.epf.pv.types.technique.common.util;

import java.util.HashSet;
import java.util.List;

import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.Task;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.WorkProduct;

import eu.tanov.epf.pv.service.types.util.FilteredContentElementOrderList;

public class TechniqueHelper {
	public static final String CATEGORY_NAME = "Techniques"; //$NON-NLS-1$

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
	 * (#42) If task contains work products as mandatory input and task is selected in technique - automatically add its work
	 * products (that are mandatory inputs) to technique
	 * 
	 * Order of automatically added work products is not guaranteed (HashSet is used)
	 */
	public static void updateWorkProducts(CustomCategory technique) {
		final HashSet<WorkProduct> directWorkProducts = new HashSet<WorkProduct>(
				FilteredContentElementOrderList.<WorkProduct> toFilteredList(technique,
						UmaPackage.eINSTANCE.getCustomCategory_CategorizedElements(), WorkProduct.class));

		final List<Task> tasks = FilteredContentElementOrderList.<Task> toFilteredList(technique,
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
}
