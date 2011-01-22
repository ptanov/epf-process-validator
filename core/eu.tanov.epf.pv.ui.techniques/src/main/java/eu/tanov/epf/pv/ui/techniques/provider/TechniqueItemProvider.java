package eu.tanov.epf.pv.ui.techniques.provider;

import java.util.Collection;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.epf.library.edit.IDefaultNameSetter;
import org.eclipse.epf.library.edit.ILibraryItemProvider;
import org.eclipse.epf.library.edit.IStatefulItemProvider;
import org.eclipse.epf.library.edit.internal.IListenerProvider;
import org.eclipse.swt.graphics.Image;

import eu.tanov.epf.pv.ui.techniques.TechniquesActivator;

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
	public Image getImage(Object object) {
		return getTechniqueImage();
	}
	
	public static Image getTechniqueImage() {
		return TechniquesActivator.getDefault().getImage("full/obj16/Technique"); //$NON-NLS-1$
	}

	@Override
	protected void collectNewChildDescriptors(@SuppressWarnings("rawtypes") Collection newChildDescriptors, Object object) {
		// no children
	}

}
