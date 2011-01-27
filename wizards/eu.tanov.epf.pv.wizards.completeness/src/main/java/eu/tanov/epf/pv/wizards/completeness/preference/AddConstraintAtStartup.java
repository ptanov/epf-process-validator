package eu.tanov.epf.pv.wizards.completeness.preference;

public class AddConstraintAtStartup implements org.eclipse.ui.IStartup {

	@Override
	public void earlyStartup() {
		OCLConstraintsPreference.registerOCLContent();
	}

}
