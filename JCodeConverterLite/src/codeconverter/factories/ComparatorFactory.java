package codeconverter.factories;

import codeconverter.ComparatorsHolder;
import codeconverter.comparator.PatternComparator;
import codeconverter.comparator.ignored.IgnoredHolder;
import codeconverter.comparator.ignored.IgnoredPatterns;

public interface ComparatorFactory {
	public ComparatorsHolder getComparators(String lang1,String lang2);
	public IgnoredHolder getIgnoreds(String lang1,String lang2);
}
