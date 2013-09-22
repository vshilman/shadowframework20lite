package codeconverter.javaCppComparator.codePatterns;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.PieceType;
import codeconverter.comparator.CodePatternComparator;
import codeconverter.javaCppComparator.codePieces.MethodVariablesComparatorJC;
import codeconverter.javaCppComparator.codePieces.TypeComparatorJC;

public class MethodDeclarationComparatorJCH implements CodePatternComparator{

	@Override
	public boolean compare(CodePattern javaPattern, CodePattern cppPattern) {

		System.out.println(javaPattern.toString()+" vs "+cppPattern.toString());
		System.out.println(javaPattern.getPatternType().get(0)+" vs "+cppPattern.getPatternType().get(0));

		if (javaPattern.getPatternType().get(0) != PatternType.METHOD_DECLARATION) {
			System.out.println("1");
			return false;
		}
		if (javaPattern.getPatternType().get(0) != cppPattern
				.getPatternType().get(0)) {
			System.out.println("2");
			return false;
		}

		if(!new TypeComparatorJC().compare(javaPattern.getPieces().get(2).getPieces().get(0), cppPattern.getPieces().get(1).getPieces().get(0).getPieces().get(1))){
			System.out.println("ma ci entri qua maledetto cretino?");
			return false;
		}

		//System.out.println(javaPattern.getPieces().get(2).getPieces().get(0).toString()+" --- "+cppPattern.getPieces().get(1).getPieces().get(0).getPieces().get(1).toString());

		if (!javaPattern.getPieces().get(2).getPieces().get(1).toString().trim()
				.equals(cppPattern.getPieces().get(1).getPieces().get(1).toString().trim())) {
			System.out.println("3");
			return false;
		}
		if (!new MethodVariablesComparatorJC().compare(javaPattern.getPieceByType(PieceType.METHOD_VARIABLES),
				cppPattern.getPieceByType(PieceType.METHOD_VARIABLES))) {
			System.out.println("4");
			return false;
		}

		return true;


	}



}
