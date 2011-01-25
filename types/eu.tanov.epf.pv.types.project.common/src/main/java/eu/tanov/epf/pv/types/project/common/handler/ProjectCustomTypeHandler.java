package eu.tanov.epf.pv.types.project.common.handler;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.Task;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.WorkProduct;

import eu.tanov.epf.pv.service.types.handler.CustomTypeHandler;
import eu.tanov.epf.pv.service.types.util.CustomTypeHelper;
import eu.tanov.epf.pv.service.types.util.UmaTypeSettingDelegateFactory;
import eu.tanov.epf.pv.types.project.common.util.ProjectHelper;

public class ProjectCustomTypeHandler implements CustomTypeHandler<CustomCategory> {

	private static final String STRUCTURAL_FEATURE_NAME_TASKS = "tasks";
	private static final String STRUCTURAL_FEATURE_NAME_WORK_PRODUCTS = "workProducts";
	/**
	 * XXX if used outside - move to ProjectHelper
	 */
	public static final String TYPE_NAME = "Project";

	private final EClass projectEClass;
	private EReference tasks;
	private EReference workProducts;

	public ProjectCustomTypeHandler() {
		this.projectEClass = CustomTypeHelper.createType(TYPE_NAME);
	}

	/*
	 * based on http://www.ibm.com/developerworks/library/os-eclipse-dynamicemf/
	 */
	@Override
	public void registerType() {
		this.tasks = CustomTypeHelper.createStructuralFeatureList(projectEClass, STRUCTURAL_FEATURE_NAME_TASKS,
				UmaPackage.eINSTANCE.getTask(), new UmaTypeSettingDelegateFactory<Task>(Task.class));
		this.workProducts = CustomTypeHelper.createStructuralFeatureList(projectEClass, STRUCTURAL_FEATURE_NAME_WORK_PRODUCTS,
				UmaPackage.eINSTANCE.getWorkProduct(), new UmaTypeSettingDelegateFactory<WorkProduct>(WorkProduct.class));

		projectEClass.getEStructuralFeatures().add(tasks);
		projectEClass.getEStructuralFeatures().add(workProducts);

		CustomTypeHelper.getExtendedUmaPackage().getEClassifiers().add(projectEClass);
	}

	@Override
	public EObject wrap(EObject object) {
		return CustomTypeHelper.copy(object, projectEClass);
	}

	@Override
	public boolean matches(EObject object) {
		return ProjectHelper.isProject(object);
	}

	@Override
	public EClass getCustomType() {
		return projectEClass;
	}

	@Override
	public Class<CustomCategory> getHolderType() {
		return CustomCategory.class;
	}

	@Override
	public String[] getCategoryPkgPath() {
		return ProjectHelper.PROJECTS_PATH;
	}

	@Override
	public boolean isReadyToRegisterType() {
		// not dependent to any type
		return true;
	}

}
