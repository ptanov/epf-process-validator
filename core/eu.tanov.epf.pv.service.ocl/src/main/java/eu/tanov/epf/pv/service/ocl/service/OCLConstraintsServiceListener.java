package eu.tanov.epf.pv.service.ocl.service;

import org.eclipse.emf.validation.service.ConstraintExistsException;
import org.eclipse.ocl.ParserException;

import eu.tanov.epf.pv.service.ocl.extension.OCLConstraintsDefinition;

public interface OCLConstraintsServiceListener {

	/**
	 * 
	 * @param definition
	 * @throws ParserException
	 *             on failure to parse, either because of a syntactic or semantic problem or because of an I/O failure
	 * @throws ConstraintExistsException
	 *             in case any of the constraints has an ID that is already registered for a different constraint
	 */
	public void constraintsDefinitionRegistered(OCLConstraintsDefinition definition);
	public void constraintsDefinitionRemoved(OCLConstraintsDefinition definition);
}
