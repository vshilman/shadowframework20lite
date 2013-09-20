package codeconverter.javaCppComparator.codePatterns;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.PieceType;
import codeconverter.comparator.CodePatternComparator;
import codeconverter.javaCppComparator.codePieces.MethodVariablesComparatorJC;
import codeconverter.javaCppComparator.codePieces.TypeComparatorJC;
import codeconverter.javaJsComparator.codePieces.MethodVariablesComparator;

public class MethodDeclarationComparatorJC implements CodePatternComparator{

	@Override
	public boolean compare(CodePattern javaPattern, CodePattern cppPattern) {

		if (javaPattern.getPatternType().get(0) != PatternType.METHOD_DECLARATION) {
			return false;
		}
		if (javaPattern.getPatternType().get(0) != cppPattern
				.getPatternType().get(0)) {
			return false;
		}

		if(!new TypeComparatorJC().compare(javaPattern.getPieces().get(2).getPieces().get(0), cppPattern.getPieces().get(0).getPieces().get(1))){
			return false;
		}

		if (!javaPattern.getPieces().get(2).getPieces().get(1).toString().trim()
				.equals(cppPattern.getPieces().get(3).toString().trim())) {
			System.out.println("ciao ciao");
			return false;
		}
		if (!new MethodVariablesComparatorJC().compare(javaPattern.getPieceByType(PieceType.METHOD_VARIABLES),
				cppPattern.getPieceByType(PieceType.METHOD_VARIABLES))) {
			System.out.println("cisi cisi");
			return false;
		}

		return true;

	}

}
