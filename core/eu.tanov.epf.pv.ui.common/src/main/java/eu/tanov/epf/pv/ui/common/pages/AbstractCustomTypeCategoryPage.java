package eu.tanov.epf.pv.ui.common.pages;

import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.uma.DescribableElement;
import org.eclipse.ui.forms.editor.FormEditor;

import eu.tanov.epf.pv.service.types.handler.CustomTypeHandler;
import eu.tanov.epf.pv.service.types.util.AbstractFilteredContentElementOrderList;
import eu.tanov.epf.pv.service.types.util.CustomTypeFilteredContentElementOrderList;

public abstract class AbstractCustomTypeCategoryPage<T extends DescribableElement> extends AbstractCustomCategoryPage<T> {

	protected final CustomTypeHandler<T> typeHandler;

	protected class CustomTypeContentFilter extends TypeContentFilter {
		@Override
		public String[] getContentPackagePath() {
			return typeHandler.getCategoryPkgPath();
		};
	}

	public AbstractCustomTypeCategoryPage(FormEditor editor, String pageId, String title, CustomTypeHandler<T> typeHandler,
			String classNameForFormTitle) {
		super(editor, pageId, title, typeHandler.getHolderType(), classNameForFormTitle);
		this.typeHandler = typeHandler;
	}

	@Override
	protected AbstractFilteredContentElementOrderList<T> createFilteredContentElementOderList() {
		return new CustomTypeFilteredContentElementOrderList<T>(contentElement, getOrderFeature(), typeHandler);
	}

	@Override
	protected IFilter createFilter() {
		return new CustomTypeContentFilter();
	}
}
