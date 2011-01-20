package eu.tanov.epf.pv.ui.techniques.type;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.epf.uma.UmaPackage;

import eu.tanov.epf.pv.service.ocl.util.CustomTypeHelper;
import eu.tanov.epf.pv.service.types.handler.CustomTypeHandler;
import eu.tanov.epf.pv.ui.techniques.util.TechniquesHelper;

public class TechniqueCustomTypeHandler implements CustomTypeHandler {

	private static final String STRUCTURAL_FEATURE_NAME_TASKS = "tasks";
	private static final String STRUCTURAL_FEATURE_NAME_WORK_PRODUCTS = "workProducts";
	private static final String TYPE_NAME = "Technique";

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
	public void registerType(EPackage extendedUmaPackage) {
		this.tasks = CustomTypeHelper.createStructuralFeatureList(extendedUmaPackage, techniqueEClass, STRUCTURAL_FEATURE_NAME_TASKS, UmaPackage.eINSTANCE.getTask(), new TasksSettingDelegateFactory());
		this.workProducts = CustomTypeHelper.createStructuralFeatureList(extendedUmaPackage, techniqueEClass, STRUCTURAL_FEATURE_NAME_WORK_PRODUCTS,
				UmaPackage.eINSTANCE.getWorkProduct(), new WorkProductsSettingDelegateFactory());

		techniqueEClass.getEStructuralFeatures().add(tasks);
		techniqueEClass.getEStructuralFeatures().add(workProducts);

		
		extendedUmaPackage.getEClassifiers().add(techniqueEClass);
	}

	@Override
	public EObject wrap(EObject object) {
		return CustomTypeHelper.copy(object, techniqueEClass);
	}

	@Override
	public boolean matches(EObject object) {
		return TechniquesHelper.isTechnique(object);
	}

	@Override
	public EClass getCustomType() {
		return techniqueEClass;
	}

}
