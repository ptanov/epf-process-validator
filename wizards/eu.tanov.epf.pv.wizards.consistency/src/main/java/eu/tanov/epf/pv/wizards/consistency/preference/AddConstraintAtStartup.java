package eu.tanov.epf.pv.wizards.consistency.preference;

public class AddConstraintAtStartup implements org.eclipse.ui.IStartup {

	@Override
	public void earlyStartup() {
		OCLConstraintsPreference.registerOCLContent();
	}

}
