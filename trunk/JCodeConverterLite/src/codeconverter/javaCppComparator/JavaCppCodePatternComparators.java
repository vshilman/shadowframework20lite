package codeconverter.javaCppComparator;

import java.util.ArrayList;
import java.util.List;

import tests.tmp.PrototypedMethodDeclarationComparator;
import codeconverter.comparator.CodePatternComparator;
import codeconverter.comparator.PatternComparator;
import codeconverter.javaCppComparator.codePatterns.AttributeAssignmentComparatorJC;
import codeconverter.javaCppComparator.codePatterns.AttributeDeclarationComparatorJCH;
import codeconverter.javaCppComparator.codePatterns.ConstructorDeclarationComparatorJC;
import codeconverter.javaCppComparator.codePatterns.ElseComparatorJC;
import codeconverter.javaCppComparator.codePatterns.ForComparatorJC;
import codeconverter.javaCppComparator.codePatterns.IfComparatorJC;
import codeconverter.javaCppComparator.codePatterns.IsolatedKeywordComparatorJC;
import codeconverter.javaCppComparator.codePatterns.MethodAccessComparatorJC;
import codeconverter.javaCppComparator.codePatterns.MethodDeclarationComparatorJC;
import codeconverter.javaCppComparator.codePatterns.ReturnComparatorJC;
import codeconverter.javaCppComparator.codePatterns.VariableAssignmentComparatorJC;
import codeconverter.javaCppComparator.codePatterns.VariableDeclarationAndAssignmentComparatorJC;
import codeconverter.javaCppComparator.codePatterns.VariableDeclarationComparatorJC;


public class JavaCppCodePatternComparators implements PatternComparator{

	private  List<CodePatternComparator> comparators = new ArrayList<CodePatternComparator>();

	public  List<CodePatternComparator> getComparators() {

		comparators.add(new AttributeAssignmentComparatorJC());
		comparators.add(new ConstructorDeclarationComparatorJC());
		comparators.add(new ElseComparatorJC());
		comparators.add(new ForComparatorJC());
		comparators.add(new IfComparatorJC());
		comparators.add(new IsolatedKeywordComparatorJC());
		comparators.add(new MethodAccessComparatorJC());
		comparators.add(new MethodDeclarationComparatorJC());
		comparators.add(new ReturnComparatorJC());
		comparators.add(new VariableAssignmentComparatorJC());
		comparators.add(new VariableDeclarationAndAssignmentComparatorJC());
		comparators.add(new VariableDeclarationComparatorJC());

		return comparators;
	}

}
