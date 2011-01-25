package eu.tanov.epf.pv.types.project.ui.extension;

import java.util.Iterator;
import java.util.Map;

import org.eclipse.epf.authoring.ui.forms.CustomCategoryAssignPage;
import org.eclipse.epf.authoring.ui.forms.CustomCategoryDescriptionPage;
import org.eclipse.epf.authoring.ui.providers.IMethodElementEditorPageProviderExtension;
import org.eclipse.ui.forms.editor.FormEditor;

import eu.tanov.epf.pv.types.project.common.util.ProjectHelper;
import eu.tanov.epf.pv.types.project.ui.pages.ProjectDescriptionPage;
import eu.tanov.epf.pv.types.project.ui.pages.ProjectGuidancePage;
import eu.tanov.epf.pv.types.project.ui.pages.ProjectProjectIterationsPage;

public class ProjectPageProviderExtension implements IMethodElementEditorPageProviderExtension {

	@Override
	public Map<Object, String> getPages(Map<Object, String> pageMap, FormEditor editor, Object input) {
		if (ProjectHelper.isProject(input)) {
			removeDescriptionPage(pageMap);
			removeAssignPage(pageMap);
			pageMap.put(new ProjectDescriptionPage(editor), null);

			pageMap.put(new ProjectProjectIterationsPage(editor), null);
			pageMap.put(new ProjectGuidancePage(editor), null);
		}

		return pageMap;
	}

	/**
	 * Because there are strings like "custom category", but should be "Project"
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
