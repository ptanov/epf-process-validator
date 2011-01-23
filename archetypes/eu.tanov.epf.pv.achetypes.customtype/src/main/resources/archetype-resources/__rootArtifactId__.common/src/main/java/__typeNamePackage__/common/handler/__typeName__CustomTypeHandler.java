package eu.tanov.epf.pv.types.${typeNamePackage}.common.handler;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.epf.uma.UmaPackage;

import eu.tanov.epf.pv.service.types.handler.CustomTypeHandler;
import eu.tanov.epf.pv.service.types.util.CustomTypeHelper;
import eu.tanov.epf.pv.types.${typeNamePackage}.common.util.${typeName}Helper;

public class ${typeName}CustomTypeHandler implements CustomTypeHandler {

	private static final String STRUCTURAL_FEATURE_NAME_TASKS = "tasks";
	private static final String STRUCTURAL_FEATURE_NAME_WORK_PRODUCTS = "workProducts";
	private static final String TYPE_NAME = "${typeName}";

	private final EClass ${typeNameVariable}EClass;
	private EReference tasks;
	private EReference workProducts;

	public ${typeName}CustomTypeHandler() {
		this.${typeNameVariable}EClass = CustomTypeHelper.createType(TYPE_NAME);
	}

	/*
	 * based on http://www.ibm.com/developerworks/library/os-eclipse-dynamicemf/
	 */
	@Override
	public void registerType(EPackage extendedUmaPackage) {
		this.tasks = CustomTypeHelper.createStructuralFeatureList(extendedUmaPackage, ${typeNameVariable}EClass,
				STRUCTURAL_FEATURE_NAME_TASKS, UmaPackage.eINSTANCE.getTask(), new TasksSettingDelegateFactory());
		this.workProducts = CustomTypeHelper.createStructuralFeatureList(extendedUmaPackage, ${typeNameVariable}EClass,
				STRUCTURAL_FEATURE_NAME_WORK_PRODUCTS, UmaPackage.eINSTANCE.getWorkProduct(),
				new WorkProductsSettingDelegateFactory());

		${typeNameVariable}EClass.getEStructuralFeatures().add(tasks);
		${typeNameVariable}EClass.getEStructuralFeatures().add(workProducts);

		extendedUmaPackage.getEClassifiers().add(${typeNameVariable}EClass);
	}

	@Override
	public EObject wrap(EObject object) {
		return CustomTypeHelper.copy(object, ${typeNameVariable}EClass);
	}

	@Override
	public boolean matches(EObject object) {
		return ${typeName}Helper.is${typeName}(object);
	}

	@Override
	public EClass getCustomType() {
		return ${typeNameVariable}EClass;
	}

}
