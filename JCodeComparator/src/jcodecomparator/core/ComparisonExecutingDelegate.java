package jcodecomparator.core;

import java.util.List;

import codeconverter.DifferentiationResult;



public interface ComparisonExecutingDelegate {


	public  DifferentiationResult executeComparison(ICompareItem left, ICompareItem right);


}
