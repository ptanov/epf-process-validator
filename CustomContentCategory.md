# Introduction #

Custom content category is content category like Discipline, but containing more (or less) features, e.g. "Tasks", "Work Products" and "Guidelines".

# Approaches #
## Approach 1: New type, extend UMA.ecore ##
New .ecore file with new type should be created with type that extends `CustomCategory`:
```
<?xml version="1.0" encoding="UTF-8"?>
<ecore:EPackage xmi:version="2.0"
    xmlns:xmi="http://www.omg.org/XMI" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:ecore="http://www.eclipse.org/emf/2002/Ecore" name="uext"
    nsURI="http://www.eclipse.org/epf/uext/1.0.6" nsPrefix="uext">
  <eClassifiers xsi:type="ecore:EClass" name="Technique" eSuperTypes="../../../org.eclipse.epf.uma/model/1.0.6/uma.ecore#//CustomCategory">
    <eStructuralFeatures xsi:type="ecore:EReference" name="tasks" ordered="false"
        upperBound="-1" eType="ecore:EClass ../../../org.eclipse.epf.uma/model/1.0.6/uma.ecore#//Task"/>
    <eStructuralFeatures xsi:type="ecore:EReference" name="workProducts" ordered="false"
        upperBound="-1" eType="ecore:EClass ../../../org.eclipse.epf.uma/model/1.0.6/uma.ecore#//WorkProduct"/>
  </eClassifiers>
</ecore:EPackage>
```

and respective extensions should be defined:
```
   <extension point="org.eclipse.emf.ecore.generated_package">
      <package
            uri="http://www.eclipse.org/epf/uext/1.0.6"
            class="uext.UextPackage"
            genModel="src/model/My.genmodel"/>
   </extension>

   <extension point="org.eclipse.emf.ecore.extension_parser">
      <parser
            type="uext"
            class="uext.util.UextResourceFactoryImpl"/>
   </extension>
```

`CustomCategory` should be extended because there is such code in default editor chooser (`org.eclipse.epf.authoring.ui.editors.EditorChooser`):
```
			} else if ((obj instanceof MethodPlugin)
					|| (obj instanceof ContentPackage) || (obj instanceof Role)
					|| (obj instanceof Task) || (obj instanceof WorkProduct)
					|| (obj instanceof Guidance) || (obj instanceof Discipline)
					|| (obj instanceof DisciplineGrouping)
					|| (obj instanceof Domain)
					|| (obj instanceof WorkProductType)
					|| (obj instanceof RoleSet) || (obj instanceof Tool)
					|| (obj instanceof RoleSetGrouping)
					|| (obj instanceof MethodLibrary)) {
				openEditor((MethodElement) obj, MethodElementEditor.EDITOR_ID);
			} else if (obj instanceof CustomCategory) {
				CustomCategory custCat = (CustomCategory) obj;
				if(TngUtil.isRootCustomCategory(custCat)) {
					return;
				}
				openEditor((MethodElement) obj, MethodElementEditor.EDITOR_ID);
```
ContentCategory is not present so openEditor() is never called for not-a-CustomCategory-types.

Model code and edit code should be generated from genmodel.
In edit code extension should be defined:
```
   <extension point="org.eclipse.emf.edit.itemProviderAdapterFactories">
      <factory
            uri="http://www.eclipse.org/epf/uext/1.0.6"
            class="uext.provider.UextItemProviderAdapterFactory"
            supportedTypes=
              "org.eclipse.emf.edit.provider.IEditingDomainItemProvider
               org.eclipse.emf.edit.provider.IStructuredItemContentProvider
               org.eclipse.emf.edit.provider.ITreeItemContentProvider
               org.eclipse.emf.edit.provider.IItemLabelProvider
               org.eclipse.emf.edit.provider.IItemPropertySource"/>
   </extension>
```

In custom category item provider children descriptor and children-accept should be added:
```
public class TechniqueCategoriesItemProvider extends
		TransientCategoryPackageItemProvider {

	protected void collectNewChildDescriptors(Collection newChildDescriptors,
			Object object) {

		newChildDescriptors.add(createChildParameter(UmaPackage.eINSTANCE
				.getContentPackage_ContentElements(), UextFactory.eINSTANCE.createTechnique()));
	}


	public static boolean accept(Object obj) {
		if (obj instanceof Technique) {
			return true;
		}
		return false;
	}
...
}
```

**TODO** _what else have to be done (I don't remember)?_


Then pages for custom content category should be created as described in [ViewForCustomType](ViewForCustomType.md).




**Disadvantages**
  1. A lot of hacks should be done in order to make this works so in next version of EPF it is likely to be not working (and rewriting will be required).
  1. Compatibility - if EPF library, containing custom content category, is opened by pure EPF (without these plugins) - custom content categories will be silently **removed** which is very dangerous.


## Approach 2: use CustomCategory ##
CustomCategory is ContentCategory which have only one list that contains all categorized elements.

In custom category item provider children descriptor, children-accept and child name should be added:
```
public class TechniquesCategoryItemProvider extends
		TransientCategoryPackageItemProvider {

	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		newChildDescriptors.add(createChildParameter(UmaPackage.eINSTANCE.getContentPackage_ContentElements(),
				UmaFactory.eINSTANCE.createCustomCategory()));
	}


	private static boolean accept(Object obj) {
		if (obj instanceof CustomCategory) {
			return true;
		}
		return false;
	}

	@Override
	protected boolean acceptAsChild(Object obj) {
		if (!super.acceptAsChild(obj)) {
			return false;
		}
		return accept(obj);
	}

//change child default name:
	@Override
	public String getCreateChildText(Object owner, Object feature,
			Object child, Collection<?> selection) {
		if (child instanceof CustomCategory) {
			//TODO i18n MyEditPlugin.INSTANCE.getString("Techniques_child");
			return "Technique";
		}
...
	}


...
}
```


Then pages for custom content category should be created as described in [ViewForCustomType](ViewForCustomType.md).




**Disadvantages**
  1. All features that will be contained in CustomCategory should be stored together in only one collection - it is not possible to create more fields/collections, e.g. Tasks, WorkProducts, etc.
  1. View for this CustomCategory will be harder to write because it is impossible to describe desired list only with its structural feature, but with type of items in it, too. **Note** in order to decrease/remove negative effect of this disadvantage class `AbstractCustomCategoryPage` is created, more info here [AbstractCustomCategoryPage](ViewForCustomType#Class.md)
  1. Checking for custom type is not done by `instanceof CustomCategory` (because `CustomCategory` can be not a technique), but with parent name, etc.. Helper method can be used, e.g.:
```
public class TechniquesHelper {
...
	public static boolean isTechnique(Object o) {
		if (!(o instanceof CustomCategory)) {
			return false;
		}
		final CustomCategory customCategory = (CustomCategory) o;

		if (!(customCategory.eContainer() instanceof ContentPackage)) {
			return false;
		}
		final ContentPackage contentPackage = (ContentPackage)customCategory.eContainer();
		
		return TechniquesCategoryItemProvider.TECHNIQUES_NAME.equals(contentPackage.getName());
	}
}

```