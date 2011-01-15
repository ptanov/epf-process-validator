package eu.tanov.epf.pv.ui.common.util;

import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.uma.ContentElement;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.forms.widgets.ScrolledForm;

public class FormHelper {
	/**
	 * Helper
	 */
	private FormHelper() {
	}

	public static void updateFormText(ScrolledForm form, String classNameForFormTitle, ContentElement contentElement) {
		if (form != null && !form.isDisposed()) {
			form.setText(classNameForFormTitle + AuthoringUIResources.editor_title_colon_with_spaces + contentElement.getName());
		}
	}

	/**
	 * Hack to remove last added listener - because AssociationFormPage.addListeners() adds wrong listeners that changes title
	 * with bad value and title blinks - this method helps in avoiding this blinking
	 * 
	 * XXX how to replace safely listeners and keep custom title?
	 * 
	 * @param expectedListenerType
	 *            to be sure that we remove right listener
	 */
	public static void replaceLastListener(Widget widget, int eventType, Listener listener, String expectedListenerType) {
		final Listener[] listeners = widget.getListeners(eventType);
		if (listeners.length < 1) {
			throw new IllegalStateException("no last listener");
		}

		final Listener listenerToRemove = listeners[listeners.length - 1];
		if (!listenerToRemove.getClass().getName().startsWith(expectedListenerType)) {
			throw new IllegalStateException(String.format("listener does not match expected type: %s, but was (%s): %s",
					expectedListenerType, listenerToRemove.getClass().getName(), listenerToRemove));
		}
		widget.removeListener(eventType, listenerToRemove);

		widget.addListener(eventType, listener);
	}
}
