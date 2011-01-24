package eu.tanov.epf.pv.types.standard.ui.provider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.epf.library.edit.category.TransientCategoryPackageItemProvider;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.ContentCategory;
import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.swt.graphics.Image;

import eu.tanov.epf.pv.types.standard.ui.StandardActivator;
import eu.tanov.epf.pv.types.standard.ui.i18n.StandardUIResources;

public class StandardsCategoryItemProvider extends TransientCategoryPackageItemProvider {
	private static final String NEW_NAME = "new_standard";

	/**
	 * Creates a new instance.
	 */
	public StandardsCategoryItemProvider(AdapterFactory adapterFactory, ContentPackage target, String name) {
		super(adapterFactory, target, name);
	}

	@Override
	protected boolean isInherited(ContentCategory category) {
		return false;
	}

	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		newChildDescriptors.add(createChildParameter(UmaPackage.eINSTANCE.getContentPackage_ContentElements(),
				UmaFactory.eINSTANCE.createCustomCategory()));
	}

	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			childrenFeatures = new ArrayList<EStructuralFeature>();
			childrenFeatures.add(UmaPackage.eINSTANCE.getContentPackage_ContentElements());
		}
		return childrenFeatures;
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

	@Override
	public Image getImage(Object object) {
		return StandardActivator.getDefault().getImage("full/obj16/Standards"); //$NON-NLS-1$
	}

	@Override
	public Collection<?> getChildren(Object object) {
		final Collection<?> children = super.getChildren(object);

		for (Object next : children) {
			if (next instanceof CustomCategory) {
				final CustomCategory customCategory = (CustomCategory) next;

				final StandardItemProvider itemProvider = new StandardItemProvider(adapterFactory);
				customCategory.eAdapters().add(itemProvider);

				// TODO issue #56 use adapter factory extension
				// final ILibraryItemProvider itemProvider = (ILibraryItemProvider) TngUtil
				// .getBestAdapterFactory(adapterFactory).adapt(customCategory,
				// ITreeItemContentProvider.class);

				itemProvider.setParent(object);
			}
		}
		return children;
	}

	@Override
	public void setDefaultName(Object obj) {
		if (obj instanceof CustomCategory) {
			@SuppressWarnings("unchecked")
			final List<CustomCategory> onlyStandards = TngUtil.extract(((ContentPackage) target).getContentElements(),
					CustomCategory.class);
			TngUtil.setDefaultName(onlyStandards, (MethodElement) obj, NEW_NAME);
		}
	}

	@Override
	public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection) {
		if (child instanceof CustomCategory) {
			return StandardUIResources.Standards_child;
		}
		throw new IllegalStateException("Unknown child type for Standards: " + child);
	}

	@Override
	public Object getCreateChildImage(Object owner, Object feature, Object child, Collection<?> selection) {
		if (child instanceof CustomCategory) {
			return StandardActivator.getDefault().getImage("full/obj16/Standard"); //$NON-NLS-1$
		}
		throw new IllegalStateException("Unknown child type for Standards: " + child);
	}

}
