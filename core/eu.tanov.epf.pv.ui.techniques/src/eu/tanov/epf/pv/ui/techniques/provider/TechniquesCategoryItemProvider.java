package eu.tanov.epf.pv.ui.techniques.provider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.category.TransientCategoryPackageItemProvider;
import org.eclipse.epf.library.edit.util.ModelStructure;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.ContentCategory;
import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.UmaPackage;

import eu.tanov.epf.pv.ui.techniques.i18n.TechniquesUIResources;

public class TechniquesCategoryItemProvider extends TransientCategoryPackageItemProvider {
	private static final String NEW_NAME_TECHNIQUE = "new_technique";
	public static final String TECHNIQUES_NAME = "Techniques"; //$NON-NLS-1$
	public static final String[] TECHNIQUES_PATH;
	static {
		TECHNIQUES_PATH = new String[ModelStructure.DEFAULT_DOMAIN_PATH.length];
		// -1, because last is used for Techniques
		System.arraycopy(ModelStructure.DEFAULT_DOMAIN_PATH, 0, TECHNIQUES_PATH, 0, TECHNIQUES_PATH.length - 1);
		TECHNIQUES_PATH[TECHNIQUES_PATH.length - 1] = TECHNIQUES_NAME;
	}

	/**
	 * Creates a new instance.
	 */
	public TechniquesCategoryItemProvider(AdapterFactory adapterFactory, ContentPackage target, String name) {
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
	public Object getImage(Object object) {
		// TODO
		return LibraryEditPlugin.INSTANCE.getImage("full/obj16/Disciplines"); //$NON-NLS-1$
	}

	@Override
	public Collection<?> getChildren(Object object) {
		// TODO
		final Collection<?> children = super.getChildren(object);

		return children;
	}

	@Override
	public void setDefaultName(Object obj) {
		if (obj instanceof CustomCategory) {
			@SuppressWarnings("unchecked")
			final List<CustomCategory> onlyTechniques = TngUtil.extract(((ContentPackage) target).getContentElements(),
					CustomCategory.class);
			TngUtil.setDefaultName(onlyTechniques, (MethodElement) obj, NEW_NAME_TECHNIQUE);
		}
	}

	@Override
	public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection) {
		if (child instanceof CustomCategory) {
			return TechniquesUIResources.Techniques_child;
		}
		throw new IllegalStateException("Unknown child type: " + child);
	}
}
