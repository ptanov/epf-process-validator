package eu.tanov.epf.pv.types.standard.common.handler;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.UmaPackage;

import eu.tanov.epf.pv.service.types.handler.CustomTypeHandler;
import eu.tanov.epf.pv.service.types.util.CustomTypeHelper;
import eu.tanov.epf.pv.types.standard.common.util.StandardHelper;

public class StandardCustomTypeHandler implements CustomTypeHandler<CustomCategory> {

	private static final String STRUCTURAL_FEATURE_NAME_TASKS = "tasks";
	private static final String STRUCTURAL_FEATURE_NAME_WORK_PRODUCTS = "workProducts";
	/**
	 * XXX if used outside - move to StandardHelper
	 */
	public static final String TYPE_NAME = "Standard";

	private final EClass standardEClass;
	private EReference tasks;
	private EReference workProducts;

	public StandardCustomTypeHandler() {
		this.standardEClass = CustomTypeHelper.createType(TYPE_NAME);
	}

	/*
	 * based on http://www.ibm.com/developerworks/library/os-eclipse-dynamicemf/
	 */
	@Override
	public void registerType() {
		this.tasks = CustomTypeHelper.createStructuralFeatureList(standardEClass, STRUCTURAL_FEATURE_NAME_TASKS,
				UmaPackage.eINSTANCE.getTask(), new TasksSettingDelegateFactory());
		this.workProducts = CustomTypeHelper.createStructuralFeatureList(standardEClass, STRUCTURAL_FEATURE_NAME_WORK_PRODUCTS,
				UmaPackage.eINSTANCE.getWorkProduct(), new WorkProductsSettingDelegateFactory());

		standardEClass.getEStructuralFeatures().add(tasks);
		standardEClass.getEStructuralFeatures().add(workProducts);

		CustomTypeHelper.getExtendedUmaPackage().getEClassifiers().add(standardEClass);
	}

	@Override
	public EObject wrap(EObject object) {
		return CustomTypeHelper.copy(object, standardEClass);
	}

	@Override
	public boolean matches(EObject object) {
		return StandardHelper.isStandard(object);
	}

	@Override
	public EClass getCustomType() {
		return standardEClass;
	}

	@Override
	public Class<CustomCategory> getHolderType() {
		return CustomCategory.class;
	}

	@Override
	public String[] getCategoryPkgPath() {
		return StandardHelper.STANDARDS_PATH;
	}

	@Override
	public boolean isReadyToRegisterType() {
		// not dependent to any type
		return true;
	}

}
