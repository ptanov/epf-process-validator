# Features and benefits #
**Important note:** specified goals are not reached yet, but they are planned to be ready in near future
  * Repository for projects
    * Out of the box custom types for Technique, Practice, Standard, Project with iterations
    * Framework to contribute new custom types
  * Backward compatible with plain EPF Composer (without provided plugins)
> > Standard objects can be edited with plain EPF Composer, but custom types are still there when opened from EPF Composer with this project plugins
  * Validation framework for UMA objects and custom types:
    * Java plugins can be contributed with custom validations
> > > if user know how to do this
      * **required skills**: Java and plugin development
    * [OCL](http://en.wikipedia.org/wiki/Object_Constraint_Language) constraints can be easily added in preferences
> > > if user is not familiar with Java and plugin development, [OCL](http://en.wikipedia.org/wiki/Object_Constraint_Language) constraints are the most flexible option to define complex constraints
      * **required skills**: [OCL](http://en.wikipedia.org/wiki/Object_Constraint_Language) language
      * **custom defined types are supported in [OCL](http://en.wikipedia.org/wiki/Object_Constraint_Language) expressions, too**
    * Custom wizards for visual creation of basic constraints:
      * for example:
        * completeness, set some feature as mandatory
        * consistency, if some feature is provided in child - it should be provided in parent, too
      * **required skills**: no special skills are required (nor Java, nor OCL), only basic computer skills, e.g. working with EPF Composer
      * **more custom wizards can be contributed with plugins**
    * Validations constraints can be enabled or disabled easily from preference page
      * Enable/disable single constraint or whole constraints group