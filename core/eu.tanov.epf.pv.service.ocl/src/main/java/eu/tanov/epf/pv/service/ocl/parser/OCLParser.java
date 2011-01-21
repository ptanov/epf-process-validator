package eu.tanov.epf.pv.service.ocl.parser;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.ocl.OCLInput;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.ecore.Constraint;
import org.eclipse.ocl.ecore.OCL;
import org.eclipse.ocl.utilities.UMLReflection;

public class OCLParser {

	private final OCL ocl;

	public OCLParser() {
		this.ocl = createOCL();
	}

	/**
	 * Adds only invariants
	 * @param content
	 *            OCL text
	 * @return list of constraints from content
	 * @throws ParserException
	 *             on failure to parse, either because of a syntactic or
	 *             semantic problem or because of an I/O failure
	 */
	public List<Constraint> parseInvariants(String content) throws ParserException {
		final OCLInput oclInput = new OCLInput(content);

		final List<Constraint> constraints = ocl.parse(oclInput);

		final List<Constraint> result = new ArrayList<Constraint>(constraints.size());
		for (Constraint constraint : constraints) {
			// only add invariant constraints to result
			if (isInvariant(constraint)) {
				result.add(constraint);
			}
		}

		return result;
	}

	private static boolean isInvariant(Constraint constraint) {
		return UMLReflection.INVARIANT.equals(constraint.getStereotype());
	}

	protected OCL createOCL() {
		return OCL.newInstance(new ExtendedEcoreEnvironmentFactory());
	}

	public OCL getOCL() {
		return ocl;
	}
}
