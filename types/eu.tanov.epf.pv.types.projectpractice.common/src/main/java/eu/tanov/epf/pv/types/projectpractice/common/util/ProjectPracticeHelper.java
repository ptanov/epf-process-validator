package eu.tanov.epf.pv.types.projectpractice.common.util;

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
import eu.tanov.epf.pv.types.projectpractice.common.handler.ProjectPracticeCustomTypeHandler;

public class ProjectPracticeHelper {
	public static final String CATEGORY_NAME = "ProjectPractices"; //$NON-NLS-1$

	public static final String[] PROJECT_PRACTICES_PATH;
	static {
		PROJECT_PRACTICES_PATH = new String[ModelStructure.DEFAULT_DOMAIN_PATH.length];
		// -1, because last is used for Project Practice category
		System.arraycopy(ModelStructure.DEFAULT_DOMAIN_PATH, 0, PROJECT_PRACTICES_PATH, 0, PROJECT_PRACTICES_PATH.length - 1);
		PROJECT_PRACTICES_PATH[PROJECT_PRACTICES_PATH.length - 1] = ProjectPracticeHelper.CATEGORY_NAME;
	}

	/**
	 * helper
	 */
	private ProjectPracticeHelper() {
	}

	public static boolean isProjectPractice(Object o) {
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
	 * (#42) If task contains work products as mandatory input and task is selected in Project Practice - automatically add its
	 * work
	 * products (that are mandatory inputs) to Project Practice
	 * 
	 * Order of automatically added work products is not guaranteed (HashSet is used)
	 */
	public static void updateWorkProducts(CustomCategory projectPractice) {
		final HashSet<WorkProduct> directWorkProducts = new HashSet<WorkProduct>(
				TypeFilteredContentElementOrderList.<WorkProduct> toFilteredList(projectPractice,
						UmaPackage.eINSTANCE.getCustomCategory_CategorizedElements(), WorkProduct.class));

		final List<Task> tasks = TypeFilteredContentElementOrderList.<Task> toFilteredList(projectPractice,
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

		projectPractice.getCategorizedElements().addAll(workProductsToAdd);
	}

	public static EClass getCustomType() {
		return CustomTypeHelper.getCustomType(ProjectPracticeCustomTypeHandler.TYPE_NAME);
	}

	public static boolean isRegistered() {
		return CustomTypeHelper.isRegistered(ProjectPracticeCustomTypeHandler.TYPE_NAME);
	}

}
