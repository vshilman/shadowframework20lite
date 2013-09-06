package codeconverter.factories;

import tests.tmp.IgnoredPatterns;
import codeconverter.ComparatorsHolder;
import codeconverter.comparator.PatternComparator;

public interface ComparatorFactory {
	public ComparatorsHolder getComparators(String lang1,String lang2);
}
