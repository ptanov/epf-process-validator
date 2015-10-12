_**TODO** add more content in this page_

# Introduction #
Set of plugins to be contributed to [Eclipse Process Framework (EPF)](http://www.eclipse.org/epf/) for validating processes.

This project is started as part of my master thesis.

# Project state #
_Project is in beta_
Demonstration at http://www.youtube.com/watch?v=9ihAxyZ16QE :
  * <a href='http://www.youtube.com/watch?feature=player_embedded&v=9ihAxyZ16QE' target='_blank'><img src='http://img.youtube.com/vi/9ihAxyZ16QE/0.jpg' width='425' height=344 /></a>

# Project goals #
  * Repository for projects
    * Out of the box custom types for Technique, Practice, Standard, Project with iterations
    * Framework to contribute new custom types
  * Backward compatible with plain EPF Composer (without provided plugins)
> > Standard objects can be edited with plain EPF Compser, but custom types are still there when opened from EPF Composer with this project plugins
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

# Road map/Versions #
~~For now road map can be understand by looking in [Issues](Issues.md) page: creating of pluggable framework for validation rules definition and execution.~~

  * Milestone-BeforeValidationRules

> Understand RCP, extension points, EPF Composer framework - how to extend
  * Milestone-ValidationRulesImpl
> Enumerate sample validation rules and implement them
  * Milestone-ValidationAPI
> Generalize validation rules needs and create something like API for accessing UMA model so creation of new validation rules should become easy task
  * Milestone-Deploying
> Add product, update site, manual plugins, etc.
  * Milestone-Release1.0
> Add functionality to use extensions from third party plugins, make easy deployable, maven project, etc.

# Resources #
Icons from http://findicons.com are used

# Thanks to #
Special thanks to Iva Krasteva. The idea for the projects is her and she actively supports it. The whole theory of process verification followed in this project is her contribution.

# More info #
More info can be found in [Wiki pages](http://code.google.com/p/epf-process-validator/w/list).

---

_P.S. sorry for my bad English (in all pages) :(_