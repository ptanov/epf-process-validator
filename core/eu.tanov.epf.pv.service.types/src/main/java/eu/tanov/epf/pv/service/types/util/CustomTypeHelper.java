package eu.tanov.epf.pv.service.types.util;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Internal.SettingDelegate;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.epf.uma.UmaPackage;

public class CustomTypeHelper {
	private static final String PATTERN_SETTING_DELEGATE_NAME = "%s-%s";

	/**
	 * helper class
	 */
	private CustomTypeHelper() {
	}

	private static class CopierWithCustomType extends EcoreUtil.Copier {
		private static final long serialVersionUID = -8499429240233477929L;

		private final EClass clazz;

		public CopierWithCustomType(EClass clazz) {
			this.clazz = clazz;
		}

		@Override
		protected EClass getTarget(EClass eClass) {
			if (!eClass.isSuperTypeOf(clazz)) {
				throw new IllegalArgumentException(String.format("eClass %s is not super type of %s", eClass, clazz));
			}
			return clazz;
		}
	}

	public static EClass createType(String name) {
		final EClass result = EcoreFactory.eINSTANCE.createEClass();
		result.setName(name);
		result.getESuperTypes().add(UmaPackage.eINSTANCE.getCustomCategory());
		return result;
	}

	public static EReference createStructuralFeatureList(EPackage extendedUmaPackage, EClass clazz, String name, EClass type,
			SettingDelegate.Factory settingDelegate) {
		final EReference result = EcoreFactory.eINSTANCE.createEReference();
		result.setName(name);
		result.setEType(type);
		result.setUpperBound(EStructuralFeature.UNBOUNDED_MULTIPLICITY);
		result.setDerived(true);
		result.setTransient(true);
		result.setVolatile(true);
		result.setChangeable(false);
		result.setContainment(false);

		addSettingDelegate(extendedUmaPackage, result, settingDelegate,
				String.format(PATTERN_SETTING_DELEGATE_NAME, clazz.getName(), name));

		return result;
	}

	/**
	 * XXX really it is so hard to register setting delegate?
	 */
	private static void addSettingDelegate(EPackage extendedUmaPackage, EReference structuralFeature,
			SettingDelegate.Factory settingDelegate, String settingDelegateName) {
		final EAnnotation settingDelegateAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
		settingDelegateAnnotation.setSource(settingDelegateName);
		structuralFeature.getEAnnotations().add(settingDelegateAnnotation);

		final List<String> oldSettingDelegates = EcoreUtil.getSettingDelegates(extendedUmaPackage);
		final List<String> newSettingDelegates = new ArrayList<String>(oldSettingDelegates.size() + 1);
		newSettingDelegates.addAll(oldSettingDelegates);
		newSettingDelegates.add(settingDelegateName);

		EcoreUtil.setSettingDelegates(extendedUmaPackage, newSettingDelegates);

		SettingDelegate.Factory.Registry.INSTANCE.put(settingDelegateName, settingDelegate);
	}

	public static <T extends EObject> T copy(T eObject, EClass targetType) {
		final EcoreUtil.Copier copier = new CopierWithCustomType(targetType);
		final EObject result = copier.copy(eObject);
		copier.copyReferences();

		@SuppressWarnings("unchecked")
		final T castedResult = (T) result;
		return castedResult;
	}

	public static void addSettingDelegates() {

	}
}
