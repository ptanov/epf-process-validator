package eu.tanov.epf.pv.service.ocl.service;

import java.util.Collection;

import org.eclipse.ocl.ParserException;

import eu.tanov.epf.pv.service.ocl.extension.OCLConstraintsDefinition;

public interface OCLConstraintsService {
	/**
	 * @param definition
	 * @throws IllegalArgumentException
	 *             if definition is already registered
	 */
	public void registerConstraintsDefinition(OCLConstraintsDefinition definition) throws IllegalArgumentException;

	/**
	 * @param definition
	 * @throws IllegalArgumentException
	 *             if definition is not registered
	 */
	public void removeConstraintsDefinition(OCLConstraintsDefinition definition) throws IllegalArgumentException;

	public void addListener(OCLConstraintsServiceListener listener);

	public void removeListener(OCLConstraintsServiceListener listener);

	public Collection<OCLConstraintsDefinition> getConstraintsDefinitions();

	/**
	 * Check if content contains valid OCL
	 * 
	 * @param content
	 *            OCL text
	 * @throws ParserException
	 *             on failure to parse, either because of a syntactic or
	 *             semantic problem or because of an I/O failure
	 */
	public void checkOCL(String content) throws ParserException;
}
