package eu.tanov.epf.pv.ui.validator.actions;

import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.epf.common.utils.StrUtil;
import org.eclipse.epf.library.ui.BusyIndicatorHelper;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.WorkbenchJob;

import eu.tanov.epf.pv.ui.validator.i18n.ValidatorUIResources;

public class ValidateAction implements IObjectActionDelegate {

	@Override
	public void run(IAction action) {
		final WorkspaceJob validateProcessJob = new WorkspaceJob(ValidatorUIResources.validatorJob_name) {

			@Override
			public IStatus runInWorkspace(IProgressMonitor monitor)
					throws CoreException {

				monitor.beginTask("", 3);
				monitor.worked(1);
				try {
					Thread.sleep(3000);
					monitor.worked(2);
					Thread.sleep(3000);
					monitor.worked(3);
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return Status.OK_STATUS;
			}
			
		};
		BusyIndicatorHelper.setUserInterfaceActive(false);
		PlatformUI.getWorkbench().getProgressService().showInDialog(null, validateProcessJob);
		validateProcessJob.addJobChangeListener(new JobChangeAdapter() {
			@Override
			public void done(IJobChangeEvent event) {
				// UI activation job
				final WorkbenchJob reactivateUIJob = new WorkbenchJob(StrUtil.EMPTY_STRING) {
					@Override
					public IStatus runInUIThread(IProgressMonitor monitor) {
						BusyIndicatorHelper.setUserInterfaceActive(true);

						return Status.OK_STATUS;
					}
				};
				reactivateUIJob.setSystem(true);
				reactivateUIJob.schedule();
			}
		});
		validateProcessJob.schedule();
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
	}

	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
	}

}
