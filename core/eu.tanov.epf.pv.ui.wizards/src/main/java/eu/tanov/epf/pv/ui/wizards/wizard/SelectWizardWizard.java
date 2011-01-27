package eu.tanov.epf.pv.ui.wizards.wizard;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardNode;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWizard;
import org.eclipse.ui.internal.activities.ws.WorkbenchTriggerPoints;
import org.eclipse.ui.internal.dialogs.WizardCollectionElement;
import org.eclipse.ui.internal.dialogs.WorkbenchWizardElement;
import org.eclipse.ui.internal.dialogs.WorkbenchWizardListSelectionPage;
import org.eclipse.ui.internal.dialogs.WorkbenchWizardNode;
import org.eclipse.ui.internal.registry.WizardsRegistryReader;
import org.eclipse.ui.model.AdaptableList;
import org.eclipse.ui.wizards.IWizardCategory;

import eu.tanov.epf.pv.ui.wizards.registry.ConstraintsWizardRegistry;

/**
 * Based on org.eclipse.ui.internal.dialogs.ExportWizard
 * FIXME remove access to internal!
 */
@SuppressWarnings("restriction")
public class SelectWizardWizard extends Wizard {
	private IWorkbench workbench;

	private IStructuredSelection selection;

	// the list selection page
	class SelectionPage extends WorkbenchWizardListSelectionPage {
		SelectionPage(IWorkbench w, IStructuredSelection ss, AdaptableList e, String s) {
			super(w, ss, e, s, WorkbenchTriggerPoints.EXPORT_WIZARDS);
		}

		public void createControl(Composite parent) {
			super.createControl(parent);
			// workbench.getHelpSystem().setHelp(getControl(), IWorkbenchHelpContextIds.EXPORT_WIZARD_SELECTION_WIZARD_PAGE);
		}

		protected IWizardNode createWizardNode(WorkbenchWizardElement element) {
			return new WorkbenchWizardNode(this, element) {
				public IWorkbenchWizard createWizard() throws CoreException {
					return wizardElement.createWizard();
				}
			};
		}
	}

	/**
	 * Creates the wizard's pages lazily.
	 */
	public void addPages() {
		// TODO i18n
		addPage(new SelectionPage(this.workbench, this.selection, getAvailableConstraintsWizards(), "Select wizard"));
	}

	/**
	 * Returns the export wizards that are available for invocation.
	 */
	protected AdaptableList getAvailableConstraintsWizards() {
		// TODO: exports are still flat - we need to get at the flat list. All
		// wizards will be in the "other" category.
		IWizardCategory root = ConstraintsWizardRegistry.getInstance().getRootCategory();
		WizardCollectionElement otherCategory = (WizardCollectionElement) root.findCategory(new Path(
				WizardsRegistryReader.UNCATEGORIZED_WIZARD_CATEGORY));
		if (otherCategory == null) {
			return new AdaptableList();
		}
		return otherCategory.getWizardAdaptableList();
	}

	public void init(IWorkbench workbench, IStructuredSelection currentSelection) {
		this.workbench = workbench;
		this.selection = currentSelection;

		// TODO i18n
		setWindowTitle("Constraints wizard");
		// setDefaultPageImageDescriptor(WorkbenchImages
		// .getImageDescriptor(IWorkbenchGraphicConstants.IMG_WIZBAN_EXPORT_WIZ));
		setNeedsProgressMonitor(true);
	}

	@Override
	public boolean performFinish() {
		return true;
	}
}
