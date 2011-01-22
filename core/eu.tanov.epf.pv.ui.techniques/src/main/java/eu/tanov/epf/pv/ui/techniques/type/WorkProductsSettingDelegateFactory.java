package eu.tanov.epf.pv.ui.techniques.type;

import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Internal.SettingDelegate;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.DescribableElement;
import org.eclipse.epf.uma.WorkProduct;

import eu.tanov.epf.pv.service.types.util.CustomCategoryCategorizedElementsReadOnlySettingDelegate;
import eu.tanov.epf.pv.ui.techniques.util.TechniquesHelper;

public class WorkProductsSettingDelegateFactory implements SettingDelegate.Factory {

	private static class WorkProductsSettingDelegate extends
			CustomCategoryCategorizedElementsReadOnlySettingDelegate<WorkProduct> {
		public WorkProductsSettingDelegate() {
			super(WorkProduct.class);
		}

		@Override
		protected List<DescribableElement> getList(CustomCategory customCategory) {
			TechniquesHelper.updateWorkProducts(customCategory);
			return super.getList(customCategory);
		}
	}

	@Override
	public SettingDelegate createSettingDelegate(EStructuralFeature eStructuralFeature) {
		return new WorkProductsSettingDelegate();
	}

}
