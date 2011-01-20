package eu.tanov.epf.pv.service.ocl.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.validation.service.ConstraintExistsException;
import org.eclipse.ocl.ParserException;

import eu.tanov.epf.pv.service.ocl.extension.OCLConstraintsDefinition;
import eu.tanov.epf.pv.service.ocl.provider.OCLConstraintProvider;

/**
 * XXX not synchronized 
 */
public class OCLConstraintsServiceImpl {
	private static final OCLConstraintsServiceImpl INSTANCE = new OCLConstraintsServiceImpl();

	private OCLConstraintProvider provider;

	private final Set<OCLConstraintsDefinition> definitions = new HashSet<OCLConstraintsDefinition>();

	/**
	 * singleton
	 */
	private OCLConstraintsServiceImpl() {
	}

	public static OCLConstraintsServiceImpl getInstance() {
		return INSTANCE;
	}

	/**
	 * @param definition
	 * @throws ParserException
	 *             on failure to parse, either because of a syntactic or semantic problem or because of an I/O failure
	 * @throws IllegalArgumentException
	 * 				if definition is already registered
	 * @throws ConstraintExistsException
	 *             in case any of the constraints has an ID that is already registered for a different constraint
	 */
	public void registerConstraintsDefinition(OCLConstraintsDefinition definition) throws ParserException, IllegalArgumentException,
			ConstraintExistsException {
		if (provider != null) {
			provider.registerConstraintsDefinition(definition);
		} else {
			if (definitions.contains(definition)) {
				throw new IllegalArgumentException("Definition already registered: "+definition);
			}
			definitions.add(definition);
		}
	}

	/**
	 * @param definition
	 * @throws IllegalArgumentException if definition is not registered
	 */
	public void removeConstraintsDefinition(OCLConstraintsDefinition definition) throws IllegalArgumentException {
		if (provider != null) {
			provider.removeConstraintsDefinition(definition);
		} else {
			if (!definitions.remove(definition)) {
				throw new IllegalArgumentException("Definition not registered: "+definition);
			}
		}
	}
	
	/**
	 * Should be used only by OCLConstraintProvider
	 * @param provider
	 * @return already accumulated definitions (before provider was created)
	 */
	public Collection<OCLConstraintsDefinition> setProvider(OCLConstraintProvider provider) {
		if (provider == null) {
			throw new NullPointerException("null provider passed");
		}
		if (this.provider != null) {
			throw new IllegalStateException(String.format("provider already set, old: %s, new: %s", this.provider, provider));
		}
		this.provider = provider;

		return definitions;
		//definitions list will not be used any more
	}
}
