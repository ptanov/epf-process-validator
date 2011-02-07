package eu.tanov.epf.pv.types.projectpractice.ui.pages;

import org.eclipse.epf.uma.Practice;
import org.eclipse.ui.forms.editor.FormEditor;

import eu.tanov.epf.pv.types.projectpractice.common.util.ProjectPracticeHelper;
import eu.tanov.epf.pv.types.projectpractice.ui.i18n.ProjectPracticeUIResources;
import eu.tanov.epf.pv.types.projectpractice.ui.provider.ProjectPracticeItemProvider;
import eu.tanov.epf.pv.ui.common.pages.AbstractCustomCategoryPage;
import eu.tanov.epf.pv.ui.common.util.EditorHelper;

/**
 * TODO why practice could not be referenced in guidelines?
 */
public class ProjectPracticePracticeGuidancesPage extends AbstractCustomCategoryPage<Practice> {
	private static final String FORM_PAGE_ID = "projectPracticePracticeGuidancesPage"; //$NON-NLS-1$	

	public ProjectPracticePracticeGuidancesPage(FormEditor editor) {
		// TODO i18n
		super(editor, FORM_PAGE_ID, "Practice Guidances", Practice.class, ProjectPracticeUIResources.projectPractice_text);
		EditorHelper.updateTitleImage(editor, ProjectPracticeItemProvider.getProjectPracticeImage());
	}

	@Override
	protected String tabString() {
		// FIXME: filter practice guidances
		return ProjectPracticeUIResources.projectPracticePracticeGuidancesPage_filterTabString;
	}

	@Override
	protected String multipleSelectDescription(int count) {
		return ProjectPracticeUIResources.bind(
				ProjectPracticeUIResources.projectPracticePracticeGuidancesPage_multipleSelectDescription, new Integer(count));
	}

	@Override
	protected String sectionDescription() {
		return ProjectPracticeUIResources.projectPracticePracticeGuidancesPage_sectionDescription;
	}

	@Override
	protected String sectionName() {
		return ProjectPracticeUIResources.projectPracticePracticeGuidancesPage_sectionName;
	}

	@Override
	protected String selectedLabel() {
		return ProjectPracticeUIResources.projectPracticePracticeGuidancesPage_selectedLabel;
	}

	@Override
	protected String[] modelStructurePath() {
		return ProjectPracticeHelper.PROJECT_PRACTICES_PATH;
	}

}
