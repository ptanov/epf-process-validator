package eu.tanov.epf.pv.ui.wizards.page;

import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import eu.tanov.epf.pv.ui.wizards.widgets.StructuralFeaturesTree;

public class SelectFeaturePage extends WizardPage {

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
		tree.getTree().setLayoutData(gridData2);

		tree.setInput(UmaPackage.eINSTANCE.getDiscipline());
		setControl(composite);
	}

}
