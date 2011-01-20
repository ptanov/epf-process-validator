package eu.tanov.epf.pv.service.ocl.service.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.validation.service.ConstraintExistsException;
import org.eclipse.ocl.ParserException;

import eu.tanov.epf.pv.service.ocl.extension.OCLConstraintsDefinition;
import eu.tanov.epf.pv.service.ocl.provider.OCLConstraintProvider;
import eu.tanov.epf.pv.service.ocl.service.OCLConstraintsService;

/**
 * XXX not synchronized 
 */
public class OCLConstraintsServiceImpl implements OCLConstraintsService {
	private OCLConstraintProvider provider;

	private final Set<OCLConstraintsDefinition> definitions = new HashSet<OCLConstraintsDefinition>();

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
