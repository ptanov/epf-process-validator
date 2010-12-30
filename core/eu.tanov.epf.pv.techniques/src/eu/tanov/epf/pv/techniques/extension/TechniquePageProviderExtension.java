package eu.tanov.epf.pv.techniques.extension;

import java.util.Map;

import org.eclipse.epf.authoring.ui.forms.ContentElementGuidancePage;
import org.eclipse.epf.authoring.ui.providers.IMethodElementEditorPageProviderExtension;
import org.eclipse.ui.forms.editor.FormEditor;

import eu.tanov.epf.pv.techniques.pages.TechniqueTasksPage;
import eu.tanov.epf.pv.techniques.pages.TechniqueWorkProductsPage;
import eu.tanov.epf.pv.techniques.util.TechniquesHelper;

public class TechniquePageProviderExtension implements IMethodElementEditorPageProviderExtension {

	@Override
	public Map<Object, String> getPages(Map<Object, String> pageMap, FormEditor editor, Object input) {
		if (TechniquesHelper.isTechnique(input)) {
			pageMap.put(new TechniqueTasksPage(editor), null);
			pageMap.put(new TechniqueWorkProductsPage(editor), null);
			pageMap.put(new ContentElementGuidancePage(editor), null);
		}

		return pageMap;
	}

}
