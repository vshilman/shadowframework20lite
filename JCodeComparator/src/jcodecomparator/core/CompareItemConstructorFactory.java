package jcodecomparator.core;

/**
 *
 * @author Nicola Pellicano'
 *
 * Returns the correct CompareItemConstructor using the type of ISelection
 *
 */



public interface CompareItemConstructorFactory {

	/**
	 *
	 * @param canonicalName
	 * @return constructor
	 */

	public CompareItemConstructor generateCompareItemConstructor(String canonicalName);
}
