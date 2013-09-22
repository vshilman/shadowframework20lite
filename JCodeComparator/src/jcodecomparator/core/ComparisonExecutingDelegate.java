package jcodecomparator.core;


import codeconverter.DifferentiationResult;

/**
 *
 * @author Nicola Pellicano'
 *
 * The class that calls the external methods for comparison
 *
 */


public interface ComparisonExecutingDelegate {

	/**
	 *
	 * @return: differences
	 */

	public  DifferentiationResult executeComparison(ICompareItem left, ICompareItem right);


}
