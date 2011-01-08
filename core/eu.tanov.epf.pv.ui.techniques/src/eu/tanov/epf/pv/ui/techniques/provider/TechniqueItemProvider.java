package eu.tanov.epf.pv.ui.techniques.provider;

import java.util.Collection;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.epf.library.edit.IDefaultNameSetter;
import org.eclipse.epf.library.edit.ILibraryItemProvider;
import org.eclipse.epf.library.edit.IStatefulItemProvider;
import org.eclipse.epf.library.edit.internal.IListenerProvider;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.jface.resource.ImageDescriptor;

import eu.tanov.epf.pv.ui.techniques.TechniquesActivator;
import eu.tanov.epf.pv.ui.techniques.i18n.TechniquesUIResources;

public class TechniqueItemProvider extends org.eclipse.epf.library.edit.category.CustomCategoryItemProvider implements
		ILibraryItemProvider, IStatefulItemProvider, IDefaultNameSetter, IListenerProvider {

	public TechniqueItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	@Override
	public Collection<?> getChildren(Object object) {
		return super.getChildren(object);
	}

	@Override
	public ImageDescriptor getImage(Object object) {
		return TechniquesActivator.getDefault().getImage("full/obj16/Technique"); //$NON-NLS-1$
	}

	@Override
	public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection) {
		if (child instanceof CustomCategory) {
			return TechniquesUIResources.Techniques_child;
		}
		throw new IllegalStateException("Unknown child type for technique: " + child);
	}

	@Override
	public Object getCreateChildImage(Object owner, Object feature, Object child,
			@SuppressWarnings("rawtypes") Collection selection) {
		if (child instanceof CustomCategory) {
			return TechniquesActivator.getDefault().getImage("full/obj16/Technique"); //$NON-NLS-1$
		}
		throw new IllegalStateException("Unknown child type for technique: " + child);
	}

}
