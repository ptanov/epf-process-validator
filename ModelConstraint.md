# Introduction #

[Eclipse Validation Framework](http://www.eclipse.org/modeling/emf/?project=validation) provides common way to validate models.
It is configured from Preferences > Model Validation > Constraints

Eclipse help page: http://help.eclipse.org/helios/index.jsp?nav=/24

Extension point is described here:
http://help.eclipse.org/helios/index.jsp?topic=/org.eclipse.emf.validation.doc/references/extension-points/org_eclipse_emf_validation_constraintProviders.html

Some basic example is provided here:
http://www.skywayperspectives.org/wiki/index.php/Custom_Step_Types#OCL_Validations

Tutorial: http://help.eclipse.org/helios/index.jsp?topic=/org.eclipse.emf.validation.doc/tutorials/validationTutorial.html

# How to define custom constraint #
  * Extension
```
	<extension point="org.eclipse.emf.validation.constraintProviders">
		<category id="eu.tanov.epf.pv.validators.category.default"
				name="%constraintCategory_Process_Default"/>
		<constraintProvider cache="true">
			<package namespaceUri="http://www.eclipse.org/epf/uma/1.0.6/uma.ecore"/>
			<constraints categories="eu.tanov.epf.pv.validators.category.default, org.eclipse.epf.validation.library.category">
				<constraint
						class="eu.tanov.epf.pv.validators.example.SampleConstraint"
						id="eu.tanov.epf.pv.validators.example.SampleConstraint"
						lang="Java"
						mode="Batch"
						name="%constraint_name_SampleConstraint"
						severity="WARNING"
						statusCode="1">
					<message>%constraint_message_SampleConstraint</message>
					<target class="ProcessComponent" />
				</constraint>
			</constraints>
		</constraintProvider>
	</extension>
```
  * Implementation:
```
package eu.tanov.epf.pv.validators.example;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;

public class SampleConstraint extends AbstractModelConstraint {

	@Override
	public IStatus validate(IValidationContext ctx) {
		final EObject target = ctx.getTarget();
		if (!(target instanceof org.eclipse.epf.uma.ProcessComponent)) {
			throw new IllegalStateException("SampleConstraint expects only ProcessComponent, not: " + target);
		}
		final String name = ((org.eclipse.epf.uma.ProcessComponent) target).getName();
		if ("goodName".equals(name)) {
			return ctx.createSuccessStatus();
		} else {
			return ctx.createFailureStatus(name);
		}
	}

}
```

  * Messages
```
constraintCategory_Process_Default=Process Validation
constraint_name_SampleConstraint=Sample Constraint
constraint_message_SampleConstraint=Name of process should match "goodName", but was "{0}"
```

# How to define custom constraint provider #
  * Extension
```
	<extension
         point="org.eclipse.emf.validation.constraintProviders"
         id="oclProvider">

		<!-- Custom constraint provider using OCL documents -->
		<constraintProvider cache="false"
            class="eu.tanov.epf.pv.service.ocl.provider.OCLConstraintProvider">
			<package namespaceUri="http://www.eclipse.org/epf/uma/1.0.6/uma.ecore"/>
		</constraintProvider>
	</extension>
```
  * Implementation
```
public class OCLConstraintProvider extends AbstractConstraintProvider implements OCLConstraintsServiceListener {
	@Override
	public void setInitializationData(IConfigurationElement config, String propertyName, Object data) throws CoreException {
		super.setInitializationData(config, propertyName, data);
// register constraints
	}

	private void addConstraint(OCLConstraintsDefinition definition, OCL ocl, Constraint constraint) {
		final Collection<IModelConstraint> constraints = getConstraints();

		final OCLConstraintDescriptor descriptor = new OCLConstraintDescriptor(definition, constraint, constraints.size() + 1);

		CategoryManager.getInstance().getCategory(definition.getCategory()).addConstraint(descriptor);
		defaultCateogry.addConstraint(descriptor);

		final OCLConstraint result = new OCLConstraint(descriptor, constraint, ocl);
		constraints.add(result);
		registerConstraints(Collections.singletonList(result))
	}
}
```

  * In order to change constraints at runtime - it is necessary to define that provider should not be cached. This is done by undocumented attribute `cache="false"`, for more information http://publib.boulder.ibm.com/infocenter/rsmhelp/v7r0m0/index.jsp?topic=/org.eclipse.emf.validation.doc/references/extension-points/org_eclipse_emf_validation_constraintProviders.html:
```
		<constraintProvider cache="false"
            class="eu.tanov.epf.pv.service.ocl.provider.OCLConstraintProvider">
```

# External resources #
  * http://help.eclipse.org/helios/index.jsp?nav=/24
  * http://www.eclipse.org/modeling/emf/?project=validation
  * http://help.eclipse.org/helios/index.jsp?topic=/org.eclipse.emf.validation.doc/references/extension-points/org_eclipse_emf_validation_constraintProviders.html
  * http://www.skywayperspectives.org/wiki/index.php/Custom_Step_Types#OCL_Validations
  * http://www.eclipse.org/articles/article.php?file=Article-EMF-Codegen-with-OCL/index.html
  * http://help.eclipse.org/ganymede/index.jsp?topic=/org.eclipse.emf.doc/references/overview/EMF.Validation.html
  * http://help.eclipse.org/helios/index.jsp?topic=/org.eclipse.emf.validation.doc/tutorials/validationTutorial.html