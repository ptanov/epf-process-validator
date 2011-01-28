package eu.tanov.epf.pv.wizards.consistency.pages;

import java.util.Iterator;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import eu.tanov.epf.pv.ui.wizards.widgets.StructuralFeaturesTree;
import eu.tanov.epf.pv.wizards.consistency.wizard.ConsistencyWizard;

public class SelectChildFeaturePage extends WizardPage {

	public SelectChildFeaturePage() {
		// TODO i18n
		super("Select feature");
		setTitle("Feature that must be available in parent");
		setDescription("Select feature that must be available in parent");

		setPageComplete(false);
	}

	@Override
	public void createControl(Composite parent) {
		setTitle("Feature that must be available in " + ((ConsistencyWizard) getWizard()).getEclass().getName());
		setDescription("Select feature that must be available in " + ((ConsistencyWizard) getWizard()).getEclass().getName());

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
					updateStatus(null, null);
					return;
				}
				if (event.getSelection() instanceof IStructuredSelection) {
					final IStructuredSelection selection = (IStructuredSelection) event.getSelection();
					for (Iterator iterator = selection.iterator(); iterator.hasNext();) {
						updateStatus(iterator.next(), (ITreeSelection) tree.getSelection());
					}
				}
			}
		});
		tree.getTree().setLayoutData(gridData2);

		tree.setInput(((ConsistencyWizard) getWizard()).getEclass());
		setControl(composite);
	}

	// TODO i18n
	private void updateStatus(Object selected, ITreeSelection iTreeSelection) {
		((ConsistencyWizard) getWizard()).setSelectedChildPath(null);
		setPageComplete(false);
		if (selected instanceof EReference) {
			((ConsistencyWizard) getWizard()).setSelectedChildPath(iTreeSelection.getPathsFor(selected));
			setErrorMessage(null);
			setPageComplete(true);
			setMessage("Press finish to save");
		} else if (selected == null) {
			setErrorMessage("Item should be selected");
			setMessage(null);
		} else {
			throw new IllegalArgumentException("ERerefence expected, but: " + selected);
		}
		getWizard().getContainer().updateButtons();
	}

}
