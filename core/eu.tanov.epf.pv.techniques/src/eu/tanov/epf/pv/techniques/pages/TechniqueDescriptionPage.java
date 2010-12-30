package eu.tanov.epf.pv.techniques.pages;

import org.eclipse.epf.authoring.ui.forms.CustomCategoryDescriptionPage;
import org.eclipse.ui.forms.editor.FormEditor;

import eu.tanov.epf.pv.techniques.i18n.TechniquesUIResources;

public class TechniqueDescriptionPage extends CustomCategoryDescriptionPage {

	public TechniqueDescriptionPage(FormEditor editor) {
		super(editor);
	}
	
	@Override
	public void loadSectionDescription() {
		this.generalSectionDescription = TechniquesUIResources.technique_generalInfoSection_desc;
		this.detailSectionDescription = TechniquesUIResources.technique_detailSection_desc;
		this.variabilitySectionDescription = TechniquesUIResources.technique_variabilitySection_desc;
		this.versionSectionDescription = TechniquesUIResources.technique_versionInfoSection_desc;
		this.iconSectionDescription =  TechniquesUIResources.technique_IconSection_desc;
	}

}
