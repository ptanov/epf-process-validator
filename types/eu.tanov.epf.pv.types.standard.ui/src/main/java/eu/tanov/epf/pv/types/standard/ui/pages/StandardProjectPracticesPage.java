package eu.tanov.epf.pv.types.standard.ui.pages;

import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.ui.forms.editor.FormEditor;

import eu.tanov.epf.pv.service.types.handler.CustomTypeHandler;
import eu.tanov.epf.pv.service.types.service.CustomTypeHandlersService;
import eu.tanov.epf.pv.types.projectpractice.common.util.ProjectPracticeHelper;
import eu.tanov.epf.pv.types.standard.common.util.StandardHelper;
import eu.tanov.epf.pv.types.standard.ui.StandardActivator;
import eu.tanov.epf.pv.types.standard.ui.i18n.StandardUIResources;
import eu.tanov.epf.pv.types.standard.ui.provider.StandardItemProvider;
import eu.tanov.epf.pv.ui.common.pages.AbstractCustomTypeCategoryPage;
import eu.tanov.epf.pv.ui.common.util.EditorHelper;

public class StandardProjectPracticesPage extends AbstractCustomTypeCategoryPage<CustomCategory> {
	private static final String FORM_PAGE_ID = "standardProjectPracticesPage"; //$NON-NLS-1$	

	public StandardProjectPracticesPage(FormEditor editor) {
		super(editor, FORM_PAGE_ID, StandardUIResources.standardProjectPracticesPage_title, getTypeHelper(),
				StandardUIResources.standard_text);
		EditorHelper.updateTitleImage(editor, StandardItemProvider.getStandardImage());
	}

	private static CustomTypeHandler<CustomCategory> getTypeHelper() {
		final CustomTypeHandlersService service = StandardActivator.getDefault().getService(
				CustomTypeHandlersService.class);
		return service.getHandlerForType(ProjectPracticeHelper.getCustomType(), CustomCategory.class);
	}

	@Override
	protected String tabString() {
		// FIXME: filter techniques
		return StandardUIResources.standardProjectPracticesPage_filterTabString;
	}

	@Override
	protected String multipleSelectDescription(int count) {
		return StandardUIResources.bind(
				StandardUIResources.standardProjectPracticesPage_multipleSelectDescription, new Integer(count));
	}

	@Override
	protected String sectionDescription() {
		return StandardUIResources.standardProjectPracticesPage_sectionDescription;
	}

	@Override
	protected String sectionName() {
		return StandardUIResources.standardProjectPracticesPage_sectionName;
	}

	@Override
	protected String selectedLabel() {
		return StandardUIResources.standardProjectPracticesPage_selectedLabel;
	}

	@Override
	protected String[] modelStructurePath() {
		return StandardHelper.STANDARDS_PATH;
	}

}
