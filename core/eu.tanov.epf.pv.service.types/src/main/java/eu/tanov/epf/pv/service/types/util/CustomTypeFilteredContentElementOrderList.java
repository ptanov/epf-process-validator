package eu.tanov.epf.pv.service.types.util;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.epf.uma.ContentElement;
import org.eclipse.epf.uma.DescribableElement;
import org.eclipse.epf.uma.VariabilityElement;

import eu.tanov.epf.pv.service.types.handler.CustomTypeHandler;

public class CustomTypeFilteredContentElementOrderList<T extends DescribableElement> extends
		TypeFilteredContentElementOrderList<T> {
	private static final long serialVersionUID = -553484377131876413L;

	protected final CustomTypeHandler<T> typeHandler;

	public CustomTypeFilteredContentElementOrderList(ContentElement contentElement, EStructuralFeature feature,
			CustomTypeHandler<T> typeHandler) {
		super(contentElement, feature, typeHandler.getHolderType());

		this.typeHandler = typeHandler;
	}

	@Override
	protected List<T> toFilteredList() {
		return toFilteredList(editElement, feature, typeHandler);
	}

	public static <T extends DescribableElement> List<T> toFilteredList(VariabilityElement editElement,
			EStructuralFeature feature, CustomTypeHandler<T> typeHandler) {
		final List<T> subResult = TypeFilteredContentElementOrderList.toFilteredList(editElement, feature,
				typeHandler.getHolderType());

		final ArrayList<T> result = new ArrayList<T>(subResult.size());

		for (T next : subResult) {
			if (typeHandler.matches(next)) {
				result.add(next);
			}
		}

		return result;
	}

}
