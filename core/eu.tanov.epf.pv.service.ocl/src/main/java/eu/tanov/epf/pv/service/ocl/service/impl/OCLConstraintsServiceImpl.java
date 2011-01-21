package eu.tanov.epf.pv.service.ocl.service.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import org.eclipse.ocl.ParserException;

import eu.tanov.epf.pv.service.ocl.OCLActivator;
import eu.tanov.epf.pv.service.ocl.extension.OCLConstraintsDefinition;
import eu.tanov.epf.pv.service.ocl.parser.OCLParser;
import eu.tanov.epf.pv.service.ocl.service.OCLConstraintsService;
import eu.tanov.epf.pv.service.ocl.service.OCLConstraintsServiceListener;
import eu.tanov.epf.pv.service.types.service.CustomTypeHandlersService;

/**
 * TODO not synchronized
 */
public class OCLConstraintsServiceImpl implements OCLConstraintsService {
	private final Set<OCLConstraintsDefinition> definitions = new HashSet<OCLConstraintsDefinition>();

	private final LinkedList<OCLConstraintsServiceListener> listeners = new LinkedList<OCLConstraintsServiceListener>();

	private final OCLParser parser;

	public OCLConstraintsServiceImpl() {
		// load types service
		OCLActivator.getDefault().getService(CustomTypeHandlersService.class);

		this.parser = createOCLParser();
	}

	protected OCLParser createOCLParser() {
		return new OCLParser();
	}

	@Override
	public void registerConstraintsDefinition(OCLConstraintsDefinition definition) throws IllegalArgumentException {
		if (definitions.contains(definition)) {
			throw new IllegalArgumentException("Definition already registered: " + definition);
		}
		definitions.add(definition);

		notifyListenersRegistered(definition);
	}

	@Override
	public void removeConstraintsDefinition(OCLConstraintsDefinition definition) throws IllegalArgumentException {
		if (!definitions.remove(definition)) {
			throw new IllegalArgumentException("Definition not registered: " + definition);
		}

		notifyListenersRemoved(definition);
	}

	@Override
	public void addListener(OCLConstraintsServiceListener listener) {
		listeners.add(listener);
	}

	@Override
	public void removeListener(OCLConstraintsServiceListener listener) {
		listeners.remove(listener);
	}

	private void notifyListenersRegistered(OCLConstraintsDefinition definition) {
		for (OCLConstraintsServiceListener listener : listeners) {
			listener.constraintsDefinitionRegistered(definition);
		}
	}

	private void notifyListenersRemoved(OCLConstraintsDefinition definition) {
		for (OCLConstraintsServiceListener listener : listeners) {
			listener.constraintsDefinitionRemoved(definition);
		}
	}

	@Override
	public Collection<OCLConstraintsDefinition> getConstraintsDefinitions() {
		return Collections.unmodifiableCollection(definitions);
	}

	@Override
	public void checkInvariantOCL(String content) throws IllegalArgumentException, ParserException {
		parser.parseInvariants(content);
	}

}
