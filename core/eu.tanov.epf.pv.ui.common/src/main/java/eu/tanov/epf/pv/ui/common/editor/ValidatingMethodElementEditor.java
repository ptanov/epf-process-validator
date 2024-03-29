package eu.tanov.epf.pv.ui.common.editor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.epf.authoring.ui.actions.LibraryValidateAction;
import org.eclipse.epf.authoring.ui.editors.MethodElementEditor;
import org.eclipse.epf.authoring.ui.views.LibraryView;
import org.eclipse.epf.persistence.MultiFileXMIResourceImpl;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;

/**
 * Copied from MethodElementEditor and corresponding classes
 * TODO fix this class
 */
public class ValidatingMethodElementEditor extends MethodElementEditor {
	@Override
	protected boolean isValidateResourcesBeforeSaveRequired() {
		return true;
	}

	private static class DiagnosticWrapper implements Diagnostic {
		private final Diagnostic original;
		private final String message;

		public DiagnosticWrapper(Diagnostic original, String message) {
			this.original = original;
			this.message = message;
		}

		@Override
		public int getSeverity() {
			return original.getSeverity();
		}

		@Override
		public String getMessage() {
			return message;
		}

		@Override
		public String getSource() {
			return original.getSource();
		}

		@Override
		public int getCode() {
			return original.getCode();
		}

		@Override
		public Throwable getException() {
			return original.getException();
		}

		@Override
		public List<?> getData() {
			return original.getData();
		}

		@Override
		public List<Diagnostic> getChildren() {
			return removeDuplicates(original.getChildren());
		}

		/**
		 * TODO quick fix in order to show error only once, something should be done in order to avoid validating of one object
		 * twice
		 * 
		 * @param children
		 * @return
		 */
		private List<Diagnostic> removeDuplicates(List<Diagnostic> children) {
			final List<Diagnostic> result = new ArrayList<Diagnostic>(children);
			for (Diagnostic diagnostic : children) {
				removeDuplicates(result, diagnostic);
			}
			return result;
		}

		private static void removeDuplicates(List<Diagnostic> result, Diagnostic diagnostic) {
			boolean started = false;
			for (Iterator<Diagnostic> iterator = result.iterator(); iterator.hasNext();) {
				final Diagnostic toRemove = iterator.next();
				if (toRemove == diagnostic) {
					// start checking only after source element is found (all after it)
					started = true;
					continue;
				}
				if (started) {
					if (diagnosticEquals(diagnostic, toRemove)) {
						iterator.remove();
					}
				}
			}
		}

		private static boolean diagnosticEquals(Diagnostic diagnostic, Diagnostic toRemove) {
			if (diagnostic.getSeverity() != toRemove.getSeverity()) {
				return false;
			}
			if (!equals(diagnostic.getMessage(), toRemove.getMessage())) {
				return false;
			}
			if (!equals(diagnostic.getSource(), toRemove.getSource())) {
				return false;
			}
			if (diagnostic.getCode() != toRemove.getCode()) {
				return false;
			}
			if (!equals(diagnostic.getException(), toRemove.getException())) {
				return false;
			}
			if (!equals(diagnostic.getData(), toRemove.getData())) {
				return false;
			}

			// XXX this will not work as expected - diagnosticEquals() should be used
			if (!equals(diagnostic.getChildren(), toRemove.getChildren())) {
				return false;
			}

			return true;
		}

		private static boolean equals(Object string1, Object string2) {
			if (string1 == null && string2 == null) {
				return true;
			}
			if (string1 != null) {
				return string1.equals(string2);
			} else {
				return string2.equals(string1);
			}
		}
	}

	@Override
	protected LibraryValidateAction createValidateResourceAction() {
		return new LibraryValidateAction(false) {
			/*
			 * (non-Javadoc)
			 * 
			 * @see org.eclipse.epf.authoring.ui.actions.LibraryValidateAction#refreshViews()
			 */
			protected void refreshViews() {
				LibraryView.getView().refreshViews();
			}

			@Override
			protected Diagnostic validate(IProgressMonitor progressMonitor) {
				final Diagnostic result = super.validate(progressMonitor);
				// in order to change message (avoid list with all objects)
				return new DiagnosticWrapper(result, EcorePlugin.INSTANCE.getString("_UI_DiagnosticRoot_diagnostic", //$NON-NLS-1$
						// TODO nls:
						new Object[] { "libarary" }));
			}

			public boolean updateSelection(IStructuredSelection selection) {
				selectedObjects = new ArrayList<EObject>();
				for (Iterator<?> objects = selection.iterator(); objects.hasNext();) {
					Object object = AdapterFactoryEditingDomain.unwrap(objects.next());
					if (object instanceof EObject) {
						selectedObjects.add((EObject) object);
					} else if (object instanceof Resource) {
						selectedObjects.addAll(((Resource) object).getContents());
					} else {
						return false;
					}
				}
				// include all sub objects
				// selectedObjects = EcoreUtil.filterDescendants(selectedObjects);
				return !selectedObjects.isEmpty();
			}

		};
	}

	@Override
	public boolean validateResources(@SuppressWarnings("rawtypes") Collection modifiedResources) {
		Set<EObject> elements = new HashSet<EObject>();
		for (@SuppressWarnings("rawtypes")
		Iterator iter = modifiedResources.iterator(); iter.hasNext();) {
			Resource resource = (Resource) iter.next();
			if (resource instanceof MultiFileXMIResourceImpl) {
				for (EObject eObject : resource.getContents()) {
					elements.add(eObject);
					for (Iterator<EObject> iterator = eObject.eAllContents(); iterator.hasNext();) {
						elements.add(iterator.next());
					}
				}
				// elements.addAll(resource.getContents());
			}
		}
		LibraryValidateAction validateAction = createValidateResourceAction();
		validateAction.updateSelection(new StructuredSelection(new ArrayList<EObject>(elements)));
		validateAction.run();
		// allow resource to be saved:
		return true;// validateAction.isSuccessful();
	}
}
