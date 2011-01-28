package eu.tanov.epf.pv.wizards.completeness.wizard;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IExportWizard;
import org.eclipse.ui.IWorkbench;

import eu.tanov.epf.pv.wizards.completeness.pages.SelectFeaturePage;
import eu.tanov.epf.pv.wizards.completeness.preference.OCLConstraintsPreference;

public class CompletenessWizard extends Wizard implements IExportWizard {

	private EClass eclass;
	private TreePath[] path;

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		addPage(new SelectFeaturePage());
		if (!(selection.getFirstElement() instanceof EObject)) {
			throw new IllegalArgumentException("Selection should be EObject, not: " + selection.getFirstElement());
		}
		this.eclass = (EClass) ((EObject) selection.getFirstElement()).eClass();
	}

	@Override
	public boolean performFinish() {
		if (path == null) {
			// throw new IllegalStateException("Finish, but nothing selected");
			return false;
		}

		final String ocl = generateOCL(eclass, path);
		
		registerOCL(ocl);

		return true;
	}

	private void registerOCL(String ocl) {
		String oclContent = OCLConstraintsPreference.PREFERENCE_STORE.getString(OCLConstraintsPreference.NAME_OCL_CONTENT);
		OCLConstraintsPreference.PREFERENCE_STORE.setValue(OCLConstraintsPreference.NAME_OCL_CONTENT,oclContent+"\n\n"+ocl);
		OCLConstraintsPreference.registerOCLContent();
	}

	private static String generateOCL(EClass eclass, TreePath[] path) {
		final StringBuilder result = new StringBuilder();
		result.append(String.format("context %s::%s\n", eclass.getEPackage().getName(), eclass.getName()));
		result.append(String.format("inv %s: not self.%s->isEmpty()", generateName(eclass, path), generateSequence(eclass, path)));

		return result.toString();
	}

	private static List<String> calculateParents(EClass eclass, TreePath[] path) {
		if (path.length != 1) {
			throw new IllegalArgumentException("path: " + Arrays.toString(path));
		}
		final LinkedList<String> result = new LinkedList<String>();

		for (int i = 0; i < path[0].getSegmentCount(); i++) {
			final Object segment = path[0].getSegment(i);
			if (!(segment instanceof EReference)) {
				throw new IllegalArgumentException("not EReference: " + segment);
			}
			result.add(((EReference) segment).getName());
		}

		return result;

	}

	private static String generateSequence(EClass eclass, TreePath[] path) {
		final List<String> calculateParents = calculateParents(eclass, path);
		final StringBuilder result = new StringBuilder(calculateParents.size() * 15);
		for (String parent : calculateParents) {
			result.append(parent).append(".");
		}

		// remove last .
		result.delete(result.length() - 1, result.length());

		return result.toString();
	}

	private static String generateName(EClass eclass, TreePath[] path) {
		final List<String> calculateParents = calculateParents(eclass, path);
		final StringBuilder result = new StringBuilder(calculateParents.size() * 15);
		result.append(eclass.getName()).append("_");
		for (String parent : calculateParents) {
			result.append(parent).append("_");
		}

		// remove last _
		result.delete(result.length() - 1, result.length());

		return result.toString();
	}

	public EClass getEclass() {
		return eclass;
	}

	public void setSelectedPath(TreePath[] pathsFor) {
		this.path = pathsFor;
	}

}
