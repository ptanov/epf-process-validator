package eu.tanov.epf.pv.types.project.ui.provider;

import java.util.Collection;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.epf.library.edit.IDefaultNameSetter;
import org.eclipse.epf.library.edit.ILibraryItemProvider;
import org.eclipse.epf.library.edit.IStatefulItemProvider;
import org.eclipse.epf.library.edit.internal.IListenerProvider;
import org.eclipse.swt.graphics.Image;

import eu.tanov.epf.itemprovider.extension.CustomItemProvider;
import eu.tanov.epf.pv.types.project.common.util.ProjectHelper;
import eu.tanov.epf.pv.types.project.ui.ProjectActivator;

public class ProjectItemProvider extends org.eclipse.epf.library.edit.category.CustomCategoryItemProvider implements
		ILibraryItemProvider, IStatefulItemProvider, IDefaultNameSetter, IListenerProvider, CustomItemProvider {
	public ProjectItemProvider() {
		this(null);
	}

	public ProjectItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	@Override
	public Collection<?> getChildren(Object object) {
		// XXX Set the childrenStoreMap to null, to get the updated content, how to listen (add adapter) on custom category for changes?! 
		childrenStoreMap = null;
		return super.getChildren(object);
	}

	@Override
	public Image getImage(Object object) {
		return getProjectImage();
	}

	public static Image getProjectImage() {
		return ProjectActivator.getDefault().getImage("full/obj16/Project"); //$NON-NLS-1$
	}

	@Override
	public void collectNewChildDescriptors(@SuppressWarnings("rawtypes") Collection newChildDescriptors, Object object) {
		// no children
	}

	@Override
	public boolean matches(Object object) {
		return ProjectHelper.isProject(object);
	}

	@Override
	public void setAdapterFactory(AdapterFactory adapterFactory) {
		if (adapterFactory == null) {
			throw new NullPointerException("Adapter factory should not be null");
		}
		if (this.adapterFactory!=null) {
			throw new IllegalStateException(String.format("Adapter factory already set: %s, new: %s", this.adapterFactory, adapterFactory));
		}
		this.adapterFactory = adapterFactory;
	}
}
