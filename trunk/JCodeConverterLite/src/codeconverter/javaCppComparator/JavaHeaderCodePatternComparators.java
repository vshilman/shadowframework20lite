package codeconverter.javaCppComparator;

import java.util.ArrayList;
import java.util.List;

import tests.tmp.PrototypedMethodDeclarationComparator;
import codeconverter.comparator.CodePatternComparator;
import codeconverter.comparator.PatternComparator;
import codeconverter.javaCppComparator.codePatterns.AttributeDeclarationComparatorJCH;
import codeconverter.javaCppComparator.codePatterns.ClassDeclarationComparatorJCH;
import codeconverter.javaCppComparator.codePatterns.ConstructorDeclarationComparatorJCH;
import codeconverter.javaCppComparator.codePatterns.IsolatedKeywordComparatorJC;
import codeconverter.javaCppComparator.codePatterns.MethodDeclarationComparatorJCH;


public class JavaHeaderCodePatternComparators implements PatternComparator{

	private  List<CodePatternComparator> comparators = new ArrayList<CodePatternComparator>();

	public  List<CodePatternComparator> getComparators() {

		comparators.add(new AttributeDeclarationComparatorJCH());
		comparators.add(new ClassDeclarationComparatorJCH());
		comparators.add(new ConstructorDeclarationComparatorJCH());
		comparators.add(new IsolatedKeywordComparatorJC());
		comparators.add(new MethodDeclarationComparatorJCH());

		return comparators;
	}

}
