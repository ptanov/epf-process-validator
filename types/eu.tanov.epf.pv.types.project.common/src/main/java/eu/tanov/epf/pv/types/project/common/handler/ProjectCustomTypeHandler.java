package eu.tanov.epf.pv.types.project.common.handler;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.epf.uma.CustomCategory;

import eu.tanov.epf.pv.service.types.handler.CustomTypeHandler;
import eu.tanov.epf.pv.service.types.service.CustomTypeHandlersService;
import eu.tanov.epf.pv.service.types.util.CustomTypeHelper;
import eu.tanov.epf.pv.service.types.util.CustomTypeSettingDelegateFactory;
import eu.tanov.epf.pv.types.project.common.ProjectActivator;
import eu.tanov.epf.pv.types.project.common.util.ProjectHelper;
import eu.tanov.epf.pv.types.projectiteration.common.util.ProjectIterationHelper;

public class ProjectCustomTypeHandler implements CustomTypeHandler<CustomCategory> {

	private static final String STRUCTURAL_FEATURE_NAME_ITERATIONS = "iterations";
	/**
	 * XXX if used outside - move to ProjectHelper
	 */
	public static final String TYPE_NAME = "Project";

	private final EClass projectEClass;
	private EReference iterations;

	public ProjectCustomTypeHandler() {
		this.projectEClass = CustomTypeHelper.createType(TYPE_NAME);
	}

	/*
	 * based on http://www.ibm.com/developerworks/library/os-eclipse-dynamicemf/
	 */
	@Override
	public void registerType() {
		final CustomTypeHandler<CustomCategory> projectIterationTypeHandler = getProjectIterationTypeHandler();
		this.iterations = CustomTypeHelper.createStructuralFeatureList(projectEClass, STRUCTURAL_FEATURE_NAME_ITERATIONS,
				projectIterationTypeHandler.getCustomType(), new CustomTypeSettingDelegateFactory<CustomCategory>(
						projectIterationTypeHandler));

		projectEClass.getEStructuralFeatures().add(iterations);

		CustomTypeHelper.getExtendedUmaPackage().getEClassifiers().add(projectEClass);
	}

	private CustomTypeHandler<CustomCategory> getProjectIterationTypeHandler() {
		final CustomTypeHandlersService service = ProjectActivator.getDefault().getService(CustomTypeHandlersService.class);
		final EClass projectIterationType = ProjectIterationHelper.getCustomType();

		return service.getHandlerForType(projectIterationType, CustomCategory.class);
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
		// depends on Project Iteraation
		return ProjectIterationHelper.isRegistered();
	}

}
