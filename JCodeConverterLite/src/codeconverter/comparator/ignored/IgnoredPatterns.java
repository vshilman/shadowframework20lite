package codeconverter.comparator.ignored;

import java.util.List;

import codeconverter.PatternType;

/**
 * Contains pattern types that have to be ignored during a comparison
 *
 * @author Nicola Pellicano'
 *
 */

public interface IgnoredPatterns {

	/**
	 *
	 * @return ignored pattern types
	 */

	public List<PatternType> getIgnoredtypes();

	/**
	 *
	 * @param types
	 * @return true if at least one element of types is ignored
	 */

	public boolean ignoredContainsAny(List<PatternType> types);

}