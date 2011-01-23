package eu.tanov.epf.pv.types.${typeNamePackage}.common.handler;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Internal.SettingDelegate;
import org.eclipse.epf.uma.Task;

import eu.tanov.epf.pv.service.types.util.CustomCategoryCategorizedElementsReadOnlySettingDelegate;

public class TasksSettingDelegateFactory implements SettingDelegate.Factory {

	@Override
	public SettingDelegate createSettingDelegate(EStructuralFeature eStructuralFeature) {
		return new CustomCategoryCategorizedElementsReadOnlySettingDelegate<Task>(Task.class);
	}

}
