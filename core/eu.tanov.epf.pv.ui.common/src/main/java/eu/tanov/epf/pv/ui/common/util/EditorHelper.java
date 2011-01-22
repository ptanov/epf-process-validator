package eu.tanov.epf.pv.ui.common.util;

import org.eclipse.epf.authoring.ui.editors.MethodElementEditor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.forms.editor.FormEditor;

public class EditorHelper {
	/**
	 * Helper
	 */
	private EditorHelper() {
	}

	/**
	 * Hack to update image of editor, because by default it is set with TngUtil.getImage(elementObj) which provides same icon for
	 * all CustomCategory types, does not respect to custom types
	 * 
	 * @param editor
	 * @param image
	 */
	public static void updateTitleImage(FormEditor editor, Image image) {
		if (editor instanceof MethodElementEditor) {
			final MethodElementEditor methodElementEditor = (MethodElementEditor) editor;
			methodElementEditor.updatedTitleImage(image);
		}
	}
}
