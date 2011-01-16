package eu.tanov.epf.pv.ui.techniques.type;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Internal.DynamicValueHolder;
import org.eclipse.emf.ecore.EStructuralFeature.Internal.SettingDelegate;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.DescribableElement;
import org.eclipse.epf.uma.WorkProduct;

import eu.tanov.epf.pv.ocl.util.AbstractReadOnlySettingDelegate;

public class WorkProductsSettingDelegateFactory implements SettingDelegate.Factory {
	/**
	 * TODO extend more useful custom type, issue #90
	 */
	private static class TasksSettingDelegate extends AbstractReadOnlySettingDelegate {

		@Override
		public List<WorkProduct> dynamicGet(InternalEObject owner, DynamicValueHolder settings, int dynamicFeatureID,
				boolean resolve, boolean coreType) {
			if (!(owner instanceof CustomCategory)) {
				throw new IllegalArgumentException("Holder object is not CustomCategory: " + owner);
			}
			final List<DescribableElement> categorizedElements = ((CustomCategory) owner).getCategorizedElements();

			final List<WorkProduct> result = new LinkedList<WorkProduct>();

			for (DescribableElement next : categorizedElements) {
				if (next instanceof WorkProduct) {
					result.add((WorkProduct) next);
				}
			}
			return result;
		}
	}

	@Override
	public SettingDelegate createSettingDelegate(EStructuralFeature eStructuralFeature) {
		return new TasksSettingDelegate();
	}

}
