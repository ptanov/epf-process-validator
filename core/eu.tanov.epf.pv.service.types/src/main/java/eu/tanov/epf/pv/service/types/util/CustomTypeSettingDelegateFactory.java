package eu.tanov.epf.pv.service.types.util;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Internal.SettingDelegate;
import org.eclipse.epf.uma.DescribableElement;

import eu.tanov.epf.pv.service.types.handler.CustomTypeHandler;

public class CustomTypeSettingDelegateFactory<T extends DescribableElement> implements SettingDelegate.Factory {

	private final CustomTypeHandler<T> typeHandler;

	@Override
	public SettingDelegate createSettingDelegate(EStructuralFeature eStructuralFeature) {
		return new CustomCategoryCustomTypeReadOnlySettingDelegate<T>(typeHandler);
	}

	public CustomTypeSettingDelegateFactory(CustomTypeHandler<T> typeHandler) {
		this.typeHandler = typeHandler;
	}

}