package eu.tanov.epf.pv.ui.ocl.preference;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class OneColumnMultilineStringFieldEditor extends StringFieldEditor {
	/**
	 * The text field, or <code>null</code> if none.
	 * 
	 * Why it has package visibility in parent?
	 */
	protected Text textField;

	/**
	 * Text limit of text field in characters; initially unlimited.
	 * 
	 * Why it has private visibility in parent?
	 */
	protected int textLimit = UNLIMITED;

	/**
	 * The validation strategy; <code>VALIDATE_ON_KEY_STROKE</code> by default.
	 * 
	 * Why it has private visibility in parent?
	 */
	protected int validateStrategy = VALIDATE_ON_KEY_STROKE;

	private StringFieldValidator contentValidator;

	public interface StringFieldValidator {
		public boolean validate(String content);
	}

	protected OneColumnMultilineStringFieldEditor() {
	}

	public OneColumnMultilineStringFieldEditor(String name, String labelText, int width, int strategy, Composite parent) {
		super(name, labelText, width, strategy, parent);
	}

	public OneColumnMultilineStringFieldEditor(String name, String labelText, int width, Composite parent) {
		this(name, labelText, width, VALIDATE_ON_KEY_STROKE, parent);
	}

	public OneColumnMultilineStringFieldEditor(String name, String labelText, Composite parent) {
		this(name, labelText, UNLIMITED, parent);
	}

	@Override
	public int getNumberOfControls() {
		return 1;
	}

	@Override
	protected void doFillIntoGrid(Composite parent, int numColumns) {
		super.doFillIntoGrid(parent, numColumns);
		// add vertical fill

		final Object parentLayoutData = parent.getLayoutData();

		if (parentLayoutData instanceof GridData) {
			final GridData parentGridData = (GridData) parentLayoutData;
			parentGridData.verticalAlignment = GridData.FILL;
			parentGridData.grabExcessVerticalSpace = true;
		}
		final Object layoutData = getTextControl().getLayoutData();
		if (layoutData instanceof GridData) {
			final GridData textGridData = (GridData) layoutData;

			textGridData.verticalAlignment = GridData.FILL;
			textGridData.grabExcessVerticalSpace = true;
		}
	}

	@Override
	public Text getTextControl(Composite parent) {
		if (textField == null) {
			textField = new Text(parent, SWT.MULTI | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
			textField.setFont(parent.getFont());
			switch (validateStrategy) {
			case VALIDATE_ON_KEY_STROKE:
				textField.addKeyListener(new KeyAdapter() {

					/*
					 * (non-Javadoc)
					 * 
					 * @see org.eclipse.swt.events.KeyAdapter#keyReleased(org.eclipse.swt.events.KeyEvent)
					 */
					public void keyReleased(KeyEvent e) {
						valueChanged();
					}
				});
				textField.addFocusListener(new FocusAdapter() {
					// Ensure that the value is checked on focus loss in case we
					// missed a keyRelease or user hasn't released key.
					// See https://bugs.eclipse.org/bugs/show_bug.cgi?id=214716
					public void focusLost(FocusEvent e) {
						valueChanged();
					}
				});

				break;
			case VALIDATE_ON_FOCUS_LOST:
				textField.addKeyListener(new KeyAdapter() {
					public void keyPressed(KeyEvent e) {
						clearErrorMessage();
					}
				});
				textField.addFocusListener(new FocusAdapter() {
					public void focusGained(FocusEvent e) {
						refreshValidState();
					}

					public void focusLost(FocusEvent e) {
						valueChanged();
						clearErrorMessage();
					}
				});
				break;
			default:
				Assert.isTrue(false, "Unknown validate strategy");//$NON-NLS-1$
			}
			textField.addDisposeListener(new DisposeListener() {
				public void widgetDisposed(DisposeEvent event) {
					textField = null;
				}
			});
			if (textLimit > 0) {// Only set limits above 0 - see SWT spec
				textField.setTextLimit(textLimit);
			}
		} else {
			checkParent(textField, parent);
		}
		return textField;
	}

	@Override
	public void setTextLimit(int limit) {
		textLimit = limit;
		if (textField != null) {
			textField.setTextLimit(limit);
		}
	}

	@Override
	public void setValidateStrategy(int value) {
		Assert.isTrue(value == VALIDATE_ON_FOCUS_LOST || value == VALIDATE_ON_KEY_STROKE);
		validateStrategy = value;
	}

	public void setContentValidator(StringFieldValidator contentValidator) {
		this.contentValidator = contentValidator;
	}

	@Override
	protected boolean doCheckState() {
		if (contentValidator == null) {
			// no content validator - validation is OK
			return true;
		}

		return contentValidator.validate(getTextControl().getText());
	}

}
