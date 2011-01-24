package eu.tanov.epf.pv.types.technique.common.handler;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.UmaPackage;

import eu.tanov.epf.pv.service.types.handler.CustomTypeHandler;
import eu.tanov.epf.pv.service.types.util.CustomTypeHelper;
import eu.tanov.epf.pv.types.technique.common.util.TechniqueHelper;

public class TechniqueCustomTypeHandler implements CustomTypeHandler<CustomCategory> {

	private static final String STRUCTURAL_FEATURE_NAME_TASKS = "tasks";
	private static final String STRUCTURAL_FEATURE_NAME_WORK_PRODUCTS = "workProducts";
	/**
	 * XXX if used outside - move to TechniqueHelper
	 */
	public static final String TYPE_NAME = "Technique";

	private final EClass techniqueEClass;
	private EReference tasks;
	private EReference workProducts;

	public TechniqueCustomTypeHandler() {
		this.techniqueEClass = CustomTypeHelper.createType(TYPE_NAME);
	}

	/*
	 * based on http://www.ibm.com/developerworks/library/os-eclipse-dynamicemf/
	 */
	@Override
	public void registerType() {
		this.tasks = CustomTypeHelper.createStructuralFeatureList(techniqueEClass, STRUCTURAL_FEATURE_NAME_TASKS,
				UmaPackage.eINSTANCE.getTask(), new TasksSettingDelegateFactory());
		this.workProducts = CustomTypeHelper.createStructuralFeatureList(techniqueEClass, STRUCTURAL_FEATURE_NAME_WORK_PRODUCTS,
				UmaPackage.eINSTANCE.getWorkProduct(), new WorkProductsSettingDelegateFactory());

		techniqueEClass.getEStructuralFeatures().add(tasks);
		techniqueEClass.getEStructuralFeatures().add(workProducts);

		CustomTypeHelper.getExtendedUmaPackage().getEClassifiers().add(techniqueEClass);
	}

	@Override
	public EObject wrap(EObject object) {
		return CustomTypeHelper.copy(object, techniqueEClass);
	}

	@Override
	public boolean matches(EObject object) {
		return TechniqueHelper.isTechnique(object);
	}

	@Override
	public EClass getCustomType() {
		return techniqueEClass;
	}

	@Override
	public Class<CustomCategory> getHolderType() {
		return CustomCategory.class;
	}

	@Override
	public String[] getCategoryPkgPath() {
		return TechniqueHelper.TECHNIQUES_PATH;
	}

	@Override
	public boolean isReadyToRegisterType() {
		// not dependent to any type
		return true;
	}

}
