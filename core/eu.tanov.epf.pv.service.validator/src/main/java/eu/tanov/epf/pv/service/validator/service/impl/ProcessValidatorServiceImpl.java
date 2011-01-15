package eu.tanov.epf.pv.service.validator.service.impl;

import java.util.Collections;
import java.util.List;

import org.eclipse.epf.uma.ProcessComponent;

import eu.tanov.epf.pv.service.validator.extension.ProcessValidator;
import eu.tanov.epf.pv.service.validator.service.ProcessValidatorService;

public class ProcessValidatorServiceImpl implements ProcessValidatorService {

	@Override
	public void validate(ProcessComponent process) {
		// TODO Auto-generated method stub
		final List<ProcessValidator> enabledProcessValidators = getEnabledProcessValidators();
		for (ProcessValidator validator : enabledProcessValidators) {
			//TODO some dialog, issue # 61
//			validator.userInput
			//if fail  - return
		}

		for (ProcessValidator validator : enabledProcessValidators) {
			//TODO not in UI thread, issue # 60
//			validator.validate
			//if fail  - return
		}
		
	}

	private List<ProcessValidator> getEnabledProcessValidators() {
		//TODO getEnabledProcessValidators()
		return Collections.emptyList();
	}

}
