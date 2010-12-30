package eu.tanov.epf.pv.techniques.pages;

import org.eclipse.epf.authoring.ui.forms.CustomCategoryDescriptionPage;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.forms.editor.FormEditor;

import eu.tanov.epf.pv.techniques.i18n.TechniquesUIResources;

public class TechniqueDescriptionPage extends CustomCategoryDescriptionPage {

	public TechniqueDescriptionPage(FormEditor editor) {
		super(editor);
	}
	
	@Override
	public void init(IEditorSite site, IEditorInput input) {
		super.init(site, input);
		
		//without variability - if on - it searches in custom categories, not in techniques
		//but it is not used - so disable
		this.variabilitySectionOn = false;
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
