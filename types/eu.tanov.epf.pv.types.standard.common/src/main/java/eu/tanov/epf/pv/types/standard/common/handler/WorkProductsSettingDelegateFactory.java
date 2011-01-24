package eu.tanov.epf.pv.types.standard.common.handler;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Internal.SettingDelegate;
import org.eclipse.epf.uma.WorkProduct;

import eu.tanov.epf.pv.service.types.util.CustomCategoryCategorizedElementsReadOnlySettingDelegate;

public class WorkProductsSettingDelegateFactory implements SettingDelegate.Factory {

	private static class WorkProductsSettingDelegate extends
			CustomCategoryCategorizedElementsReadOnlySettingDelegate<WorkProduct> {
		public WorkProductsSettingDelegate() {
			super(WorkProduct.class);
		}
	}

	@Override
	public SettingDelegate createSettingDelegate(EStructuralFeature eStructuralFeature) {
		return new WorkProductsSettingDelegate();
	}

}
