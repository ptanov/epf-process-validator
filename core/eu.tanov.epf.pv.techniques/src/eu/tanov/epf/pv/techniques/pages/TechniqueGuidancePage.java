package eu.tanov.epf.pv.techniques.pages;

import org.eclipse.epf.authoring.ui.forms.ContentElementGuidancePage;
import org.eclipse.ui.forms.editor.FormEditor;

import eu.tanov.epf.pv.techniques.i18n.TechniquesUIResources;

public class TechniqueGuidancePage extends ContentElementGuidancePage {

	public TechniqueGuidancePage(FormEditor editor) {
		super(editor);
	}

	@Override
	protected String getSectionDescription() {
		return TechniquesUIResources.technique_guidancepage_sectiondescription;
	}
}
