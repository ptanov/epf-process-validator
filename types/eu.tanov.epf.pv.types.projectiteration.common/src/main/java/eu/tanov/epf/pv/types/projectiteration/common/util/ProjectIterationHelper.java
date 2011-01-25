package eu.tanov.epf.pv.types.projectiteration.common.util;

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
import eu.tanov.epf.pv.types.projectiteration.common.handler.ProjectIterationCustomTypeHandler;

public class ProjectIterationHelper {
	public static final String CATEGORY_NAME = "ProjectIterations"; //$NON-NLS-1$

	public static final String[] PROJECT_ITERATIONS_PATH;
	static {
		PROJECT_ITERATIONS_PATH = new String[ModelStructure.DEFAULT_DOMAIN_PATH.length];
		// -1, because last is used for Project Iteration category
		System.arraycopy(ModelStructure.DEFAULT_DOMAIN_PATH, 0, PROJECT_ITERATIONS_PATH, 0, PROJECT_ITERATIONS_PATH.length - 1);
		PROJECT_ITERATIONS_PATH[PROJECT_ITERATIONS_PATH.length - 1] = ProjectIterationHelper.CATEGORY_NAME;
	}

	/**
	 * helper
	 */
	private ProjectIterationHelper() {
	}

	public static boolean isProjectIteration(Object o) {
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
	 * (#42) If task contains work products as mandatory input and task is selected in Project Iteration - automatically add its work
	 * products (that are mandatory inputs) to Project Iteration
	 * 
	 * Order of automatically added work products is not guaranteed (HashSet is used)
	 */
	public static void updateWorkProducts(CustomCategory projectIteration) {
		final HashSet<WorkProduct> directWorkProducts = new HashSet<WorkProduct>(
				TypeFilteredContentElementOrderList.<WorkProduct> toFilteredList(projectIteration,
						UmaPackage.eINSTANCE.getCustomCategory_CategorizedElements(), WorkProduct.class));

		final List<Task> tasks = TypeFilteredContentElementOrderList.<Task> toFilteredList(projectIteration,
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

		projectIteration.getCategorizedElements().addAll(workProductsToAdd);
	}

	public static EClass getCustomType() {
		return CustomTypeHelper.getCustomType(ProjectIterationCustomTypeHandler.TYPE_NAME);
	}

	public static boolean isRegistered() {
		return CustomTypeHelper.isRegistered(ProjectIterationCustomTypeHandler.TYPE_NAME);
	}

}
