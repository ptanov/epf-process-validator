# Details #
New action can be added to specified type (set by attribute `objectClass`) by contributing extension:
```
<extension point="org.eclipse.ui.popupMenus"> 
   <objectContribution id="org.eclipse.ui.articles.action.contribution.popup.object"
      objectClass="java.lang.Object">
      <action
         id="org.eclipse.ui.articles.action.contribution.object.action1" 
         label="Action 5"
         class="org.eclipse.epf.authoring.ui2.menu.ObjectAction1Delegate"> 
      </action> 
   </objectContribution> 
</extension>

```
where `SampleAction` implements `org.eclipse.ui.IObjectActionDelegate`

# Example #
There is example: /eu.tanov.epf.pv.ui.wizards/src/main/java/eu/tanov/epf/pv/ui/wizards/action/CreateSimpleValidationConstraintAction.java
```
package eu.tanov.epf.pv.ui.wizards.action;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

public class CreateSimpleValidationConstraintAction implements IObjectActionDelegate {

	private EObject selectedObject = null;

	@Override
	public void run(IAction action) {
		// do something with selectedObject
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		selectedObject = null;
		if (selection instanceof IStructuredSelection) {
			final IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			if (structuredSelection.getFirstElement() instanceof EObject) {
				selectedObject = (EObject) structuredSelection.getFirstElement();
			}
		}
	}

	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
	}

}
```
# Notes #
The actual type of object can be observed in
```
org.eclipse.ui.IObjectActionDelegate.run(IAction action) {
```