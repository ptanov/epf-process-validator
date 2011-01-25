package eu.tanov.epf.pv.ui.wizards.action;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import eu.tanov.epf.pv.ui.wizards.wizard.SelectWizardWizard;

public class CreateSimpleValidationConstraintAction implements IObjectActionDelegate {

	private IStructuredSelection selection = null;

	@Override
	public void run(IAction action) {
		if (selection == null) {
			throw new IllegalStateException("CreateSimpleValidationConstraintAction: selection is null");
		}
		final SelectWizardWizard wizard = new SelectWizardWizard();
		wizard.init(PlatformUI.getWorkbench(), selection);
		WizardDialog dialog = new WizardDialog(PlatformUI.getWorkbench().getDisplay().getActiveShell(), wizard);
		dialog.create();
		dialog.open();
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			this.selection = (IStructuredSelection) selection;
		}
	}

	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
	}

}
