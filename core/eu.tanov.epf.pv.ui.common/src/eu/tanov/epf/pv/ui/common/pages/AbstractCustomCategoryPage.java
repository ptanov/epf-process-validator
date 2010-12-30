package eu.tanov.epf.pv.ui.common.pages;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.epf.authoring.ui.filters.ContentFilter;
import org.eclipse.epf.authoring.ui.forms.AssociationFormPage;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.util.CategorySortHelper;
import org.eclipse.epf.library.edit.util.ContentElementOrderList;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.util.LibraryManager;
import org.eclipse.epf.uma.ContentElement;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.DescribableElement;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;

import eu.tanov.epf.pv.ui.common.util.FilteredContentElementOrderList;
import eu.tanov.epf.pv.ui.common.util.FormHelper;

public abstract class AbstractCustomCategoryPage<T extends DescribableElement> extends AssociationFormPage {

	protected CustomCategory container;

	private FilteredContentElementOrderList<T> allSteps;

	protected final String pageId;
	protected final Class<T> clazz;
	protected final String classNameForFormTitle;

	public AbstractCustomCategoryPage(FormEditor editor, String pageId, String title, Class<T> clazz, String classNameForFormTitle) {
		super(editor, pageId, title);
		this.pageId = pageId;
		this.clazz = clazz;
		this.classNameForFormTitle = classNameForFormTitle;
	}

	@Override
	protected void createFormContent(IManagedForm managedForm) {
		super.createFormContent(managedForm);

		FormHelper.updateFormText(form, classNameForFormTitle, contentElement);
	}

	@Override
	protected void addListeners() {
		super.addListeners();

		FormHelper.replaceLastListener(form, SWT.Activate, new Listener() {
			public void handleEvent(Event e) {
				FormHelper.updateFormText(form, classNameForFormTitle, contentElement);

				refreshViewers();
				if (TngUtil.isLocked(contentElement)) {
					enableControls(false);
				} else {
					enableControls(true);
				}
			}
		}, "org.eclipse.epf.authoring.ui.forms.AssociationFormPage");

		FormHelper.replaceLastListener(form, SWT.Deactivate, new Listener() {
			public void handleEvent(Event e) {
				FormHelper.updateFormText(form, classNameForFormTitle, contentElement);

				refreshViewers();
				if (TngUtil.isLocked(contentElement)) {
					enableControls(false);
				} else {
					enableControls(true);
				}
			}
		}, "org.eclipse.epf.authoring.ui.forms.AssociationFormPage");
	}

	@Override
	public void init(IEditorSite site, IEditorInput input) {
		super.init(site, input);
		container = (CustomCategory) contentElement;
		setUseCategory2(false);
		setUseCategory3(false);
		setAllowChange1(true);
		setIsUpAndDownButtonsRequired1(true);
	}

	@Override
	protected void initContentProviderSelected() {
		contentProviderSelected = new AdapterFactoryContentProvider(
				TngAdapterFactory.INSTANCE.getNavigatorView_ComposedAdapterFactory()) {

			public Object[] getElements(Object object) {
				if (allSteps == null) {
					allSteps = new FilteredContentElementOrderList<T>(contentElement, getOrderFeature(), clazz);
				}
				final List<Object> returnList = CategorySortHelper.sortCategoryElements(contentElement,
						allSteps.toFilteredArray());
				return returnList.toArray();
			}
		};
		viewer_selected.setContentProvider(contentProviderSelected);
	}

	protected CustomCategory addToCategory(IActionManager actionMgr, CustomCategory categoryToModify, T objectToAdd) {
		final EStructuralFeature feature = UmaPackage.eINSTANCE.getCustomCategory_CategorizedElements();
		return (CustomCategory) LibraryManager.getInstance().addToCategory(actionMgr, categoryToModify, objectToAdd, feature,
				getModelStructurePath(), true, usedCategories);
	}

	protected boolean removeFromCategory(IActionManager actionMgr, CustomCategory categoryToModify, T objectToRemove) {
		final EStructuralFeature feature = UmaPackage.eINSTANCE.getCustomCategory_CategorizedElements();
		return LibraryManager.getInstance().removeFromCategory(actionMgr, categoryToModify, objectToRemove, feature,
				getModelStructurePath(), usedCategories);
	}

	@Override
	protected void addItemsToModel1(@SuppressWarnings("rawtypes") ArrayList objectsToAdd) {
		@SuppressWarnings("unchecked")
		final List<T> casted = objectsToAdd;
		for (T task : casted) {
			addToCategory(getActionManager(), container, task);
		}
	}

	@Override
	protected void removeItemsFromModel1(@SuppressWarnings("rawtypes") ArrayList tasksToRemove) {
		@SuppressWarnings("unchecked")
		final List<T> casted = tasksToRemove;
		for (T task : casted) {
			removeFromCategory(getActionManager(), container, task);
		}
	}

	@Override
	protected Object getContentElement() {
		return container;
	}

	@Override
	protected final String getTabString() {
		return tabString();
	}

	protected abstract String tabString();

	@Override
	protected IFilter getFilter() {
		return filter = new ContentFilter() {
			protected boolean childAccept(Object obj) {
				if (obj instanceof ContentElement) {
					if (getHelper().isContributor((ContentElement) obj))
						return false;
				}
				return clazz.isInstance(obj);
			}
		};
	}

	@Override
	protected final String getMultipleSelectDescription(int count) {
		return multipleSelectDescription(count);
	}

	protected abstract String multipleSelectDescription(int count);

	@Override
	protected final String getSectionDescription() {
		return sectionDescription();
	}

	protected abstract String sectionDescription();

	@Override
	protected final String getSectionName() {
		return sectionName();
	}

	protected abstract String sectionName();

	@Override
	protected final String getSelectedLabel() {
		return selectedLabel();
	}

	protected abstract String selectedLabel();

	@Override
	protected EStructuralFeature getOrderFeature() {
		return UmaPackage.eINSTANCE.getCustomCategory_CategorizedElements();
	}

	@Override
	protected final String[] getModelStructurePath() {
		return modelStructurePath();
	}

	protected abstract String[] modelStructurePath();

	@Override
	protected ContentElementOrderList getContentElementOrderList() {
		return allSteps;
	}

}
