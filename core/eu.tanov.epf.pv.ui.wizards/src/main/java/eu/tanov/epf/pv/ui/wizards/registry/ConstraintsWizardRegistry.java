package eu.tanov.epf.pv.ui.wizards.registry;

import org.eclipse.ui.internal.wizards.AbstractExtensionWizardRegistry;

import eu.tanov.epf.pv.ui.wizards.WizardsActivator;

/**
 * TODO move to service package, rename to service
 * Based on ExportWizardRegistry
 * TODO do not use internal packages!
 */
@SuppressWarnings("restriction")
public class ConstraintsWizardRegistry extends AbstractExtensionWizardRegistry {

	private static final String NAME_EXTENSION_POINT = "constraintsWizards";
	private static ConstraintsWizardRegistry singleton;

	/**
	 * Return the singleton instance of this class.
	 * 
	 * @return the singleton instance of this class
	 */
	public static synchronized ConstraintsWizardRegistry getInstance() {
		if (singleton == null) {
			singleton = new ConstraintsWizardRegistry();
		}
		return singleton;
	}

	/**
	 * singleton
	 */
	private ConstraintsWizardRegistry() {
		super();
	}

	protected String getExtensionPoint() {
		return NAME_EXTENSION_POINT;
	}

	protected String getPlugin() {
		return WizardsActivator.PLUGIN_ID;
	}
}
