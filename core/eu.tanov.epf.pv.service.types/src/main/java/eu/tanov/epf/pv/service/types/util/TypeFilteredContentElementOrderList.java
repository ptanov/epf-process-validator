package eu.tanov.epf.pv.service.types.util;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.ContentElement;
import org.eclipse.epf.uma.VariabilityElement;

public class TypeFilteredContentElementOrderList<T> extends AbstractFilteredContentElementOrderList<T> {
	private static final long serialVersionUID = 7235835634352555907L;

	protected final Class<T> acceptOnlyType;

	public TypeFilteredContentElementOrderList(ContentElement contentElement, EStructuralFeature feature, Class<T> acceptOnlyType) {
		super(contentElement, feature);

		this.acceptOnlyType = acceptOnlyType;
	}

	@Override
	protected List<T> toFilteredList() {
		return toFilteredList(editElement, feature, acceptOnlyType);
	}

	/**
	 * TODO improve method
	 */
	public static <T> List<T> toFilteredList(VariabilityElement editElement, EStructuralFeature feature, Class<T> acceptOnlyType) {
		final List<T> result;
		final Object object = editElement.eGet(feature);
		if (object instanceof List) {
			result = TngUtil.<T> extractType((List<?>) object, acceptOnlyType);
		} else if (object == null) {
			result = Collections.emptyList();
		} else {
			throw new IllegalStateException("Feature " + feature + " on " + editElement + " is not list, but: " + object);
		}
		return result;
	}

}
