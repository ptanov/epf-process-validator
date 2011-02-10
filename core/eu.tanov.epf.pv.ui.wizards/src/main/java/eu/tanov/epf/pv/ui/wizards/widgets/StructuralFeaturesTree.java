package eu.tanov.epf.pv.ui.wizards.widgets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

public class StructuralFeaturesTree extends TreeViewer {

	private final IContentProvider contentProvider = new ITreeContentProvider() {
		@Override
		public void dispose() {
		}

		@Override
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}

		@Override
		public Object[] getElements(Object inputElement) {
			return getChildren(inputElement);
		}

		@Override
		public Object[] getChildren(Object parentElement) {
			if (parentElement instanceof EReference) {
				parentElement = ((EReference) parentElement).getEReferenceType();
			}
			if (parentElement instanceof EClass) {
				final EClass eObject = (EClass) parentElement;
				final List<EReference> result = new ArrayList<EReference>(eObject.getEStructuralFeatures().size());
				for (EStructuralFeature object : eObject.getEAllStructuralFeatures()) {
					if (object instanceof EReference && (((EReference) object).isMany())) {
						// add only list EReferences
						result.add((EReference) object);
					}
				}
				// local structural features - first: 
				Collections.reverse(result);
				return result.toArray();
			}
			return new Object[0];
		}

		@Override
		public Object getParent(Object element) {
			if (element instanceof EObject) {
				final EObject eObject = (EObject) element;
				return eObject.eContainer();

			}
			throw new IllegalArgumentException("getParent(): Only EObjects are supported, not: " + element);
		}

		@Override
		public boolean hasChildren(Object element) {
			return getChildren(element).length > 0;
		}

	};
	private final ILabelProvider labelProvider = new ILabelProvider() {
		public String getText(Object object) {
			if (!(object instanceof EReference)) {
				throw new IllegalArgumentException("Only EReference is supported, but was: " + object);
			}
			final EReference reference = (EReference) object;
			return String.format("%s:%s", reference.getName(), reference.getEReferenceType().getName());
		}

		@Override
		public void addListener(ILabelProviderListener listener) {
		}

		@Override
		public void dispose() {
		}

		@Override
		public boolean isLabelProperty(Object element, String property) {
			return false;
		}

		@Override
		public void removeListener(ILabelProviderListener listener) {
		}

		@Override
		public Image getImage(Object element) {
			return null;
		}
	};

	public StructuralFeaturesTree(Composite parent) {
		super(parent, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);

		setLabelProvider(labelProvider);
		setContentProvider(contentProvider);
		setUseHashlookup(true);

		// Set the input to treeViewer
		// treeViewer.setInput(getInput());

		// treeViewer.addSelectionChangedListener(this);
		// treeViewer.addDoubleClickListener(this);
		getTree().setFont(parent.getFont());

	}

}
