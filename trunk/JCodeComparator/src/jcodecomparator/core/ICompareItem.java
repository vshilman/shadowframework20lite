package jcodecomparator.core;

import java.io.InputStream;

import org.eclipse.compare.ITypedElement;

/**
 * Defines a comparable object
 *
 * @author Nicola Pellicano'
 *
 */


public interface ICompareItem extends ITypedElement {

		/**
		 * Generic class to set all the needed information to the object
		 * @param element
		 */
		public void setInfo(Object element);
		/**
		 * returns the object information for comparison
		 * @return
		 */

		public InputStream getContents();

}
