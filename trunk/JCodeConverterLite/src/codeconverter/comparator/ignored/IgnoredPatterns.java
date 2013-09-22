package codeconverter.comparator.ignored;

import java.util.List;

import codeconverter.PatternType;

public interface IgnoredPatterns {

	public List<PatternType> getIgnoredtypes();

	public boolean ignoredContainsAny(List<PatternType> types);

}