package eu.tanov.epf.pv.types.technique.common.handler;

import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Internal.SettingDelegate;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.DescribableElement;
import org.eclipse.epf.uma.WorkProduct;

import eu.tanov.epf.pv.service.types.util.CustomCategoryCategorizedElementsReadOnlySettingDelegate;
import eu.tanov.epf.pv.types.technique.common.util.TechniqueHelper;

public class WorkProductsSettingDelegateFactory implements SettingDelegate.Factory {

	private static class WorkProductsSettingDelegate extends
			CustomCategoryCategorizedElementsReadOnlySettingDelegate<WorkProduct> {
		public WorkProductsSettingDelegate() {
			super(WorkProduct.class);
		}

		@Override
		protected List<DescribableElement> getList(CustomCategory customCategory) {
			TechniqueHelper.updateWorkProducts(customCategory);
			return super.getList(customCategory);
		}
	}

	@Override
	public SettingDelegate createSettingDelegate(EStructuralFeature eStructuralFeature) {
		return new WorkProductsSettingDelegate();
	}

}
