package codeconverter.factories;

import codeconverter.comparator.PatternComparator;

public interface ComparatorFactory {
	public PatternComparator getComparators(String lang1,String lang2);
}
