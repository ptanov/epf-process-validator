package eu.tanov.epf.pv.ui.wizards.page;

import java.util.Iterator;

import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import eu.tanov.epf.pv.ui.wizards.widgets.StructuralFeaturesTree;

public class SelectFeaturePage extends WizardPage {

	private Object selected = null;

	public SelectFeaturePage() {
		// TODO i18n
		super("Select feature");
		setTitle("Select feature");
		setDescription("Select feature for type");
	}

	@Override
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		Label sportsLabel = new Label(composite, SWT.NONE);
		sportsLabel.setText("Select feature:");
		final GridData gridData = new GridData();
		gridData.horizontalSpan = 2;
		sportsLabel.setLayoutData(gridData);

		final GridData gridData2 = new GridData();
		gridData2.horizontalSpan = 2;
		gridData2.horizontalAlignment = SWT.FILL;
		gridData2.grabExcessHorizontalSpace = true;
		gridData2.verticalAlignment = SWT.FILL;
		gridData2.grabExcessVerticalSpace = true;

		GridLayout gl = new GridLayout();
		int ncol = 2;
		gl.numColumns = ncol;
		final StructuralFeaturesTree tree = new StructuralFeaturesTree(composite);
		tree.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				if (event.getSelection().isEmpty()) {
					updateStatus(null);
					return;
				}
				if (event.getSelection() instanceof IStructuredSelection) {
					final IStructuredSelection selection = (IStructuredSelection) event.getSelection();
					for (Iterator iterator = selection.iterator(); iterator.hasNext();) {
						updateStatus(iterator.next());
					}
				}
			}
		});
		tree.getTree().setLayoutData(gridData2);

		tree.setInput(UmaPackage.eINSTANCE.getDiscipline());
		setControl(composite);
	}

	// TODO i18n
	private void updateStatus(Object selected) {
		this.selected = selected;
		if (selected == null) {
			setErrorMessage("Item should be selected");
			setMessage(null);
		} else {
			setErrorMessage(null);
			setMessage("Press finish to save");
		}
		getWizard().getContainer().updateButtons();
	}

}
