package eu.tanov.epf.pv.types.projectiteration.ui.extension;

import java.util.Iterator;
import java.util.Map;

import org.eclipse.epf.authoring.ui.forms.CustomCategoryAssignPage;
import org.eclipse.epf.authoring.ui.forms.CustomCategoryDescriptionPage;
import org.eclipse.epf.authoring.ui.providers.IMethodElementEditorPageProviderExtension;
import org.eclipse.ui.forms.editor.FormEditor;

import eu.tanov.epf.pv.types.projectiteration.common.util.ProjectIterationHelper;
import eu.tanov.epf.pv.types.projectiteration.ui.pages.ProjectIterationDescriptionPage;
import eu.tanov.epf.pv.types.projectiteration.ui.pages.ProjectIterationGuidancePage;
import eu.tanov.epf.pv.types.projectiteration.ui.pages.ProjectIterationTasksPage;
import eu.tanov.epf.pv.types.projectiteration.ui.pages.ProjectIterationWorkProductsPage;

public class ProjectIterationPageProviderExtension implements IMethodElementEditorPageProviderExtension {

	@Override
	public Map<Object, String> getPages(Map<Object, String> pageMap, FormEditor editor, Object input) {
		if (ProjectIterationHelper.isProjectIteration(input)) {
			removeDescriptionPage(pageMap);
			removeAssignPage(pageMap);
			pageMap.put(new ProjectIterationDescriptionPage(editor), null);

			pageMap.put(new ProjectIterationTasksPage(editor), null);
			pageMap.put(new ProjectIterationWorkProductsPage(editor), null);
			pageMap.put(new ProjectIterationGuidancePage(editor), null);
		}

		return pageMap;
	}

	/**
	 * Because there are strings like "custom category", but should be "ProjectIteration"
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
