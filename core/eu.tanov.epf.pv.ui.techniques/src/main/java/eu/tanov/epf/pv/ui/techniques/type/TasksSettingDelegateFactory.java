package eu.tanov.epf.pv.ui.techniques.type;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Internal.DynamicValueHolder;
import org.eclipse.emf.ecore.EStructuralFeature.Internal.SettingDelegate;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.DescribableElement;
import org.eclipse.epf.uma.Task;

import eu.tanov.epf.pv.service.ocl.util.AbstractReadOnlySettingDelegate;

public class TasksSettingDelegateFactory implements SettingDelegate.Factory {
	/**
	 * TODO extend more useful custom type, issue #90
	 */
	private static class TasksSettingDelegate extends AbstractReadOnlySettingDelegate {

		@Override
		public List<Task> dynamicGet(InternalEObject owner, DynamicValueHolder settings, int dynamicFeatureID, boolean resolve,
				boolean coreType) {
			if (!(owner instanceof CustomCategory)) {
				throw new IllegalArgumentException("Holder object is not CustomCategory: " + owner);
			}
			final List<DescribableElement> categorizedElements = ((CustomCategory) owner).getCategorizedElements();

			final List<Task> result = new LinkedList<Task>();

			for (DescribableElement next : categorizedElements) {
				if (next instanceof Task) {
					result.add((Task) next);
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
