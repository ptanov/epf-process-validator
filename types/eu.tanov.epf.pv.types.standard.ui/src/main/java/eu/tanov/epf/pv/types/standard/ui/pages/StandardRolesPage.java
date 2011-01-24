package eu.tanov.epf.pv.types.standard.ui.pages;

import org.eclipse.epf.authoring.ui.AuthoringUIText;
import org.eclipse.epf.library.edit.itemsfilter.FilterConstants;
import org.eclipse.epf.uma.Role;
import org.eclipse.ui.forms.editor.FormEditor;

import eu.tanov.epf.pv.types.standard.common.util.StandardHelper;
import eu.tanov.epf.pv.types.standard.ui.i18n.StandardUIResources;
import eu.tanov.epf.pv.types.standard.ui.provider.StandardItemProvider;
import eu.tanov.epf.pv.ui.common.pages.AbstractCustomCategoryPage;
import eu.tanov.epf.pv.ui.common.util.EditorHelper;

public class StandardRolesPage extends AbstractCustomCategoryPage<Role> {
	private static final String FORM_PAGE_ID = "standardRolesPage"; //$NON-NLS-1$	

	public StandardRolesPage(FormEditor editor) {
		super(editor, FORM_PAGE_ID, AuthoringUIText.ROLES_PAGE_TITLE, Role.class, StandardUIResources.standard_text);
		EditorHelper.updateTitleImage(editor, StandardItemProvider.getStandardImage());
	}

	@Override
	protected String tabString() {
		return FilterConstants.ROLES;
	}

	@Override
	protected String multipleSelectDescription(int count) {
		return StandardUIResources.bind(StandardUIResources.standardRolesPage_multipleSelectDescription, new Integer(count));
	}

	@Override
	protected String sectionDescription() {
		return StandardUIResources.standardRolesPage_sectionDescription;
	}

	@Override
	protected String sectionName() {
		return StandardUIResources.standardRolesPage_sectionName;
	}

	@Override
	protected String selectedLabel() {
		return StandardUIResources.standardRolesPage_selectedLabel;
	}

	@Override
	protected String[] modelStructurePath() {
		return StandardHelper.STANDARDS_PATH;
	}

}
