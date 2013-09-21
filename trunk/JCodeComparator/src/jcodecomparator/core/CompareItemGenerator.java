package jcodecomparator.core;

/**
 *
 * @author Nicola Pellicano'
 *
 * Generate an ICompareItem instance using infos
 *
 */


public interface CompareItemGenerator {

	/**
	 *
	 * @param element: informations to encapsulate
	 * @param type
	 * @return item
	 */

	public ICompareItem getCompareItem(Object element, String type);

}
