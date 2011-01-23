package eu.tanov.epf.pv.service.types.util;

import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.epf.library.edit.util.ContentElementOrderList;
import org.eclipse.epf.uma.ContentElement;
import org.eclipse.epf.uma.VariabilityElement;

public abstract class AbstractFilteredContentElementOrderList<T> extends ContentElementOrderList {
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
	protected final VariabilityElement editElement;
	/**
	 * why is private in ContentElementOrderList?
	 */
	protected final EStructuralFeature feature;

	public AbstractFilteredContentElementOrderList(ContentElement contentElement, EStructuralFeature feature) {
		super(contentElement, CONTENT_ELEMENTS__FOR_ELEMENT_ONLY, feature);

		this.feature = feature;
		this.editElement = contentElement;
	}

	public T[] toFilteredArray() {
		final List<T> result = toFilteredList();

		@SuppressWarnings("unchecked")
		final T[] castedResult = (T[]) result.toArray();
		return castedResult;
	}

	protected abstract List<T> toFilteredList();

}
