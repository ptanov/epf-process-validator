package eu.tanov.epf.pv.service.ocl.service;

import java.util.Collection;

import org.eclipse.emf.validation.service.ConstraintExistsException;
import org.eclipse.ocl.ParserException;

import eu.tanov.epf.pv.service.ocl.extension.OCLConstraintsDefinition;
import eu.tanov.epf.pv.service.ocl.provider.OCLConstraintProvider;

public interface OCLConstraintsService {
	/**
	 * @param definition
	 * @throws ParserException
	 *             on failure to parse, either because of a syntactic or semantic problem or because of an I/O failure
	 * @throws IllegalArgumentException
	 *             if definition is already registered
	 * @throws ConstraintExistsException
	 *             in case any of the constraints has an ID that is already registered for a different constraint
	 */
	public void registerConstraintsDefinition(OCLConstraintsDefinition definition) throws ParserException,
			IllegalArgumentException, ConstraintExistsException;

	/**
	 * @param definition
	 * @throws IllegalArgumentException
	 *             if definition is not registered
	 */
	public void removeConstraintsDefinition(OCLConstraintsDefinition definition) throws IllegalArgumentException;

	/**
	 * TODO remove this method - replace with addListener() and removeListener(), here is for backward compatibility
	 * 
	 * @param oclConstraintProvider
	 * @return
	 */
	public Collection<OCLConstraintsDefinition> setProvider(OCLConstraintProvider oclConstraintProvider);

}
