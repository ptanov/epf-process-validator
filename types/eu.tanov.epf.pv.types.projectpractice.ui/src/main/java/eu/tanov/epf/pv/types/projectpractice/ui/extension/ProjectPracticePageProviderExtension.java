package eu.tanov.epf.pv.types.projectpractice.ui.extension;

import java.util.Iterator;
import java.util.Map;

import org.eclipse.epf.authoring.ui.forms.CustomCategoryAssignPage;
import org.eclipse.epf.authoring.ui.forms.CustomCategoryDescriptionPage;
import org.eclipse.epf.authoring.ui.providers.IMethodElementEditorPageProviderExtension;
import org.eclipse.ui.forms.editor.FormEditor;

import eu.tanov.epf.pv.types.projectpractice.common.util.ProjectPracticeHelper;
import eu.tanov.epf.pv.types.projectpractice.ui.pages.ProjectPracticeDescriptionPage;
import eu.tanov.epf.pv.types.projectpractice.ui.pages.ProjectPracticeGuidancePage;
import eu.tanov.epf.pv.types.projectpractice.ui.pages.ProjectPracticeRolesPage;
import eu.tanov.epf.pv.types.projectpractice.ui.pages.ProjectPracticeTasksPage;
import eu.tanov.epf.pv.types.projectpractice.ui.pages.ProjectPracticeToolsPage;
import eu.tanov.epf.pv.types.projectpractice.ui.pages.ProjectPracticeWorkProductsPage;

public class ProjectPracticePageProviderExtension implements IMethodElementEditorPageProviderExtension {

	@Override
	public Map<Object, String> getPages(Map<Object, String> pageMap, FormEditor editor, Object input) {
		if (ProjectPracticeHelper.isProjectPractice(input)) {
			removeDescriptionPage(pageMap);
			removeAssignPage(pageMap);
			pageMap.put(new ProjectPracticeDescriptionPage(editor), null);

			pageMap.put(new ProjectPracticeToolsPage(editor), null);
			pageMap.put(new ProjectPracticeTasksPage(editor), null);
			pageMap.put(new ProjectPracticeWorkProductsPage(editor), null);
			pageMap.put(new ProjectPracticeRolesPage(editor), null);
			// TODO not implemented yet
			// pageMap.put(new ProjectPracticeTechniquesPage(editor), null);
			pageMap.put(new ProjectPracticeGuidancePage(editor), null);
		}

		return pageMap;
	}

	/**
	 * Because there are strings like "custom category", but should be "ProjectPractice"
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
