package codeconverter.factories.test;

import codeconverter.ComparatorsHolder;
import codeconverter.comparator.ignored.CppIgnoredPatterns;
import codeconverter.comparator.ignored.HeaderIgnoredPatterns;
import codeconverter.comparator.ignored.IgnoredHolder;
import codeconverter.comparator.ignored.JavaIgnoredPatterns;
import codeconverter.comparator.ignored.JavaIgnoredPatternsCpp;
import codeconverter.comparator.ignored.JavaIgnoredPatternsH;
import codeconverter.comparator.ignored.NothingToIgnorePatterns;
import codeconverter.javaCppComparator.JavaCppCodePatternComparators;
import codeconverter.javaCppComparator.JavaHeaderCodePatternComparators;
import codeconverter.javaJsComparator.JavaJsCodePatternComparators;

/**
 *
 * @author Nicola Pellicano'
 *
 */

public class TestComparatorFactory extends ConcreteComparatorFactory{


	public TestComparatorFactory() {

		list.add(new ComparatorsHolder("java", "js", new JavaJsCodePatternComparators()));
		list.add(new ComparatorsHolder("java", "cpp", new JavaCppCodePatternComparators()));
		list.add(new ComparatorsHolder("java", "h", new JavaHeaderCodePatternComparators()));


		list2.add(new IgnoredHolder("java", "js", new JavaIgnoredPatterns(), new NothingToIgnorePatterns()));
		list2.add(new IgnoredHolder("java", "cpp", new JavaIgnoredPatternsCpp() , new CppIgnoredPatterns()));
		list2.add(new IgnoredHolder("java", "h", new JavaIgnoredPatternsH(), new HeaderIgnoredPatterns()));

	}

}
