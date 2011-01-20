package eu.tanov.epf.pv.service.ocl.service;

import java.util.Collection;

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
}
