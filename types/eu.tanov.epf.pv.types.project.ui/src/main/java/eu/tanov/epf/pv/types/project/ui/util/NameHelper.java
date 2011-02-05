package eu.tanov.epf.pv.types.project.ui.util;

import java.util.List;

import org.eclipse.epf.uma.DescribableElement;
import org.eclipse.epf.uma.MethodElement;

import com.ibm.icu.util.StringTokenizer;

public class NameHelper {

	/**
	 * Helper
	 */
	private NameHelper() {
	}

	/**
	 * TODO Copied from org.eclipse.epf.library.edit.util.TngUtil in order to add _1 to first element, issue 173
	 */
	public static void setDefaultName(List<? extends MethodElement> siblings, MethodElement e, String baseName) {
		if (e.getName() != null && e.getName().trim().length() > 0)
			return;

		for (int i = 1; true; i++) {
			String name = baseName + '_' + i;
			if (!isNameTaken(siblings, e, name)) {
				e.setName(name);
				setPresentationName(e, name);
				return;
			}
		}
	}

	/**
	 * TODO Copied from org.eclipse.epf.library.edit.util.TngUtil
	 */
	private static boolean isNameTaken(List<? extends MethodElement> siblings, MethodElement e, String name) {
		for (int i = siblings.size() - 1; i > -1; i--) {
			MethodElement sibling = (MethodElement) siblings.get(i);
			if (sibling != e && name.equalsIgnoreCase(sibling.getName())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * TODO Copied from org.eclipse.epf.library.edit.util.TngUtil
	 */
	private static void setPresentationName(MethodElement e, String baseName) {
		if (e instanceof DescribableElement) {
			DescribableElement de = (DescribableElement) e;

			StringBuffer presNameBuf = new StringBuffer();
			StringTokenizer st = new StringTokenizer(baseName, "_"); //$NON-NLS-1$
			while (st.hasMoreTokens()) {
				String aWord = st.nextToken();
				presNameBuf.append(aWord.substring(0, 1).toUpperCase() + aWord.substring(1) + " "); //$NON-NLS-1$
			}

			de.setPresentationName(presNameBuf.toString().trim());
		}
	}
}
