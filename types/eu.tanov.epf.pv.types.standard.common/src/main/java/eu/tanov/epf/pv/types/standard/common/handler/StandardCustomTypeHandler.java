package eu.tanov.epf.pv.types.standard.common.handler;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.UmaPackage;

import eu.tanov.epf.pv.service.types.handler.CustomTypeHandler;
import eu.tanov.epf.pv.service.types.service.CustomTypeHandlersService;
import eu.tanov.epf.pv.service.types.util.CustomTypeHelper;
import eu.tanov.epf.pv.types.projectpractice.common.util.ProjectPracticeHelper;
import eu.tanov.epf.pv.types.standard.common.StandardActivator;
import eu.tanov.epf.pv.types.standard.common.util.StandardHelper;

public class StandardCustomTypeHandler implements CustomTypeHandler<CustomCategory> {

	private static final String STRUCTURAL_FEATURE_NAME_WORK_PRODUCTS = "workProducts";
	private static final String STRUCTURAL_FEATURE_NAME_ROLES = "roles";
	private static final String STRUCTURAL_FEATURE_NAME_PROJECT_PRACTICES = "projectPractices";
	/**
	 * XXX if used outside - move to StandardHelper
	 */
	public static final String TYPE_NAME = "Standard";

	private final EClass standardEClass;
	private EReference roles;
	private EReference workProducts;
	private EReference projectPractices;

	public StandardCustomTypeHandler() {
		this.standardEClass = CustomTypeHelper.createType(TYPE_NAME);
	}

	/*
	 * based on http://www.ibm.com/developerworks/library/os-eclipse-dynamicemf/
	 */
	@Override
	public void registerType() {
		this.workProducts = CustomTypeHelper.createStructuralFeatureList(standardEClass, STRUCTURAL_FEATURE_NAME_WORK_PRODUCTS,
				UmaPackage.eINSTANCE.getWorkProduct(), new WorkProductsSettingDelegateFactory());
		this.roles = CustomTypeHelper.createStructuralFeatureList(standardEClass, STRUCTURAL_FEATURE_NAME_ROLES,
				UmaPackage.eINSTANCE.getRole(), new RolesSettingDelegateFactory());

		final CustomTypeHandler<CustomCategory> projectPracticeTypeHandler = getProjectPracticeTypeHandler();
		this.projectPractices = CustomTypeHelper.createStructuralFeatureList(standardEClass, STRUCTURAL_FEATURE_NAME_PROJECT_PRACTICES,
				projectPracticeTypeHandler.getCustomType(), new ProjectPracticesSettingDelegateFactory(projectPracticeTypeHandler));

		standardEClass.getEStructuralFeatures().add(roles);
		standardEClass.getEStructuralFeatures().add(workProducts);
		standardEClass.getEStructuralFeatures().add(projectPractices);

		CustomTypeHelper.getExtendedUmaPackage().getEClassifiers().add(standardEClass);
	}

	private CustomTypeHandler<CustomCategory> getProjectPracticeTypeHandler() {
		final CustomTypeHandlersService service = StandardActivator.getDefault().getService(CustomTypeHandlersService.class);
		final EClass projectPracticeType = ProjectPracticeHelper.getCustomType();

		return service.getHandlerForType(projectPracticeType, CustomCategory.class);
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
		// depends on Project Practice
		return ProjectPracticeHelper.isRegistered();
	}

}
