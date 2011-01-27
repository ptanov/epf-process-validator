package eu.tanov.epf.pv.ui.wizards.page;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

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
		GridLayout gl = new GridLayout();
		int ncol = 4;
		gl.numColumns = ncol;
		composite.setLayout(gl);
		new Label(composite, SWT.NONE).setText("label");
		setControl(composite);
	}

}
