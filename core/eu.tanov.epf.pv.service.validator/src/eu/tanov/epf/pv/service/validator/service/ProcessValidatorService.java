package eu.tanov.epf.pv.service.validator.service;

public interface ProcessValidatorService {

	/**
	 * TODO may be library, too?
	 * TODO how to return that there is error and what is the erroneous
	 * TODO ProcessComponent or org.eclipse.epf.uma.Process?
	 */
	public void validate(org.eclipse.epf.uma.ProcessComponent process);
}
