package eu.tanov.epf.pv.ui.ocl.preference;

public class AddConstraintAtStartup implements org.eclipse.ui.IStartup {

	@Override
	public void earlyStartup() {
		OCLConstraintsPreference.registerOCLContent();
	}

}
