package eu.tanov.epf.pv.ocl.util;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EStructuralFeature.Internal.DynamicValueHolder;
import org.eclipse.emf.ecore.EStructuralFeature.Internal.SettingDelegate;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.InternalEObject;

public abstract class AbstractReadOnlySettingDelegate implements SettingDelegate {

	@Override
	public Setting dynamicSetting(InternalEObject owner, DynamicValueHolder settings, int dynamicFeatureID) {
		throw new UnsupportedOperationException(String.format("dynamicSetting(%s, %s, %s)", owner, settings, dynamicFeatureID));
	}

	@Override
	public abstract Object dynamicGet(InternalEObject owner, DynamicValueHolder settings, int dynamicFeatureID, boolean resolve,
			boolean coreType);

	@Override
	public void dynamicSet(InternalEObject owner, DynamicValueHolder settings, int dynamicFeatureID, Object newValue) {
		throw new UnsupportedOperationException(String.format("dynamicSet(%s, %s, %s, %s)", owner, settings, dynamicFeatureID,
				newValue));
	}

	@Override
	public boolean dynamicIsSet(InternalEObject owner, DynamicValueHolder settings, int dynamicFeatureID) {
		throw new UnsupportedOperationException(String.format("dynamicIsSet(%s, %s, %s)", owner, settings, dynamicFeatureID));
	}

	@Override
	public void dynamicUnset(InternalEObject owner, DynamicValueHolder settings, int dynamicFeatureID) {
		throw new UnsupportedOperationException(String.format("dynamicUnset(%s, %s, %s)", owner, settings, dynamicFeatureID));
	}

	@Override
	public NotificationChain dynamicInverseAdd(InternalEObject owner, DynamicValueHolder settings, int dynamicFeatureID,
			InternalEObject otherEnd, NotificationChain notifications) {
		throw new UnsupportedOperationException(String.format("dynamicInverseAdd(%s, %s, %s, %s, %s)", owner, settings,
				dynamicFeatureID, otherEnd, notifications));
	}

	@Override
	public NotificationChain dynamicInverseRemove(InternalEObject owner, DynamicValueHolder settings, int dynamicFeatureID,
			InternalEObject otherEnd, NotificationChain notifications) {
		throw new UnsupportedOperationException(String.format("dynamicInverseRemove(%s, %s, %s, %s, %s)", owner, settings,
				dynamicFeatureID, otherEnd, notifications));
	}

}
