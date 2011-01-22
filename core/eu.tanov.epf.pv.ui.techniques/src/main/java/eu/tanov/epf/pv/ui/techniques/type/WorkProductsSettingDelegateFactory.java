package eu.tanov.epf.pv.ui.techniques.type;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Internal.SettingDelegate;
import org.eclipse.epf.uma.WorkProduct;

import eu.tanov.epf.pv.service.types.util.CustomCategoryCategorizedElementsReadOnlySettingDelegate;

public class WorkProductsSettingDelegateFactory implements SettingDelegate.Factory {

	@Override
	public SettingDelegate createSettingDelegate(EStructuralFeature eStructuralFeature) {
		return new CustomCategoryCategorizedElementsReadOnlySettingDelegate<WorkProduct>(WorkProduct.class);
	}

}
