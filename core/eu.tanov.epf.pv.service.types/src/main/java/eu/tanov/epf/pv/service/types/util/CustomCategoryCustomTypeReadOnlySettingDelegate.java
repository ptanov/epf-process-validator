package eu.tanov.epf.pv.service.types.util;

import org.eclipse.epf.uma.DescribableElement;

import eu.tanov.epf.pv.service.types.handler.CustomTypeHandler;

public class CustomCategoryCustomTypeReadOnlySettingDelegate<T extends DescribableElement> extends
		CustomCategoryCategorizedElementsReadOnlySettingDelegate<T> {

	private final CustomTypeHandler<T> typeHandler;

	public CustomCategoryCustomTypeReadOnlySettingDelegate(CustomTypeHandler<T> typeHandler) {
		super(typeHandler.getHolderType());
		this.typeHandler = typeHandler;
	}

	@Override
	protected boolean matches(DescribableElement element) {
		if (!super.matches(element)) {
			return false;
		}

		return typeHandler.matches(element);
	}

}
