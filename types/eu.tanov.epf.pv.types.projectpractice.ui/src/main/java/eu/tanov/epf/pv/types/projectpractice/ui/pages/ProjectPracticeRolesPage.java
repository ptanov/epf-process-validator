package eu.tanov.epf.pv.types.projectpractice.ui.pages;

import org.eclipse.epf.authoring.ui.AuthoringUIText;
import org.eclipse.epf.library.edit.itemsfilter.FilterConstants;
import org.eclipse.epf.uma.Role;
import org.eclipse.ui.forms.editor.FormEditor;

import eu.tanov.epf.pv.types.projectpractice.common.util.ProjectPracticeHelper;
import eu.tanov.epf.pv.types.projectpractice.ui.i18n.ProjectPracticeUIResources;
import eu.tanov.epf.pv.types.projectpractice.ui.provider.ProjectPracticeItemProvider;
import eu.tanov.epf.pv.ui.common.pages.AbstractCustomCategoryPage;
import eu.tanov.epf.pv.ui.common.util.EditorHelper;

public class ProjectPracticeRolesPage extends AbstractCustomCategoryPage<Role> {
	private static final String FORM_PAGE_ID = "projectPracticeRolesPage"; //$NON-NLS-1$	

	public ProjectPracticeRolesPage(FormEditor editor) {
		super(editor, FORM_PAGE_ID, AuthoringUIText.ROLES_PAGE_TITLE, Role.class, ProjectPracticeUIResources.projectPractice_text);
		EditorHelper.updateTitleImage(editor, ProjectPracticeItemProvider.getProjectPracticeImage());
	}

	@Override
	protected String tabString() {
		return FilterConstants.ROLES;
	}

	@Override
	protected String multipleSelectDescription(int count) {
		return ProjectPracticeUIResources.bind(ProjectPracticeUIResources.projectPracticeRolesPage_multipleSelectDescription,
				new Integer(count));
	}

	@Override
	protected String sectionDescription() {
		return ProjectPracticeUIResources.projectPracticeRolesPage_sectionDescription;
	}

	@Override
	protected String sectionName() {
		return ProjectPracticeUIResources.projectPracticeRolesPage_sectionName;
	}

	@Override
	protected String selectedLabel() {
		return ProjectPracticeUIResources.projectPracticeRolesPage_selectedLabel;
	}

	@Override
	protected String[] modelStructurePath() {
		return ProjectPracticeHelper.PROJECT_PRACTICES_PATH;
	}

}
