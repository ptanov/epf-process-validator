Used IDE is "Eclipse Modeling Tools (includes Incubating components)".
After that GMF plugin should be installed in Eclipse.

# Download #
  * Java SDK
> > http://www.oracle.com/technetwork/java/javase/downloads/index.html
  * Eclipse Modeling Tools (includes Incubating components)
> > http://www.eclipse.org/downloads/
  * Maven
> > http://maven.apache.org/download.html
  * Mercurial (hg)
> > http://mercurial.selenic.com/
  * ~~EPF Sources~~
> > ~~:pserver:anonymous@dev.eclipse.org:/cvsroot/technology/org.eclipse.epf/composer/1.5/plugins~~

# Unzip/install them #

# Environment variables #
  * Add maven/bin directory and mercurial(hg)/bin directory to `PATH`
  * Add Java install directory (e.g. `c:\PROGRA~1\Java\jdk1.6.0_23`) to `JAVA_HOME`

# Install #
  * GMF plugin for Eclipse (Help>Install Modeling Components> search for GMF or "Graphical Modeling Tooling")
  * optionally Mercurial Eclipse plugin, Eclipse update site: http://cbes.javaforge.com/update

# Steps #
  * Download project sources:
```
hg clone https://epf-process-validator.googlecode.com/hg/ epf-process-validator
```
  * Download EPF project sources:

> in directory that `epf-process-validator` is checked out type (this will download EPF composer sources):
```
cd epf-composer-plugins
mvn scm:checkout
```
  * import all projects from directory `epf-process-validator` (this includes EPF composer sources and plugins of this project)

  * ~~In order to create plugins and contribute them to EPF sources (or binaries) of EPF should be downloaded from: :pserver:anonymous@dev.eclipse.org:/cvsroot/technology/org.eclipse.epf/composer/1.5/plugins~~

  * Run project `org.eclipse.epf.rcp.ui` as Eclipse Application, select **ALL** plugins from workspace and set arguments: `-Xms512m -Xmx512m`.

# ~~Related documents~~ #
  * http://www.eclipse.org/epf/general/developers_documentation.php
  * http://www.eclipse.org/epf/composer_dev_guide/
    * http://www.eclipse.org/epf/composer_dev_guide/dev_env.php
  * http://www.eclipse.org/downloads/
    * "Eclipse Modeling Tools (includes Incubating components) (371 MB)"
      * http://www.eclipse.org/downloads/download.php?file=/technology/epp/downloads/release/galileo/SR1/eclipse-modeling-galileo-SR1-incubation-win32.zip

  * http://www.eclipse.org/epf/composer_dev_guide/cvs_setup.php
    * :pserver:anonymous@dev.eclipse.org:/cvsroot/technology/org.eclipse.epf/composer/1.5/plugin
  * http://www.eclipse.org/epf/composer_dev_guide/run_debug.php