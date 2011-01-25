package eu.tanov.epf.pv.types.projectiteration.common.handler;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.Tool;
import org.eclipse.epf.uma.UmaPackage;

import eu.tanov.epf.pv.service.types.handler.CustomTypeHandler;
import eu.tanov.epf.pv.service.types.service.CustomTypeHandlersService;
import eu.tanov.epf.pv.service.types.util.CustomTypeHelper;
import eu.tanov.epf.pv.service.types.util.CustomTypeSettingDelegateFactory;
import eu.tanov.epf.pv.service.types.util.UmaTypeSettingDelegateFactory;
import eu.tanov.epf.pv.types.projectiteration.common.ProjectIterationActivator;
import eu.tanov.epf.pv.types.projectiteration.common.util.ProjectIterationHelper;
import eu.tanov.epf.pv.types.projectpractice.common.util.ProjectPracticeHelper;
import eu.tanov.epf.pv.types.standard.common.util.StandardHelper;

public class ProjectIterationCustomTypeHandler implements CustomTypeHandler<CustomCategory> {

	private static final String STRUCTURAL_FEATURE_NAME_PRACTICES = "practices";
	private static final String STRUCTURAL_FEATURE_NAME_STANDARDS = "standards";
	private static final String STRUCTURAL_FEATURE_NAME_TOOLS = "tools";
	private static final String STRUCTURAL_FEATURE_NAME_WORKFLOWS = "workflows";

	/**
	 * XXX if used outside - move to ProjectIterationHelper
	 */
	public static final String TYPE_NAME = "ProjectIteration";

	private final EClass projectIterationEClass;

	private EReference practices;
	private EReference standards;
	private EReference tools;
	private EReference workflows;

	public ProjectIterationCustomTypeHandler() {
		this.projectIterationEClass = CustomTypeHelper.createType(TYPE_NAME);
	}

	/*
	 * based on http://www.ibm.com/developerworks/library/os-eclipse-dynamicemf/
	 */
	@Override
	public void registerType() {
		final CustomTypeHandler<CustomCategory> projectPracticeTypeHandler = getProjectPracticeTypeHandler();
		this.practices = CustomTypeHelper.createStructuralFeatureList(projectIterationEClass, STRUCTURAL_FEATURE_NAME_PRACTICES,
				projectPracticeTypeHandler.getCustomType(), new CustomTypeSettingDelegateFactory<CustomCategory>(
						projectPracticeTypeHandler));

		final CustomTypeHandler<CustomCategory> standardTypeHandler = getStandardTypeHandler();
		this.standards = CustomTypeHelper.createStructuralFeatureList(projectIterationEClass, STRUCTURAL_FEATURE_NAME_STANDARDS,
				standardTypeHandler.getCustomType(), new CustomTypeSettingDelegateFactory<CustomCategory>(standardTypeHandler));

		this.tools = CustomTypeHelper.createStructuralFeatureList(projectIterationEClass, STRUCTURAL_FEATURE_NAME_TOOLS,
				UmaPackage.eINSTANCE.getTool(), new UmaTypeSettingDelegateFactory<Tool>(Tool.class));
		this.workflows = CustomTypeHelper.createStructuralFeatureList(projectIterationEClass, STRUCTURAL_FEATURE_NAME_WORKFLOWS,
				UmaPackage.eINSTANCE.getProcess(), new UmaTypeSettingDelegateFactory<Process>(Process.class));

		projectIterationEClass.getEStructuralFeatures().add(practices);
		projectIterationEClass.getEStructuralFeatures().add(standards);
		projectIterationEClass.getEStructuralFeatures().add(tools);
		projectIterationEClass.getEStructuralFeatures().add(workflows);

		CustomTypeHelper.getExtendedUmaPackage().getEClassifiers().add(projectIterationEClass);
	}

	private CustomTypeHandler<CustomCategory> getProjectPracticeTypeHandler() {
		final CustomTypeHandlersService service = ProjectIterationActivator.getDefault().getService(
				CustomTypeHandlersService.class);
		final EClass projectPracticeType = ProjectPracticeHelper.getCustomType();

		return service.getHandlerForType(projectPracticeType, CustomCategory.class);
	}

	private CustomTypeHandler<CustomCategory> getStandardTypeHandler() {
		final CustomTypeHandlersService service = ProjectIterationActivator.getDefault().getService(
				CustomTypeHandlersService.class);
		final EClass standardType = StandardHelper.getCustomType();

		return service.getHandlerForType(standardType, CustomCategory.class);
	}

	@Override
	public EObject wrap(EObject object) {
		return CustomTypeHelper.copy(object, projectIterationEClass);
	}

	@Override
	public boolean matches(EObject object) {
		return ProjectIterationHelper.isProjectIteration(object);
	}

	@Override
	public EClass getCustomType() {
		return projectIterationEClass;
	}

	@Override
	public Class<CustomCategory> getHolderType() {
		return CustomCategory.class;
	}

	@Override
	public String[] getCategoryPkgPath() {
		return ProjectIterationHelper.PROJECT_ITERATIONS_PATH;
	}

	@Override
	public boolean isReadyToRegisterType() {
		// depends on Project Practice and Standard
		return ProjectPracticeHelper.isRegistered() && StandardHelper.isRegistered();
	}

}
