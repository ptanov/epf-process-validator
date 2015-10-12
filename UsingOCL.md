# Introduction #

~~OCL interpreter will not be extended.~~ Instead of this new runtime EMF types will be created and registered. Actual validating object (if it is CustomCategory, e.g. Technique) will be wrapped in actual dynamic EMF type.
New types can not solve all the problems: there is [issue 93](https://code.google.com/p/epf-process-validator/issues/detail?id=93).
If object is wrapped too early - validation status is bind to wrong object (wrapper, rather than original object). So it should be wrapped at OCL rule evaluation time. So `EcoreEvaluationEnvironment` should be extended in method:
```
public Object navigateProperty(EStructuralFeature property, List<?> qualifiers, Object target);
```
- wrap `target` if needed.
In order to register `ExtendedEcoreEvaluationEnvironment` - `EcoreEnvironmentFactory` should be extended, too: `ExtendedEcoreEnvironmentFactory`. OCL is instantiated as follows:
```
	private OCL createOCL() {
		return OCL.newInstance(new ExtendedEcoreEnvironmentFactory());
	}
```

In order to match some object to OCL constraint before wrapping actual object - `OCLConstraintDescriptor` should be changed:
```

	public boolean targetsTypeOf(EObject eObject) {
		//because target object is not wrapped when this method is called - issue 93
		//TODO improve, use helper
		List<CustomTypeHandler> customTypeHandlers = RegisterValidatorAtStartup.customTypeHandlers;
		for (CustomTypeHandler customTypeHandler : customTypeHandlers) {
			if (customTypeHandler.getCustomType() == constraint.getSpecification().getContextVariable().getType()) {
				if (customTypeHandler.matches(eObject)) {
					return true;
				}
			}
		}
		return constraint.getSpecification().getContextVariable().getType().isInstance(eObject);
	}
```
# Implementation #
Class `/eu.tanov.epf.pv.ocl/src/main/java/eu/tanov/epf/pv/ocl/provider/OCLConstraintProvider.java` reads OCL expressions and creates constraints in term of Eclipse Validation
**TODO**

# Using extension #
**TODO**
Contribute extension:
```
 <extension point="eu.tanov.epf.pv.service.ocl.OCLConstraints">
    <constraints
          category="eu.tanov.epf.pv.validators.example.constraints31111"
          id="eu.tanov.epf.pv.validators.example.constraints31111"
          mandatory="false"
          message="Constraint1111 %s violated on {0}"
          severity="ERROR">
       <content>
          package uma

context ExtendedUma::Technique 
inv SOMEqqer: not self.tasks->isEmpty()

endpackage
       </content>
    </constraints>
 </extension>
```

# Manage OCL rules runtime #
**TODO**
```
//add:
OCLConstraintRegistry.getInstance().registerConstraintsDefinition(definition);
//remove:
OCLConstraintRegistry.getInstance().removeConstraintsDefinition(definition);
```

# Reference #
  * http://www.eoinwoods.info/doc/ocl_quick_reference.pdf
  * http://www.uni-koblenz.de/~beckert/Lehre/Verification/10OCL.pdf
  * http://www.eoinwoods.info/doc/ocl_quick_reference.pdf
  * http://www.cs.utep.edu/cheon/cs3360/project/proj1/ocl-ref-short.pdf
  * http://help.eclipse.org/helios/index.jsp?topic=/org.eclipse.emf.validation.doc/tutorials/oclValidationTutorial.html