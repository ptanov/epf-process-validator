# Introduction #
**TODO** _work on this document will be performed together with other tasks so it will be updated regularly_

Good start point for this is UMA.ecore and http://www.omg.org/spec/SPEM/2.0/PDF , chapter 18.
SPEM 2.0 Base Plug-in is "instance" or "implementation" of SPEM metamodel, especially for software projects.

UMA.ecore can be converted to ecore diag for easy understanding (required Eclipse plugins: EMF Eclipse Modeling Framework SDK, EMF Compare SDK, Graphical Modeling Framework SDK, I'm not sure if all are required).

The key problem is real world meaning of specific classes in UMA.ecore. I hope that this is explained in link above.

# Details #
**TODO**

# SPEM #

# UMA #
In standard EPF, `library` contains collection of:
  * `Method plugins`
    * `methodPackages` collection
      * `ContentPackage`, names: Content, DeliveryProcesses, ProcessContributions
        * `childPackages` collection
          * `ContentPackage`, names: Categories, CoreContent
            * `childPackages` collection
              * **_(1)_** `ContentPackages` for `Domains`, `Disciplines`, `RoleSets`, `StandardCategories`, `CustomCategories`, etc.
                * `contentElements` collection
                  * contains content like `Domains`, `Disciplines`, `Work products`, etc.

In order to extend and add new `Category` (e.g. `Technique`) - new sibling in **_(1)_** should be created.

To refer some element in UMA something called `structure path` is used (type is `String[]`). It contains names of elements in UMA hierarchy, e.g.:
  * `Content / Categories / Domains`
> for domains
  * `Content / Categories / Techniques`
> for technique

When model is manipulated - structure path is passed to respective methods in order to distinguish different child packages.

# Relations between SPEM and UMA #