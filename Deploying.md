# Introduction #

This documents covers deploying plugins in several approaches:
  * Product
  * Update site and existing EPF Composer
  * Manual copy of plugins into existing EPF Composer

EPF Composer version: 1.5.1

**TODO** not ready yet

# Product #
I'm not sure that creating custom product from EPF is legal.

# Update site #
EPF Composer does not support Update sites.

# Manual #
**Legend**
  * **Project Plugins** is reference to:
    * **Core**
      * `eu.tanov.epf.itemprovider`
      * `eu.tanov.epf.pv.ui.common`
      * `eu.tanov.epf.pv.service.types`
      * `eu.tanov.epf.pv.service.ocl`
      * `eu.tanov.epf.pv.ui.ocl`
      * `eu.tanov.epf.pv.ui.wizards`
    * **Types**
      * `eu.tanov.epf.pv.types.technique.ui`
      * `eu.tanov.epf.pv.types.technique.common`
      * `eu.tanov.epf.pv.types.standard.ui`
      * `eu.tanov.epf.pv.types.standard.common`
      * `eu.tanov.epf.pv.types.projectpractice.ui`
      * `eu.tanov.epf.pv.types.projectpractice.common`
      * `eu.tanov.epf.pv.types.projectiteration.ui`
      * `eu.tanov.epf.pv.types.projectiteration.common`
      * `eu.tanov.epf.pv.types.project.ui`
      * `eu.tanov.epf.pv.types.project.common`
    * **Constraints Wizards**
      * `eu.tanov.epf.pv.wizards.completeness`
      * `eu.tanov.epf.pv.wizards.consistency`
    * **Sample validators**
      * `eu.tanov.epf.pv.validators.example`

  1. Download EPF Composer from:
    * http://www.eclipse.org/epf/downloads/tool/epf1.5.0_downloads.php
      * http://www.eclipse.org/downloads/download.php?file=/technology/epf/composer/release/epf-composer-1.5.1.1-win32.zip
  1. Unzip to empty directory, e.g. C:\epf-composer-1.5.1.1-win32\epf-composer
  1. Download **Project Plugins** from [Downloads](http://code.google.com/p/epf-process-validator/downloads)
  1. Copy **Project Plugins** to EPF Composer plugins folder, e.g. C:\epf-composer-1.5.1.1-win32\epf-composer\plugins
  1. Download [mdt-ocl-runtime-1.3.0.zip](http://www.eclipse.org/downloads/download.php?file=/modeling/mdt/ocl/downloads/drops/1.3.0/R200906151742/mdt-ocl-runtime-1.3.0.zip) and copy to EPF Composer plugins folder (e.g. C:\epf-composer-1.5.1.1-win32\epf-composer\plugins) this file:
    * `org.eclipse.ocl_1.3.0.v200905271400.jar`
  1. Download [emf-validation-runtime-1.3.0.zip](http://www.eclipse.org/downloads/download.php?file=/modeling/emf/validation/downloads/drops/1.3.0/R200906221200/emf-validation-runtime-1.3.0.zip) and copy to EPF Composer plugins folder (e.g. C:\epf-composer-1.5.1.1-win32\epf-composer\plugins) these files:
    * `org.eclipse.emf.validation.ocl_1.3.0.v200901271722.jar`
    * `org.eclipse.emf.validation.ui.ide_1.3.0.v200901271722.jar`
    * `org.eclipse.emf.validation.ui_1.3.0.v200901271722.jar`
  1. That is it: start EPF Composer from C:\epf-composer-1.5.1.1-win32\epf-composer\epf.exe

## Creating plugins ##
_For developers:_
**Project Plugins** should be exported as `Deployable plug-ins and fragments` from Eclipse. In `options` select `Package plug-ins as individual JAR archives` and `Qualifier replacement`. Then they should be uploaded in Download section.