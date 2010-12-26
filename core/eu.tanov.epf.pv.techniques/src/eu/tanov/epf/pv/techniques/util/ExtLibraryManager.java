package eu.tanov.epf.pv.techniques.util;

import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.util.ModelStructure;
import org.eclipse.epf.library.util.LibraryManager;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.Task;
import org.eclipse.epf.uma.UmaPackage;

public class ExtLibraryManager extends LibraryManager {
	private static ExtLibraryManager instance = null;

	public static final ExtLibraryManager getExtInstance() {
		if (instance == null) {
			synchronized (LibraryManager.class) {
				if (instance == null) {
					instance = new ExtLibraryManager();
				}
			}
		}
		return instance;
	}

	protected ExtLibraryManager() {
	}
	/**
	 * add a task to a discipline
	 * 
	 * @param actionMgr
	 * @param discipline
	 * @param task
	 * @return Discipline
	 */
	public CustomCategory addToTechnique(IActionManager actionMgr,
			CustomCategory discipline, Task task, List usedCategories) {
		return (CustomCategory) addToCategory(actionMgr, discipline, task,
				 UmaPackage.eINSTANCE
					.getCustomCategory_CategorizedElements(),
				ModelStructure.DEFAULT.domainPath, true, usedCategories);
	}
	
	public boolean removeFromTechnique(IActionManager actionMgr,
			CustomCategory discipline, Task task, List usedCategories) {
		EStructuralFeature feature = UmaPackage.eINSTANCE.getCustomCategory_CategorizedElements();
		return removeFromCategory(actionMgr, discipline, task, feature,
				ModelStructure.DEFAULT.domainPath, usedCategories);
	}


}
