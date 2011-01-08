package eu.tanov.epf.pv.ui.techniques.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.DescribableElement;
import org.eclipse.epf.uma.Task;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.WorkProduct;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestTechniquesHelper {

	@BeforeClass
	public static void initPluginSystem() {
		new LibraryEditPlugin();
	}

	@Test(expected = NullPointerException.class)
	public void updateWorkProductsNullTechnique() {
		TechniquesHelper.updateWorkProducts(null);
	}

	@Test
	public void updateWorkProductsAllToAdd() {
		final CustomCategory technique = UmaFactory.eINSTANCE.createCustomCategory();

		final WorkProduct workProduct1 = UmaFactory.eINSTANCE.createWorkProduct();
		final WorkProduct workProduct2 = UmaFactory.eINSTANCE.createWorkProduct();

		final Task task = UmaFactory.eINSTANCE.createTask();
		task.getMandatoryInput().add(workProduct1);
		task.getMandatoryInput().add(workProduct2);

		technique.getCategorizedElements().add(task);

		final List<DescribableElement> beforeUpdate = technique.getCategorizedElements();
		assertEquals(1, beforeUpdate.size());
		assertSame(task, beforeUpdate.get(0));

		TechniquesHelper.updateWorkProducts(technique);

		final List<DescribableElement> afterUpdate = technique.getCategorizedElements();
		assertEquals(3, afterUpdate.size());
		assertSame(task, afterUpdate.get(0));

		// order of automatically added work products is not guaranteed
		assertEqualsIgnoreOrder(afterUpdate.subList(1, afterUpdate.size()), workProduct1, workProduct2);
	}

	// XXX move to test helper
	private static void assertEqualsIgnoreOrder(List<?> expectedObjects, Object... actualObjects) {
		assertEquals(actualObjects.length, expectedObjects.size());

		final ArrayList<Object> remainingExpected = new ArrayList<Object>(expectedObjects);

		for (Object object : actualObjects) {
			final int indexOf = remainingExpected.indexOf(object);
			assertTrue("Not found " + object + " in " + expectedObjects, indexOf != -1);
			remainingExpected.remove(indexOf);
		}
	}

	@Test
	public void updateWorkProductsNothingToAdd() {
		final CustomCategory technique = UmaFactory.eINSTANCE.createCustomCategory();

		final WorkProduct workProduct1 = UmaFactory.eINSTANCE.createWorkProduct();
		final WorkProduct workProduct2 = UmaFactory.eINSTANCE.createWorkProduct();

		final Task task = UmaFactory.eINSTANCE.createTask();
		task.getMandatoryInput().add(workProduct1);
		task.getMandatoryInput().add(workProduct2);

		technique.getCategorizedElements().add(workProduct1);
		technique.getCategorizedElements().add(workProduct2);

		assertEquals(technique.getCategorizedElements(), Arrays.asList(workProduct1, workProduct2));

		technique.getCategorizedElements().add(task);

		assertEquals(technique.getCategorizedElements(), Arrays.asList(workProduct1, workProduct2, task));

		TechniquesHelper.updateWorkProducts(technique);

		assertEquals(technique.getCategorizedElements(), Arrays.asList(workProduct1, workProduct2, task));
	}

	@Test
	public void updateWorkProductsNotAllAdded() {
		final CustomCategory technique = UmaFactory.eINSTANCE.createCustomCategory();

		final WorkProduct workProduct1 = UmaFactory.eINSTANCE.createWorkProduct();
		final WorkProduct workProduct2 = UmaFactory.eINSTANCE.createWorkProduct();

		final Task task = UmaFactory.eINSTANCE.createTask();
		task.getMandatoryInput().add(workProduct1);
		task.getMandatoryInput().add(workProduct2);

		technique.getCategorizedElements().add(workProduct1);

		assertEquals(technique.getCategorizedElements(), Arrays.asList(workProduct1));

		technique.getCategorizedElements().add(task);

		assertEquals(technique.getCategorizedElements(), Arrays.asList(workProduct1, task));

		TechniquesHelper.updateWorkProducts(technique);

		assertEquals(technique.getCategorizedElements(), Arrays.asList(workProduct1, task, workProduct2));
	}

	@Test
	public void updateWorkProductsSkipOptionalInput() {
		final CustomCategory technique = UmaFactory.eINSTANCE.createCustomCategory();

		final WorkProduct workProduct1 = UmaFactory.eINSTANCE.createWorkProduct();
		final WorkProduct workProduct2 = UmaFactory.eINSTANCE.createWorkProduct();

		final Task task = UmaFactory.eINSTANCE.createTask();
		task.getMandatoryInput().add(workProduct1);
		task.getOptionalInput().add(workProduct2);

		technique.getCategorizedElements().add(task);

		assertEquals(technique.getCategorizedElements(), Arrays.asList(task));

		TechniquesHelper.updateWorkProducts(technique);

		assertEquals(technique.getCategorizedElements(), Arrays.asList(task, workProduct1));
	}

	@Test
	public void isTechniqueNotCustomCategory() {
		assertFalse(TechniquesHelper.isTechnique(null));
		assertFalse(TechniquesHelper.isTechnique(UmaFactory.eINSTANCE.createDiscipline()));
	}

	@Test
	public void isTechniqueCustomCategoryWithoutContainer() {
		assertFalse(TechniquesHelper.isTechnique(UmaFactory.eINSTANCE.createCustomCategory()));
	}

	@Test
	public void isTechniqueCustomCategoryWithContainer() {
		final ContentPackage contentPackage = UmaFactory.eINSTANCE.createContentPackage();

		final CustomCategory category = UmaFactory.eINSTANCE.createCustomCategory();
		contentPackage.getContentElements().add(category);

		assertFalse(TechniquesHelper.isTechnique(category));

		contentPackage.setName("Techniques");
		// if name of content package is "Techniques" - then this is technique:
		assertTrue(TechniquesHelper.isTechnique(category));
	}

}
