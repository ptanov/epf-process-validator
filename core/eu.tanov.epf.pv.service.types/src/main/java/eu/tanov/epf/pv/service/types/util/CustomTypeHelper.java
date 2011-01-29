package eu.tanov.epf.pv.service.types.util;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Internal.SettingDelegate;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.epf.uma.UmaPackage;

import eu.tanov.epf.pv.service.types.service.impl.CustomTypeHandlersServiceImpl;

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

	public static EReference createStructuralFeatureList(EClass clazz, String name, EClass type, SettingDelegate settingDelegate) {
		final EReference result = EcoreFactory.eINSTANCE.createEReference();
		result.setName(name);
		result.setEType(type);
		result.setUpperBound(EStructuralFeature.UNBOUNDED_MULTIPLICITY);
		result.setDerived(true);
		result.setTransient(true);
		result.setVolatile(true);
		result.setChangeable(false);
		result.setContainment(false);

		addSettingDelegate(result, settingDelegate, String.format(PATTERN_SETTING_DELEGATE_NAME, clazz.getName(), name));

		return result;
	}

	/**
	 * XXX SettingDelegate API is changed from emf.ecore 2.5.0 to 2.6.1
	 */
	private static void addSettingDelegate(EReference structuralFeature, SettingDelegate settingDelegate,
			String settingDelegateName) {

		final EStructuralFeature.Internal internalStrtucturalFeature = (EStructuralFeature.Internal) structuralFeature;
		internalStrtucturalFeature.setSettingDelegate(settingDelegate);
	}

	public static <T extends EObject> T copy(T eObject, EClass targetType) {
		final EcoreUtil.Copier copier = new CopierWithCustomType(targetType);
		final EObject result = copier.copy(eObject);
		copier.copyReferences();

		@SuppressWarnings("unchecked")
		final T castedResult = (T) result;
		return castedResult;
	}

	public static EClass getCustomType(String customTypeName) {
		final EPackage extendedUmaPackage = getExtendedUmaPackage();
		final EClassifier result = extendedUmaPackage.getEClassifier(customTypeName);

		if (!(result instanceof EClass)) {
			throw new IllegalArgumentException(String.format("Custom type %s is not EClass, but %s", customTypeName, result));
		}

		return (EClass) result;
	}

	public static boolean isRegistered(String customTypeName) {
		final EPackage extendedUmaPackage = getExtendedUmaPackage();
		return extendedUmaPackage.getEClassifier(customTypeName) != null;
	}

	public static EPackage getExtendedUmaPackage() {
		final Object result = EPackage.Registry.INSTANCE.get(CustomTypeHandlersServiceImpl.NS_URI_EXTENDED_UMA);

		if (!(result instanceof EPackage)) {
			throw new IllegalStateException(String.format("ExtendedUmaPackage is not EPackage, but %s", result));
		}

		return (EPackage) result;
	}
}
