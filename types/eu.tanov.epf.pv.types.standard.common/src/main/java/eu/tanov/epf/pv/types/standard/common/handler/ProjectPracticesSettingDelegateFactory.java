package eu.tanov.epf.pv.types.standard.common.handler;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Internal.SettingDelegate;
import org.eclipse.epf.uma.CustomCategory;

import eu.tanov.epf.pv.service.types.handler.CustomTypeHandler;
import eu.tanov.epf.pv.service.types.util.CustomCategoryCustomTypeReadOnlySettingDelegate;

public class ProjectPracticesSettingDelegateFactory implements SettingDelegate.Factory {

	private final CustomTypeHandler<CustomCategory> typeHandler;

	@Override
	public SettingDelegate createSettingDelegate(EStructuralFeature eStructuralFeature) {
		return new CustomCategoryCustomTypeReadOnlySettingDelegate<CustomCategory>(typeHandler);
	}

	public ProjectPracticesSettingDelegateFactory(CustomTypeHandler<CustomCategory> typeHandler) {
		this.typeHandler = typeHandler;
	}

}
