# Introduction #
Every view has "pages" (or "tabs").
Every page is `Control`, `IFormPage` or `IEditorPart` (there is class `DescriptionFormPage` for easy use).
Pages are get from `MethodElementEditorDefaultPageProvider.getPages()`

`MethodElementEditorDefaultPageProvider` is get from `MethodElementEditor.getDefaultPageProvider()`

`MethodElementEditor` is contributed with
```
   <extension point="org.eclipse.ui.editors">
      <editor
            class="org.eclipse.epf.authoring.ui.editors.MethodElementEditor"
            contributorClass="org.eclipse.epf.authoring.ui.actions.MethodLibraryActionBarContributor"
            icon="icons/full/obj16/MethodElement.gif"
            id="org.eclipse.epf.authoring.ui.editors.MethodElementEditor"
            name="%methodElementEditorName"
            matchingStrategy="org.eclipse.epf.authoring.ui.editors.MethodElementEditorMatchingStrategy"/>
```

So this contribution must be overrided.

**There is variant 2:**
There is extension point with id `MethodElementEditorPageProviders` it is **better** to use it instead.

Contribution is:
```
   <extension
         point="org.eclipse.epf.authoring.ui.MethodElementEditorPageProviders">
      <editorPage
            class="eu.tanov.epf.pv.techniques.extension.TechniquePageProviderExtension">
      </editorPage>
   </extension>
```

where `TechniquePageProviderExtension implements IMethodElementEditorPageProviderExtension` and can add/remove pages for different types:

```
		@Override
	public Map<Object, String> getPages(Map<Object, String> pageMap,
			FormEditor editor, Object input) {
		if (input instanceof CustomCategory) {
			pageMap.put(new TechniqueTasksPage(editor), null);
...
		}
		return pageMap;
	}

```

# Page for custom view #
Default approach is to extend AssociationFormPage (if this will be page for selecting content from list).
It provides 3 categories that can be visible or hidden depending on `init()` method.

  * Constructor should be something like:
```
	public TechniqueTasksPage(FormEditor editor) {
		super(editor, FORM_PAGE_ID, AuthoringUIText.TASKS_PAGE_TITLE);
	}
```
    * FORM\_PAGE\_ID should be unique page identifier
    * third argument is title/tab text
  * method `init()`
> It is called after constructor. Object that will be displayed on this page is passed and is set to `contentElement` after `super.init()`.
> Different category panes can be enabled or disabled in this method.
  * method `createFormContent()`
> Creates SWT form content.
> Title of form is set here, so if custom title is used - should be set after `super.createFormContent()`
  * method `addListeners()`
> Used to add some listeners to form, like activate/deactivate and refresh view if fired.
> If custom title is used - should be replaced with custom implementations - used `FormHelper.replaceLastListener()` which is hack for that - **TODO** how to replace safely listeners and keep custom title?
  * method `getSectionName/Description()`
> Returns info about section
  * method `get***Label/2/3()`
> Labels for selections
  * method `initContentProviderSelected()`
> Creates object that will return elements that are included in category.
Elements are get from specified structural feature (`UmaPackage.eINSTANCE.getCustomCategory_CategorizedElements()`) and FilteredContentElementOrderList is used to order elements in result and to filter elements not from desired type (e.g. only Tasks)
  * method `getOrderFeature()`
> Feature that contains selection.
  * method `getContentElement()`
> Should return object that is be displayed on this page
  * method `getFilter()`
> Creates filter that will be used to filter elements that can be added to selection (e.g. `return (obj instanceof Task);`). Here self objects should be excluded (already selected):
```
	protected IFilter getFilter() {
		return filter = new ContentFilter() {
			protected boolean childAccept(Object obj) {
				if (obj instanceof ContentElement) {
					if (getHelper().isContributor((ContentElement) obj))
						return false;
				}
				...
			}
		};
	}
```
  * method `getTabString()`
> Text for type in selection dialog (add).

  * method `addItemsToModel1/2/3(items)`
> Is called when selection is made, even if nothing is selected. Here they should physically added to list.
    * method `removeItemsFromModel1/2/3(items)`
> Is called when removing is made. Here they should physically removed from list.
    * method `getContentElementOrderList()`
> Called when UP/DOWN buttons are used - to reorder collection. Can be cache of list returned by object created in `initContentProviderSelected()`.


  * method `getMultipleSelectDescription(count)`
> Text that will be displayed in "Brief description of selected element:" when more than one element is selected (e.g. for removing)

# Class AbstractCustomCategoryPage #
This class is created in order to simplify custom category page creation. The only thing to do is to define type (class) of items that should be displayed and some localized strings, e.g.:

```
public class TechniqueTasksPage extends AbstractCustomCategoryPage<Task> {
	private static final String FORM_PAGE_ID = "techniqueTasksPage"; //$NON-NLS-1$	

	public TechniqueTasksPage(FormEditor editor) {
		super(editor, FORM_PAGE_ID, AuthoringUIText.TASKS_PAGE_TITLE, Task.class, TechniquesUIResources.technique_text);
	}

	@Override
	protected String tabString() {
		return FilterConstants.TASKS;
	}

	@Override
	protected String multipleSelectDescription(int count) {
		return TechniquesUIResources.bind(TechniquesUIResources.techniqueTasksPage_multipleSelectDescription, new Integer(count));
	}

	@Override
	protected String sectionDescription() {
		return TechniquesUIResources.techniqueTasksPage_sectionDescription;
	}

	@Override
	protected String sectionName() {
		return TechniquesUIResources.techniqueTasksPage_sectionName;
	}

	@Override
	protected String selectedLabel() {
		return TechniquesUIResources.techniqueTasksPage_selectedLabel;
	}

	@Override
	protected String[] modelStructurePath() {
		return TechniquesCategoryItemProvider.TECHNIQUES_PATH;
	}
}
```