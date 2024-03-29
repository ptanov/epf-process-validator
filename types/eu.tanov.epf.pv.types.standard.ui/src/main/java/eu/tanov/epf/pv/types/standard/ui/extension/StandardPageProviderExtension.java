package eu.tanov.epf.pv.types.standard.ui.extension;

import java.util.Iterator;
import java.util.Map;

import org.eclipse.epf.authoring.ui.forms.CustomCategoryAssignPage;
import org.eclipse.epf.authoring.ui.forms.CustomCategoryDescriptionPage;
import org.eclipse.epf.authoring.ui.providers.IMethodElementEditorPageProviderExtension;
import org.eclipse.ui.forms.editor.FormEditor;

import eu.tanov.epf.pv.types.standard.common.util.StandardHelper;
import eu.tanov.epf.pv.types.standard.ui.pages.StandardDescriptionPage;
import eu.tanov.epf.pv.types.standard.ui.pages.StandardGuidancePage;
import eu.tanov.epf.pv.types.standard.ui.pages.StandardProjectPracticesPage;
import eu.tanov.epf.pv.types.standard.ui.pages.StandardRolesPage;
import eu.tanov.epf.pv.types.standard.ui.pages.StandardWorkProductsPage;
import eu.tanov.epf.pv.types.standard.ui.pages.StandardWorkflowsPage;

public class StandardPageProviderExtension implements IMethodElementEditorPageProviderExtension {

	@Override
	public Map<Object, String> getPages(Map<Object, String> pageMap, FormEditor editor, Object input) {
		if (StandardHelper.isStandard(input)) {
			removeDescriptionPage(pageMap);
			removeAssignPage(pageMap);
			pageMap.put(new StandardDescriptionPage(editor), null);

			pageMap.put(new StandardWorkProductsPage(editor), null);
			pageMap.put(new StandardWorkflowsPage(editor), null);
			pageMap.put(new StandardRolesPage(editor), null);
			pageMap.put(new StandardGuidancePage(editor), null);
			pageMap.put(new StandardProjectPracticesPage(editor), null);
		}

		return pageMap;
	}

	/**
	 * Because there are strings like "custom category", but should be "Standard"
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
