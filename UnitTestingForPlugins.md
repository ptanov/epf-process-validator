# Introduction #

  * http://www.eclipse.org/articles/article.php?file=Article-PDEJUnitAntAutomation/index.html
  * http://www.eclipse.org/articles/Article-PDE-Automation/automation.html
  * http://stackoverflow.com/questions/255370/automating-unit-tests-junit-for-eclipse-plugin-development


# Details #

For every plugin that should be tested - respective test project should be created.
  * Type of project: "Fragment Project"
  * Name of project: `plugin-under-test-name` + "-test"
  * Host project: `plugin-under-test`
  * Directory for project: "test/" + `directory-path-of-plugin-under-test`:
```
for code:
 core/
   eu.tanov.epf.pv.ui.techniques

for test:
 test/
   core/
     eu.tanov.epf.pv.ui.techniques-test
```