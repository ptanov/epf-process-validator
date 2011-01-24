package eu.tanov.epf.pv.types.projectpractice.common.handler;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.UmaPackage;

import eu.tanov.epf.pv.service.types.handler.CustomTypeHandler;
import eu.tanov.epf.pv.service.types.service.CustomTypeHandlersService;
import eu.tanov.epf.pv.service.types.util.CustomTypeHelper;
import eu.tanov.epf.pv.types.projectpractice.common.ProjectPracticeActivator;
import eu.tanov.epf.pv.types.projectpractice.common.util.ProjectPracticeHelper;
import eu.tanov.epf.pv.types.technique.common.util.TechniqueHelper;

public class ProjectPracticeCustomTypeHandler implements CustomTypeHandler<CustomCategory> {

	private static final String STRUCTURAL_FEATURE_NAME_TOOLS = "tools";
	private static final String STRUCTURAL_FEATURE_NAME_TASKS = "tasks";
	private static final String STRUCTURAL_FEATURE_NAME_WORK_PRODUCTS = "workProducts";
	private static final String STRUCTURAL_FEATURE_NAME_ROLES = "roles";
	private static final String STRUCTURAL_FEATURE_NAME_TECHNIQUES = "techniques";

	/**
	 * XXX if used outside - move to ProjectPracticeHelper
	 */
	public static final String TYPE_NAME = "ProjectPractice";

	private final EClass projectPracticeEClass;
	private EReference tools;
	private EReference tasks;
	private EReference workProducts;
	private EReference roles;
	private EReference techniques;

	public ProjectPracticeCustomTypeHandler() {
		this.projectPracticeEClass = CustomTypeHelper.createType(TYPE_NAME);
	}

	/*
	 * based on http://www.ibm.com/developerworks/library/os-eclipse-dynamicemf/
	 */
	@Override
	public void registerType() {
		this.tools = CustomTypeHelper.createStructuralFeatureList(projectPracticeEClass, STRUCTURAL_FEATURE_NAME_TOOLS,
				UmaPackage.eINSTANCE.getTool(), new ToolsSettingDelegateFactory());
		this.tasks = CustomTypeHelper.createStructuralFeatureList(projectPracticeEClass, STRUCTURAL_FEATURE_NAME_TASKS,
				UmaPackage.eINSTANCE.getTask(), new TasksSettingDelegateFactory());
		this.workProducts = CustomTypeHelper.createStructuralFeatureList(projectPracticeEClass,
				STRUCTURAL_FEATURE_NAME_WORK_PRODUCTS, UmaPackage.eINSTANCE.getWorkProduct(),
				new WorkProductsSettingDelegateFactory());
		this.roles = CustomTypeHelper.createStructuralFeatureList(projectPracticeEClass, STRUCTURAL_FEATURE_NAME_ROLES,
				UmaPackage.eINSTANCE.getRole(), new RolesSettingDelegateFactory());

		final CustomTypeHandler<CustomCategory> techniqueTypeHandler = getTechniqueTypeHandler();
		this.techniques = CustomTypeHelper.createStructuralFeatureList(projectPracticeEClass, STRUCTURAL_FEATURE_NAME_TECHNIQUES,
				techniqueTypeHandler.getCustomType(), new TechniquesSettingDelegateFactory(techniqueTypeHandler));

		projectPracticeEClass.getEStructuralFeatures().add(tools);
		projectPracticeEClass.getEStructuralFeatures().add(tasks);
		projectPracticeEClass.getEStructuralFeatures().add(workProducts);
		projectPracticeEClass.getEStructuralFeatures().add(roles);
		projectPracticeEClass.getEStructuralFeatures().add(techniques);

		CustomTypeHelper.getExtendedUmaPackage().getEClassifiers().add(projectPracticeEClass);
	}

	private CustomTypeHandler<CustomCategory> getTechniqueTypeHandler() {
		final CustomTypeHandlersService service = ProjectPracticeActivator.getDefault().getService(
				CustomTypeHandlersService.class);
		final EClass techniqueType = TechniqueHelper.getCustomType();

		return service.getHandlerForType(techniqueType, CustomCategory.class);
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

	@Override
	public String[] getCategoryPkgPath() {
		return ProjectPracticeHelper.PROJECT_PRACTICES_PATH;
	}

	@Override
	public boolean isReadyToRegisterType() {
		// depends on technique
		return TechniqueHelper.isRegistered();
	}

}
