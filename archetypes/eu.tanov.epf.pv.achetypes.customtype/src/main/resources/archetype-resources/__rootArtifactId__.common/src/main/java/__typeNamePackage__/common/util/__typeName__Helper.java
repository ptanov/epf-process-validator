package eu.tanov.epf.pv.types.${typeNamePackage}.common.util;

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
import eu.tanov.epf.pv.types.${typeNamePackage}.common.handler.${typeName}CustomTypeHandler;

public class ${typeName}Helper {
	public static final String CATEGORY_NAME = "${typeNamePlural}"; //$NON-NLS-1$

	public static final String[] ${typeNamePluralConst}_PATH;
	static {
		${typeNamePluralConst}_PATH = new String[ModelStructure.DEFAULT_DOMAIN_PATH.length];
		// -1, because last is used for ${typeNameString} category
		System.arraycopy(ModelStructure.DEFAULT_DOMAIN_PATH, 0, ${typeNamePluralConst}_PATH, 0, ${typeNamePluralConst}_PATH.length - 1);
		${typeNamePluralConst}_PATH[${typeNamePluralConst}_PATH.length - 1] = ${typeName}Helper.CATEGORY_NAME;
	}

	/**
	 * helper
	 */
	private ${typeName}Helper() {
	}

	public static boolean is${typeName}(Object o) {
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
	 * (#42) If task contains work products as mandatory input and task is selected in ${typeNameString} - automatically add its work
	 * products (that are mandatory inputs) to ${typeNameString}
	 * 
	 * Order of automatically added work products is not guaranteed (HashSet is used)
	 */
	public static void updateWorkProducts(CustomCategory ${typeNameVariable}) {
		final HashSet<WorkProduct> directWorkProducts = new HashSet<WorkProduct>(
				TypeFilteredContentElementOrderList.<WorkProduct> toFilteredList(${typeNameVariable},
						UmaPackage.eINSTANCE.getCustomCategory_CategorizedElements(), WorkProduct.class));

		final List<Task> tasks = TypeFilteredContentElementOrderList.<Task> toFilteredList(${typeNameVariable},
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

		${typeNameVariable}.getCategorizedElements().addAll(workProductsToAdd);
	}

	public static EClass getCustomType() {
		return CustomTypeHelper.getCustomType(${typeName}CustomTypeHandler.TYPE_NAME);
	}

}
