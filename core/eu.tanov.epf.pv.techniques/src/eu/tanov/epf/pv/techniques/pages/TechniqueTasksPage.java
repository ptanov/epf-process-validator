package eu.tanov.epf.pv.techniques.pages;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.AuthoringUIText;
import org.eclipse.epf.authoring.ui.filters.ContentFilter;
import org.eclipse.epf.authoring.ui.forms.AssociationFormPage;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.itemsfilter.FilterConstants;
import org.eclipse.epf.library.edit.util.CategorySortHelper;
import org.eclipse.epf.library.edit.util.ContentElementOrderList;
import org.eclipse.epf.library.edit.util.ModelStructure;
import org.eclipse.epf.uma.ContentElement;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.Task;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.forms.editor.FormEditor;

import eu.tanov.epf.itemprovider.util.MethodPluginHelper;
import eu.tanov.epf.pv.techniques.util.ExtLibraryManager;
import eu.tanov.epf.pv.techniques.util.FilteredContentElementOrderList;


public class TechniqueTasksPage extends AssociationFormPage {

	private static final String FORM_PAGE_ID = "disciplineTasksPage"; //$NON-NLS-1$	

	private CustomCategory discipline;
	
	private FilteredContentElementOrderList<Task> allSteps;

	/**
	 * Creates a new instance.
	 */
	public TechniqueTasksPage(FormEditor editor) {
		super(editor, FORM_PAGE_ID, AuthoringUIText.TASKS_PAGE_TITLE);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#init(org.eclipse.ui.IEditorSite, org.eclipse.ui.IEditorInput)
	 */
	public void init(IEditorSite site, IEditorInput input) {
		super.init(site, input);
		discipline = (CustomCategory) contentElement;
		setUseCategory2(false);
		setUseCategory3(false);
		setAllowChange1(true);
		setIsUpAndDownButtonsRequired1(true);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#initContentProviderSelected()
	 */
	protected void initContentProviderSelected() {
		contentProviderSelected = new AdapterFactoryContentProvider(
				TngAdapterFactory.INSTANCE
						.getNavigatorView_ComposedAdapterFactory()) {

			public Object[] getElements(Object object) {
				//return ((Discipline) object).getTasks().toArray();
				if (allSteps == null) {
					allSteps = new FilteredContentElementOrderList<Task>(
							contentElement,
							getOrderFeature(), Task.class);
				}
				List<Object> returnList = CategorySortHelper.sortCategoryElements(contentElement, 
						allSteps.toFilteredArray());
				return returnList.toArray();
			}
		};
		viewer_selected.setContentProvider(contentProviderSelected);
	}



	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#addItemsToModel1(ArrayList)
	 */
	protected void addItemsToModel1(ArrayList addItems) {
		// Update the model.
		if (!addItems.isEmpty()) {
			for (Iterator it = addItems.iterator(); it.hasNext();) {
				Task task = (Task) it.next();
				
				//	check to maintain many-to-one rule for domain-workproduct
//				List disciplines = AssociationHelper.getDisciplines(task);
//				if (disciplines != null && disciplines.size() > 0)	{
//					Object associatedDiscipline = disciplines.get(0);
//					String msg = MessageFormat
//					.format(
//							LibraryEditResources.UserInteractionHelper_errRelationshipExists,
//							new Object[] {
//									task.getName(),
//									TngUtil
//											.getLabelWithPath(associatedDiscipline),
//									discipline.getName() });
//					Messenger.INSTANCE.showWarning(
//							LibraryEditResources.errorDialog_title, msg);
//					return;
//				}
				
				ExtLibraryManager.getExtInstance().addToTechnique(
						getActionManager(), discipline, task, usedCategories);
			}
		}
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#removeItemsFromModel1(ArrayList)
	 */
	protected void removeItemsFromModel1(ArrayList rmItems) {
		// Update the model.
		if (!rmItems.isEmpty()) {
			for (Iterator it = rmItems.iterator(); it.hasNext();) {
				Task task = (Task) it.next();
				ExtLibraryManager.getExtInstance().removeFromTechnique(
						getActionManager(), discipline, task, usedCategories);
			}
		}
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#getContentElement()
	 */
	protected Object getContentElement() {
		return discipline;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#getTabString()
	 */
	protected String getTabString() {
		return FilterConstants.TASKS;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.DescriptionFormPage#getFilter()
	 */
	protected IFilter getFilter() {
		return filter = new ContentFilter() {
			protected boolean childAccept(Object obj) {
				if (obj instanceof ContentElement) {
					if (getHelper().isContributor((ContentElement) obj))
						return false;
				}
				return (obj instanceof Task);
			}
		};
	}
		
	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#getMultipleSelectDescription(int)
	 */
	protected String getMultipleSelectDescription(int count) {
		return super.getMultipleSelectDescription(count, AuthoringUIResources.disciplineTasksPage_multipleSelectDescription);
	}
	
	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#getSectionDescription()
	 */
	protected String getSectionDescription() {
		return AuthoringUIResources.disciplineTasksPage_sectionDescription;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#getSectionName()
	 */
	protected String getSectionName() {
		return AuthoringUIResources.disciplineTasksPage_sectionName;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#getSelectedLabel()
	 */
	protected String getSelectedLabel() {
		return AuthoringUIResources.disciplineTasksPage_selectedLabel;
	}
	
	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#getSelectedLabel2()
	 */
	protected String getSelectedLabel2() {
		return AuthoringUIResources.disciplineTasksPage_selectedLabel;
	}
	
	/**
	 * @see org.eclipse.epf.authoring.ui.forms.AssociationFormPage#getSelectedLabel3()
	 */
	protected String getSelectedLabel3() {
		return AuthoringUIResources.disciplineTasksPage_selectedLabel;
	}

	@Override
	protected EStructuralFeature getOrderFeature() {
		return UmaPackage.eINSTANCE.getCustomCategory_CategorizedElements();
//		return UextPackage.eINSTANCE.getTechnique_Tasks();
	}
	
	@Override
	protected String[] getModelStructurePath() {
		return MethodPluginHelper.TECHNIQUES_PATH;
	}
	
	@Override
	protected ContentElementOrderList getContentElementOrderList() {
		return allSteps;
	}

}
