package eu.tanov.epf.itemprovider.extension;

import java.util.Collection;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;

/**
 * XXX or use ItemProviderAdapter?
 */
public interface CustomItemProvider extends ITreeItemContentProvider, IItemLabelProvider {

	/**
	 * From org.eclipse.emf.edit.provider.ItemProviderAdapter
	 * 
	 * 
	 * This adds to <code>newChildDescriptors</code>, a collection of new child
	 * descriptors. Typically,
	 * {@link org.eclipse.emf.edit.command.CommandParameter}s will be used as
	 * descriptors. This implementation adds nothing to the collection, but
	 * derived classes should override this method, invoking the superclass
	 * implementation and then adding to the collection.
	 */
	public void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object);

	// TODO add all necessary methods and interfaces like
	// collectNewChildDescriptors(), ITreeItemContentProvider, etc.

	/**
	 * @param object
	 * @return true if object is item provider for this custom type and can be wrapped
	 */
	public boolean matches(Object object);
	/**
	 * Should be called immediate after create!
	 * @param adapterFactory
	 */
	public void setAdapterFactory(AdapterFactory adapterFactory);
}
