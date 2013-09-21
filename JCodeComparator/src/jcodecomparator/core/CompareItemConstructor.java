package jcodecomparator.core;

import org.eclipse.jface.viewers.ISelection;

/**
 *
 * @author Nicola Pellicano'
 *
 * Interface for getting the right ICompareItem from a generic ISelection.
 *
 */


public interface CompareItemConstructor {

	/**
	 *
	 * @param selection
	 * @return the compare item with the content of the selection
	 */

	public ICompareItem getCompareItem(ISelection selection);

	/**
	 *
	 * @return clone
	 */

	public CompareItemConstructor clone();

}
