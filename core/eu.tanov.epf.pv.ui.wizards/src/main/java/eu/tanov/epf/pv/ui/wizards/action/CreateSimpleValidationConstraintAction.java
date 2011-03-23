package eu.tanov.epf.pv.ui.wizards.action;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.WrapperItemProvider;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import eu.tanov.epf.pv.service.types.service.CustomTypeHandlersService;
import eu.tanov.epf.pv.ui.wizards.WizardsActivator;
import eu.tanov.epf.pv.ui.wizards.wizard.SelectWizardWizard;

public class CreateSimpleValidationConstraintAction implements IObjectActionDelegate {

	private IStructuredSelection selection = null;

	@Override
	public void run(IAction action) {
		if (selection == null) {
			throw new IllegalStateException("CreateSimpleValidationConstraintAction: selection is null");
		}
		final SelectWizardWizard wizard = new SelectWizardWizard();
		wizard.init(PlatformUI.getWorkbench(), wrapSelection(selection));
		WizardDialog dialog = new WizardDialog(PlatformUI.getWorkbench().getDisplay().getActiveShell(), wizard);
		dialog.create();
		dialog.open();
	}

	private static IStructuredSelection wrapSelection(IStructuredSelection selection) {
		final CustomTypeHandlersService service = WizardsActivator.getDefault().getService(CustomTypeHandlersService.class);
		final Object firstElement = getFirstElement(selection);
		if (!(firstElement instanceof EObject)) {
			throw new IllegalStateException("Selection should be from EObjects, not " + firstElement);
		}
		final EObject wrapped = service.wrapObjectIfNeeded((EObject) firstElement);

		return new StructuredSelection(wrapped);
	}

	private static Object getFirstElement(IStructuredSelection selection) {
		Object result = selection.getFirstElement();
		if (result instanceof WrapperItemProvider) {
			result = ((WrapperItemProvider) result).getValue();
		}
		return result;
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
