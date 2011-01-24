package eu.tanov.epf.pv.service.types.util;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Internal.SettingDelegate;
import org.eclipse.epf.uma.DescribableElement;

public class UmaTypeSettingDelegateFactory<T extends DescribableElement> implements SettingDelegate.Factory {
	private final Class<T> clazz;

	public UmaTypeSettingDelegateFactory(Class<T> clazz) {
		this.clazz = clazz;
	}

	@Override
	public SettingDelegate createSettingDelegate(EStructuralFeature eStructuralFeature) {
		return new CustomCategoryCategorizedElementsReadOnlySettingDelegate<T>(clazz);
	}

}
