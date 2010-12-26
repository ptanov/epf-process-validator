package eu.tanov.epf.itemprovider.providers;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.category.TransientCategoryPackageItemProvider;
import org.eclipse.epf.uma.ContentCategory;
import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.UmaPackage;

/**
 * TODO create extension point and move this class to different project
 */
public class TechniquesCategoryItemProvider extends
		TransientCategoryPackageItemProvider {

	/**
	 * Creates a new instance.
	 */
	public TechniquesCategoryItemProvider(AdapterFactory adapterFactory,
			ContentPackage target, String name) {
		super(adapterFactory, target, name);
	}
@Override
protected Command createAddCommand(EditingDomain domain, EObject owner,
		EReference feature, Collection<?> collection, int index) {
	// TODO Auto-generated method stub
	return super.createAddCommand(domain, owner, feature, collection, index);
}
@Override
protected Command createAddCommand(EditingDomain domain, EObject owner,
		EStructuralFeature feature, Collection collection, int index) {
	// TODO Auto-generated method stub
	return super.createAddCommand(domain, owner, feature, collection, index);
}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.category.TransientCategoryPackageItemProvider#isInherited(org.eclipse.epf.uma.ContentCategory)
	 */
	protected boolean isInherited(ContentCategory category) {
		return false;
		
//		(category instanceof Technique //|| category instanceof DisciplineGrouping
//				)
//				&& category.getVariabilityBasedOnElement() != null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#collectNewChildDescriptors(java.util.Collection,
	 *      java.lang.Object)
	 */
	protected void collectNewChildDescriptors(Collection newChildDescriptors,
			Object object) {
//		newChildDescriptors.add(createChildParameter(UmaPackage.eINSTANCE
//				.getContentPackage_ContentElements(), UmaFactory.eINSTANCE
//				.createDisciplineGrouping()));
		newChildDescriptors.add(createChildParameter(
				UmaPackage.eINSTANCE.getContentPackage_ContentElements(),
				UmaFactory.eINSTANCE.createCustomCategory()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getChildrenFeatures(java.lang.Object)
	 */
	public Collection getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			childrenFeatures = new ArrayList();
			childrenFeatures.add(UmaPackage.eINSTANCE
					.getContentPackage_ContentElements());
		}
		return childrenFeatures;
	}

	public static boolean accept(Object obj) {
		if (obj instanceof CustomCategory) {
			return true;
		}
		return false;
//		if (obj instanceof DisciplineGrouping) {
//			return true;
//		}
//		if (obj instanceof Technique) {
//			List groups = AssociationHelper.getDisciplineGroups((Discipline) obj);
//			if (groups.isEmpty()) {
//				return true;
//			} else {
//				for (Object group : groups) {
//					if (group instanceof DisciplineGrouping) {
//						if (UmaUtil.getMethodPlugin((DisciplineGrouping)group) == UmaUtil.getMethodPlugin((Discipline)obj)) {
//							return false;
//						}
//					}
//				}
//				return true;
//			}
//		}
//		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.TransientGroupItemProvider#acceptAsChild(java.lang.Object)
	 */
	protected boolean acceptAsChild(Object obj) {
		if (!super.acceptAsChild(obj))
			return false;
		return accept(obj);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getImage(java.lang.Object)
	 */
	public Object getImage(Object object) {
		return LibraryEditPlugin.INSTANCE.getImage("full/obj16/Disciplines"); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.TransientGroupItemProvider#getChildren(java.lang.Object)
	 */
	public Collection getChildren(Object object) {
		Collection children = super.getChildren(object);

//		for (Iterator iter = children.iterator(); iter.hasNext();) {
//			Object element = iter.next();
//			if (element instanceof Technique) {
//				Technique child = (Technique) element;
////				ReflectiveItemProvider itemProvider = (ReflectiveItemProvider) TngUtil
////						.getBestAdapterFactory(adapterFactory).adapt(child,
////								ITreeItemContentProvider.class);
////				TechniqueItemProvider itemProvider = new TechniqueItemProvider(adapterFactory);
//				TechniqueItemProvider itemProvider = new TechniqueItemProvider(TngUtil
//						.getBestAdapterFactory(adapterFactory));
//				itemProvider.setParent(object);
//			}
//		}

		return children;
	}

	public void setDefaultName(Object obj) {
//		if (obj instanceof CustomCategory) {
//			TngUtil.setDefaultName(TngUtil.extract(((ContentPackage) target)
//					.getContentElements(), CustomCategory.class),
//					(MethodElement) obj, "new_technique" 
////					LibraryEditConstants.NEW_DISCIPLINE
//					);
////		} else if (obj instanceof DisciplineGrouping) {
////			TngUtil.setDefaultName(TngUtil.extract(((ContentPackage) target)
////					.getContentElements(), DisciplineGrouping.class),
////					(MethodElement) obj,
////					LibraryEditConstants.NEW_DISCIPLINE_GROUPING);
//		}
	}

	
	@Override
	public String getCreateChildText(Object owner, Object feature,
			Object child, Collection<?> selection) {
		if (child instanceof CustomCategory) {
			//TODO i18n MyEditPlugin.INSTANCE.getString("Techniques_child");
			return "Technique";
		}
		//TODO throw:
		return super.getCreateChildText(owner, feature, child, selection);
	}
}
