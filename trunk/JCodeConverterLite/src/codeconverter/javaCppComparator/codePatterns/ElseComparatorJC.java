package codeconverter.javaCppComparator.codePatterns;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.PieceType;
import codeconverter.comparator.CodePatternComparator;
import codeconverter.javaCppComparator.codePieces.BooleanExpressionComparatorJC;
import codeconverter.javaJsComparator.codePieces.BooleanExpressionComparator;

public class ElseComparatorJC implements CodePatternComparator{

	@Override
	public boolean compare(CodePattern javaPattern, CodePattern cppPattern) {

		if (javaPattern.getPatternType().get(0) != PatternType.ELSE) {
			return false;
		}
		if (javaPattern.getPatternType().get(0) != cppPattern
				.getPatternType().get(0)) {
			return false;
		}

		if (javaPattern.getPieces().size() != cppPattern.getPieces().size()) {
			return false;
		}

		if (javaPattern.getPieces().size() > 2) {
			if (javaPattern.getPieces().get(2).getPieceType() != cppPattern.getPieces().get(2).getPieceType()) {
				return false;
			}
			if (javaPattern.getPieces().get(2).getPieceType() != PieceType.IGNORED) {
				if (!new BooleanExpressionComparatorJC().compare(
						javaPattern.getPieces().get(2).getPieceByType(PieceType.EXPRESSION), cppPattern
								.getPieces().get(2).getPieceByType(PieceType.EXPRESSION))) {
					return false;
				}
			}
		}

		return true;
	}



}

