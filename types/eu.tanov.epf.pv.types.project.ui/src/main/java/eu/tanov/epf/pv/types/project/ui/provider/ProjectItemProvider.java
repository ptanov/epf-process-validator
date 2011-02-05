package eu.tanov.epf.pv.types.project.ui.provider;

import java.util.Collection;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.epf.library.edit.IDefaultNameSetter;
import org.eclipse.epf.library.edit.ILibraryItemProvider;
import org.eclipse.epf.library.edit.IStatefulItemProvider;
import org.eclipse.epf.library.edit.internal.IListenerProvider;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.swt.graphics.Image;

import eu.tanov.epf.pv.types.project.ui.ProjectActivator;
import eu.tanov.epf.pv.types.projectiteration.ui.provider.ProjectIterationItemProvider;

public class ProjectItemProvider extends org.eclipse.epf.library.edit.category.CustomCategoryItemProvider implements
		ILibraryItemProvider, IStatefulItemProvider, IDefaultNameSetter, IListenerProvider {
	/**
	 * FIXME very very bad approach... fix this...
	 */
	public static AdapterFactory lastAdapterFactory;

	public ProjectItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
		lastAdapterFactory = adapterFactory;
	}

	@Override
	public Collection<?> getChildren(Object object) {
		// TODO why not using standard scheme?
		if (!(object instanceof CustomCategory)) {
			throw new IllegalArgumentException("Only custom categories expected, not: " + object);
		}
		final CustomCategory category = ((CustomCategory) object);
		final Collection<?> children = category.getCategorizedElements();

		for (Object next : children) {
			if (next instanceof CustomCategory) {
				final CustomCategory customCategory = (CustomCategory) next;

				final ProjectIterationItemProvider itemProvider = new ProjectIterationItemProvider(adapterFactory);
				customCategory.eAdapters().add(itemProvider);

				// TODO issue #56 use adapter factory extension
				// final ILibraryItemProvider itemProvider = (ILibraryItemProvider) TngUtil
				// .getBestAdapterFactory(adapterFactory).adapt(customCategory,
				// ITreeItemContentProvider.class);

				itemProvider.setParent(object);
			} else {
				throw new IllegalStateException(String.format("Not custom category in project: %s, but: %s", object, next));
			}
		}
		return children;
	}

	@Override
	public Image getImage(Object object) {
		return getProjectImage();
	}

	public static Image getProjectImage() {
		return ProjectActivator.getDefault().getImage("full/obj16/Project"); //$NON-NLS-1$
	}

	@Override
	protected void collectNewChildDescriptors(@SuppressWarnings("rawtypes") Collection newChildDescriptors, Object object) {
		// no children
	}

}
