package eu.tanov.epf.pv.types.projectpractice.ui.provider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.epf.library.edit.category.TransientCategoryPackageItemProvider;
import org.eclipse.epf.library.edit.util.ModelStructure;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.ContentCategory;
import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.swt.graphics.Image;

import eu.tanov.epf.pv.types.projectpractice.common.util.ProjectPracticeHelper;
import eu.tanov.epf.pv.types.projectpractice.ui.ProjectPracticeActivator;
import eu.tanov.epf.pv.types.projectpractice.ui.i18n.ProjectPracticeUIResources;

public class ProjectPracticesCategoryItemProvider extends TransientCategoryPackageItemProvider {
	private static final String NEW_NAME = "new_project_practice";
	public static final String[] PROJECT_PRACTICES_PATH;
	static {
		PROJECT_PRACTICES_PATH = new String[ModelStructure.DEFAULT_DOMAIN_PATH.length];
		// -1, because last is used for Project Practice category
		System.arraycopy(ModelStructure.DEFAULT_DOMAIN_PATH, 0, PROJECT_PRACTICES_PATH, 0, PROJECT_PRACTICES_PATH.length - 1);
		PROJECT_PRACTICES_PATH[PROJECT_PRACTICES_PATH.length - 1] = ProjectPracticeHelper.CATEGORY_NAME;
	}

	/**
	 * Creates a new instance.
	 */
	public ProjectPracticesCategoryItemProvider(AdapterFactory adapterFactory, ContentPackage target, String name) {
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
		return ProjectPracticeActivator.getDefault().getImage("full/obj16/ProjectPractices"); //$NON-NLS-1$
	}

	@Override
	public Collection<?> getChildren(Object object) {
		final Collection<?> children = super.getChildren(object);

		for (Object next : children) {
			if (next instanceof CustomCategory) {
				final CustomCategory customCategory = (CustomCategory) next;

				final ProjectPracticeItemProvider itemProvider = new ProjectPracticeItemProvider(adapterFactory);
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
			final List<CustomCategory> onlyProjectPractices = TngUtil.extract(((ContentPackage) target).getContentElements(),
					CustomCategory.class);
			TngUtil.setDefaultName(onlyProjectPractices, (MethodElement) obj, NEW_NAME);
		}
	}

	@Override
	public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection) {
		if (child instanceof CustomCategory) {
			return ProjectPracticeUIResources.ProjectPractices_child;
		}
		throw new IllegalStateException("Unknown child type for ProjectPractices: " + child);
	}

	@Override
	public Object getCreateChildImage(Object owner, Object feature, Object child, Collection<?> selection) {
		if (child instanceof CustomCategory) {
			return ProjectPracticeActivator.getDefault().getImage("full/obj16/ProjectPractice"); //$NON-NLS-1$
		}
		throw new IllegalStateException("Unknown child type for ProjectPractices: " + child);
	}

}
