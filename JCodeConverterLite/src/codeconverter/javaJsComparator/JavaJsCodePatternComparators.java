package codeconverter.javaJsComparator;

import java.util.ArrayList;
import java.util.List;

import tests.tmp.PrototypedMethodDeclarationComparator;

import codeconverter.comparator.PatternComparator;
import codeconverter.javaJsComparator.codePatterns.AttributeAndVariableDeclarationAndAssignmentComparator;
import codeconverter.javaJsComparator.codePatterns.AttributeAndVariableDeclarationComparator;
import codeconverter.javaJsComparator.codePatterns.AttributeAssignmentComparator;
import codeconverter.javaJsComparator.codePatterns.ClassDeclarationComparator;
import codeconverter.javaJsComparator.codePatterns.ConstructorDeclarationComparator;
import codeconverter.javaJsComparator.codePatterns.ElseComparator;
import codeconverter.javaJsComparator.codePatterns.ForComparator;
import codeconverter.javaJsComparator.codePatterns.IfComparator;
import codeconverter.javaJsComparator.codePatterns.IsolatedKeywordsComparator;
import codeconverter.javaJsComparator.codePatterns.MethodAccessComparator;
import codeconverter.javaJsComparator.codePatterns.MethodDeclarationComparator;
import codeconverter.javaJsComparator.codePatterns.OpenGlGenBuffersMethodComparator;
import codeconverter.javaJsComparator.codePatterns.OpenGlGenTexturesMethodComparator;
import codeconverter.javaJsComparator.codePatterns.OpenGlMethodAccessComparator;
import codeconverter.javaJsComparator.codePatterns.ReturnComparator;
import codeconverter.javaJsComparator.codePatterns.VariableAssignmentComparator;
import codeconverter.javaJsComparator.codePatterns.VariableDeclarationAndAssignmentComparator;
import codeconverter.javaJsComparator.codePatterns.VariableDeclarationComparator;

public class JavaJsCodePatternComparators implements PatternComparator{

	private  List<CodePatternComparator> comparators = new ArrayList<CodePatternComparator>();

	public  List<CodePatternComparator> getComparators() {

		comparators.add(new AttributeAssignmentComparator());
		comparators.add(new ClassDeclarationComparator());
		comparators.add(new ConstructorDeclarationComparator());
		comparators.add(new ElseComparator());
		comparators.add(new ForComparator());
		comparators.add(new IfComparator());
		comparators.add(new IsolatedKeywordsComparator());
		comparators.add(new MethodAccessComparator());
		comparators.add(new MethodDeclarationComparator());
		comparators.add(new OpenGlMethodAccessComparator());
		comparators.add(new ReturnComparator());
		comparators.add(new VariableAssignmentComparator());
		comparators.add(new VariableDeclarationAndAssignmentComparator());
		comparators.add(new VariableDeclarationComparator());
		comparators.add(new AttributeAndVariableDeclarationComparator());
		comparators.add(new AttributeAndVariableDeclarationAndAssignmentComparator());
		comparators.add(new OpenGlGenBuffersMethodComparator());
		comparators.add(new OpenGlGenTexturesMethodComparator());


		comparators.add(new PrototypedMethodDeclarationComparator());

		return comparators;
	}

}
