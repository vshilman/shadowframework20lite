package codeconverter.factories;

import codeconverter.ComparatorsHolder;
import codeconverter.comparator.ignored.IgnoredHolder;

/**
 * Factory of object that depended from every language of the comparison
 *
 * @author Nicola Pellicano'
 *
 */

public interface ComparatorFactory {
	/**
	 *
	 * @param lang1
	 * @param lang2
	 * @return the comparators holder
	 */
	public ComparatorsHolder getComparators(String lang1,String lang2);
	/**
	 *
	 * @param lang1
	 * @param lang2
	 * @return the ignored holder
	 */

	public IgnoredHolder getIgnoreds(String lang1,String lang2);
}
