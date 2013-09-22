package codeconverter.comparator;

import java.util.List;

/**
 *Class that contains a list of supported comparators
 *
 * @author Nicola Pellicano'
 *
 */


public interface PatternComparator {

	/**
	 *
	 * @return the comparators
	 */

	public List<CodePatternComparator> getComparators();
}
