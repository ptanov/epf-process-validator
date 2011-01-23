package eu.tanov.epf.pv.types.projectpractice.common.handler;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.UmaPackage;

import eu.tanov.epf.pv.service.types.handler.CustomTypeHandler;
import eu.tanov.epf.pv.service.types.util.CustomTypeHelper;
import eu.tanov.epf.pv.types.projectpractice.common.util.ProjectPracticeHelper;

public class ProjectPracticeCustomTypeHandler implements CustomTypeHandler<CustomCategory> {

	private static final String STRUCTURAL_FEATURE_NAME_TASKS = "tasks";
	private static final String STRUCTURAL_FEATURE_NAME_WORK_PRODUCTS = "workProducts";
	private static final String TYPE_NAME = "ProjectPractice";

	private final EClass projectPracticeEClass;
	private EReference tasks;
	private EReference workProducts;

	public ProjectPracticeCustomTypeHandler() {
		this.projectPracticeEClass = CustomTypeHelper.createType(TYPE_NAME);
	}

	/*
	 * based on http://www.ibm.com/developerworks/library/os-eclipse-dynamicemf/
	 */
	@Override
	public void registerType() {
		this.tasks = CustomTypeHelper.createStructuralFeatureList(projectPracticeEClass, STRUCTURAL_FEATURE_NAME_TASKS,
				UmaPackage.eINSTANCE.getTask(), new TasksSettingDelegateFactory());
		this.workProducts = CustomTypeHelper.createStructuralFeatureList(projectPracticeEClass,
				STRUCTURAL_FEATURE_NAME_WORK_PRODUCTS, UmaPackage.eINSTANCE.getWorkProduct(),
				new WorkProductsSettingDelegateFactory());

		projectPracticeEClass.getEStructuralFeatures().add(tasks);
		projectPracticeEClass.getEStructuralFeatures().add(workProducts);

		CustomTypeHelper.getExtendedUmaPackage().getEClassifiers().add(projectPracticeEClass);
	}

	@Override
	public EObject wrap(EObject object) {
		return CustomTypeHelper.copy(object, projectPracticeEClass);
	}

	@Override
	public boolean matches(EObject object) {
		return ProjectPracticeHelper.isProjectPractice(object);
	}

	@Override
	public EClass getCustomType() {
		return projectPracticeEClass;
	}

	@Override
	public Class<CustomCategory> getHolderType() {
		return CustomCategory.class;
	}

}
