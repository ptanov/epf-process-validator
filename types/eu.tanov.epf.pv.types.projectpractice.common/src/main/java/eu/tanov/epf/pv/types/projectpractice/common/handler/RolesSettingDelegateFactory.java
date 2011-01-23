package eu.tanov.epf.pv.types.projectpractice.common.handler;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Internal.SettingDelegate;
import org.eclipse.epf.uma.Role;

import eu.tanov.epf.pv.service.types.util.CustomCategoryCategorizedElementsReadOnlySettingDelegate;

public class RolesSettingDelegateFactory implements SettingDelegate.Factory {

	@Override
	public SettingDelegate createSettingDelegate(EStructuralFeature eStructuralFeature) {
		return new CustomCategoryCategorizedElementsReadOnlySettingDelegate<Role>(Role.class);
	}

}
