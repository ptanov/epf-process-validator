package eu.tanov.epf.pv.techniques.extension;

import java.util.Iterator;
import java.util.Map;

import org.eclipse.epf.authoring.ui.forms.CustomCategoryAssignPage;
import org.eclipse.epf.authoring.ui.forms.CustomCategoryDescriptionPage;
import org.eclipse.epf.authoring.ui.providers.IMethodElementEditorPageProviderExtension;
import org.eclipse.ui.forms.editor.FormEditor;

import eu.tanov.epf.pv.techniques.pages.TechniqueDescriptionPage;
import eu.tanov.epf.pv.techniques.pages.TechniqueGuidancePage;
import eu.tanov.epf.pv.techniques.pages.TechniqueTasksPage;
import eu.tanov.epf.pv.techniques.pages.TechniqueWorkProductsPage;
import eu.tanov.epf.pv.techniques.util.TechniquesHelper;

public class TechniquePageProviderExtension implements IMethodElementEditorPageProviderExtension {

	@Override
	public Map<Object, String> getPages(Map<Object, String> pageMap, FormEditor editor, Object input) {
		if (TechniquesHelper.isTechnique(input)) {
			removeDescriptionPage(pageMap);
			removeAssignPage(pageMap);
			pageMap.put(new TechniqueDescriptionPage(editor), null);

			pageMap.put(new TechniqueTasksPage(editor), null);
			pageMap.put(new TechniqueWorkProductsPage(editor), null);
			pageMap.put(new TechniqueGuidancePage(editor), null);
		}

		return pageMap;
	}

	/**
	 * Because there are strings like "custom category", but should be "Technique"
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
