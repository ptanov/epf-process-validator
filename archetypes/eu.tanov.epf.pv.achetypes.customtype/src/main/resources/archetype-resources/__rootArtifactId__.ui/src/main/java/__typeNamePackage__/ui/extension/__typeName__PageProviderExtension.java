package eu.tanov.epf.pv.types.${typeNamePackage}.ui.extension;

import java.util.Iterator;
import java.util.Map;

import org.eclipse.epf.authoring.ui.forms.CustomCategoryAssignPage;
import org.eclipse.epf.authoring.ui.forms.CustomCategoryDescriptionPage;
import org.eclipse.epf.authoring.ui.providers.IMethodElementEditorPageProviderExtension;
import org.eclipse.ui.forms.editor.FormEditor;

import eu.tanov.epf.pv.types.${typeNamePackage}.common.util.${typeName}Helper;
import eu.tanov.epf.pv.types.${typeNamePackage}.ui.pages.${typeName}DescriptionPage;
import eu.tanov.epf.pv.types.${typeNamePackage}.ui.pages.${typeName}GuidancePage;
import eu.tanov.epf.pv.types.${typeNamePackage}.ui.pages.${typeName}TasksPage;
import eu.tanov.epf.pv.types.${typeNamePackage}.ui.pages.${typeName}WorkProductsPage;

public class ${typeName}PageProviderExtension implements IMethodElementEditorPageProviderExtension {

	@Override
	public Map<Object, String> getPages(Map<Object, String> pageMap, FormEditor editor, Object input) {
		if (${typeName}Helper.is${typeName}(input)) {
			removeDescriptionPage(pageMap);
			removeAssignPage(pageMap);
			pageMap.put(new ${typeName}DescriptionPage(editor), null);

			pageMap.put(new ${typeName}TasksPage(editor), null);
			pageMap.put(new ${typeName}WorkProductsPage(editor), null);
			pageMap.put(new ${typeName}GuidancePage(editor), null);
		}

		return pageMap;
	}

	/**
	 * Because there are strings like "custom category", but should be "${typeName}"
	 */
	private void removeDescriptionPage(Map<Object, String> pageMap) {
		for (Iterator<Object> iterator = pageMap.keySet().iterator(); iterator.hasNext();) {
			final Object key = iterator.next();
			if (key instanceof CustomCategoryDescriptionPage) {
				iterator.remove();
				// we assume that only one CustomCategoryDescriptionPage is added - so break in first match
				break;
			}
		}
	}

	private void removeAssignPage(Map<Object, String> pageMap) {
		for (Iterator<Object> iterator = pageMap.keySet().iterator(); iterator.hasNext();) {
			final Object key = iterator.next();
			if (key instanceof CustomCategoryAssignPage) {
				iterator.remove();
				// we assume that only one AssignPage is added - so break in first match
				break;
			}
		}
	}

}
