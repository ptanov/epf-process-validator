package eu.tanov.epf.pv.service.types.util;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature.Internal.DynamicValueHolder;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.DescribableElement;

public class CustomCategoryCategorizedElementsReadOnlySettingDelegate<T extends DescribableElement> extends
		AbstractReadOnlySettingDelegate {

	private final Class<T> clazz;

	public CustomCategoryCategorizedElementsReadOnlySettingDelegate(Class<T> clazz) {
		if (clazz == null) {
			throw new NullPointerException("Class should not be null");
		}
		this.clazz = clazz;
	}

	@Override
	public List<T> dynamicGet(InternalEObject owner, DynamicValueHolder settings, int dynamicFeatureID, boolean resolve,
			boolean coreType) {
		if (!(owner instanceof CustomCategory)) {
			throw new IllegalArgumentException("Holder object is not CustomCategory: " + owner);
		}
		final List<DescribableElement> categorizedElements = getList((CustomCategory) owner);

		final List<T> result = new LinkedList<T>();

		for (DescribableElement next : categorizedElements) {
			if (clazz.isInstance(next)) {
				@SuppressWarnings("unchecked")
				final T casted = (T) next;
				result.add(casted);
			}
		}
		return result;
	}

	/**
	 * Can be overrided if some modification of list is needed
	 * @param customCategory
	 * @return categorizedElements structural feature for the given customCategory
	 */
	protected List<DescribableElement> getList(CustomCategory customCategory) {
		return customCategory.getCategorizedElements();
	}

}
