package codeconverter.factories.test;

import tests.tmp.JavaIgnoredPatterns;
import codeconverter.ComparatorsHolder;
import codeconverter.javaJsComparator.JavaJsCodePatternComparators;

public class TestComparatorFactory extends ConcreteComparatorFactory{


	public TestComparatorFactory() {

		list.add(new ComparatorsHolder("java", "js", new JavaJsCodePatternComparators()));


	}

}
