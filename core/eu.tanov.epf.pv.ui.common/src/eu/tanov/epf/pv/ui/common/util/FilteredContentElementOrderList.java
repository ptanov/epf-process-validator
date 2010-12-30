package eu.tanov.epf.pv.ui.common.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.epf.library.edit.util.ContentElementOrderList;
import org.eclipse.epf.uma.ContentElement;
import org.eclipse.epf.uma.VariabilityElement;

public class FilteredContentElementOrderList<T> extends ContentElementOrderList {
	private static final long serialVersionUID = 329339946902608831L;

	/**
	 * why is private in ContentElementOrderList?
	 * 
	 * always CONTENT_ELEMENTS__FOR_ELEMENT_ONLY
	 */
	protected final boolean mixed = false;

	/**
	 * why is private in ContentElementOrderList?
	 */
	private final VariabilityElement editElement;
	/**
	 * why is private in ContentElementOrderList?
	 */
	private final EStructuralFeature feature;

	private final Class<?> acceptOnlyType;

	public FilteredContentElementOrderList(ContentElement contentElement, EStructuralFeature feature, Class<T> acceptOnlyType) {
		super(contentElement, CONTENT_ELEMENTS__FOR_ELEMENT_ONLY, feature);

		this.feature = feature;
		this.editElement = contentElement;
		this.acceptOnlyType = acceptOnlyType;
	}

	/**
	 * TODO improve method
	 */
	public T[] toFilteredArray() {
		final List<T> result;
		final Object object = editElement.eGet(feature);
		if (object instanceof List) {
			result = new ArrayList<T>();
			for (Object next : (List<?>) object) {
				if (acceptOnlyType.isInstance(next)) {
					@SuppressWarnings("unchecked")
					final T casted = (T) next;
					result.add(casted);
				}
			}
		} else if (object == null) {
			result = Collections.emptyList();
		} else {
			throw new IllegalStateException("Feature " + feature + " on " + editElement + " is not list, but: " + object);
		}
		@SuppressWarnings("unchecked")
		final T[] castedResult = (T[]) result.toArray();
		return castedResult;
	}

}
