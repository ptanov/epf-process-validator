# Introduction #

According to ProjectGoals there should be easy way to contribute different types:
  * Custom types
  * Custom validations
    * Java
    * OCL
  * Wizards for creating simple validations

Java and plugin development skills are required!

More examples can be viewed in source code of EPF Process Validator itself.

**TODO**

# Custom Types #
User should be familiar with ExtendMethodContent.
There is maven archetype project `archetypes/eu.tanov.epf.pv.achetypes.customtype` that will create all necessary to contribute custom type.
Command to execute:
```
mvn archetype:generate \
 -DarchetypeGroupId=eu.tanov.epf.pv.archetypes \
 -DarchetypeArtifactId=eu.tanov.epf.pv.archetypes.customtype \
 -DarchetypeVersion=1.0.0-SNAPSHOT \
 -DgroupId=eu.tanov.epf.pv.types \
 -DartifactId=eu.tanov.epf.pv.types.technique \
 -DtypeName=Technique \
 -DtypeNamePackage=technique \
 -DtypeNamePlural=Techniques \
 -DtypeNameStringPlural=Techniques \
 -DtypeNameString=Technique \
 -DtypeNameVariable=technique \
 -DtypeNamePluralConst=TECHNIQUES \
 -DtypeNameNewInstanceName=technique
```
in order to contribute custom type Technique with Tasks and Work Products.

If custom type contains more than one word in its name convention is as defined in archetype:
```
<requiredProperties>
	<requiredProperty key="typeName">
		<defaultValue>CustomType</defaultValue>
	</requiredProperty>
	<requiredProperty key="typeNamePlural">
		<defaultValue>CustomTypes</defaultValue>
	</requiredProperty>
	<requiredProperty key="typeNameStringPlural">
		<defaultValue>Custom Types</defaultValue>
	</requiredProperty>
	<requiredProperty key="typeNameString">
		<defaultValue>Custom Type</defaultValue>
	</requiredProperty>
	<requiredProperty key="typeNamePackage">
		<defaultValue>customtype</defaultValue>
	</requiredProperty>
	<requiredProperty key="typeNameVariable">
		<defaultValue>customType</defaultValue>
	</requiredProperty>
	<requiredProperty key="typeNamePluralConst">
		<defaultValue>CUSTOM_TYPES</defaultValue>
	</requiredProperty>
	<requiredProperty key="typeNameNewInstanceName">
		<defaultValue>custom_type</defaultValue>
	</requiredProperty>
</requiredProperties>
```

e.g. for ProjectPractice:
```
mvn archetype:generate \
 -DarchetypeGroupId=eu.tanov.epf.pv.archetypes \
 -DarchetypeArtifactId=eu.tanov.epf.pv.archetypes.customtype \
 -DarchetypeVersion=1.0.0-SNAPSHOT \
 -DgroupId=eu.tanov.epf.pv.types \
 -DartifactId=eu.tanov.epf.pv.types.projectpractice \
 -DtypeName=ProjectPractice \
 -DtypeNamePackage=projectpractice \
 -DtypeNamePlural=ProjectPractices \
 -DtypeNameStringPlural="Project Practices" \
 -DtypeNameString="Project Practice" \
 -DtypeNameVariable=projectPractice \
 -DtypeNamePluralConst=PROJECT_PRACTICES \
 -DtypeNameNewInstanceName=project_practice
```

# Custom validations #
## Java ##
Explained in ModelConstraint

## OCL ##
There are two ways to contribute OCL constraint: on compile time and on runtime
### At compile time (using extension point) ###

### At runtime (using OCLConstraintsService) ###

# Wizards for creating simple validations #