package eu.tanov.epf.pv.ui.wizards.wizard;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IExportWizard;
import org.eclipse.ui.IWorkbench;

import eu.tanov.epf.pv.ui.wizards.page.SelectFeaturePage;

public class SampleWizard extends Wizard implements IExportWizard {

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		addPage(new SelectFeaturePage());
	}

	@Override
	public boolean performFinish() {
		// TODO Auto-generated method stub
		return true;
	}

}
