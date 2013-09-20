package codeconverter.javaCppComparator.codePatterns;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.PieceType;
import codeconverter.comparator.CodePatternComparator;
import codeconverter.javaCppComparator.codePieces.BooleanExpressionComparatorJC;
import codeconverter.javaJsComparator.codePieces.BooleanExpressionComparator;

public class IfComparatorJC implements CodePatternComparator{

	@Override
	public boolean compare(CodePattern javaPattern, CodePattern cppPattern) {

		if (javaPattern.getPatternType().get(0) != PatternType.IF) {
			return false;
		}
		if (javaPattern.getPatternType().get(0) != cppPattern
				.getPatternType().get(0)) {
			return false;
		}

		if (javaPattern.getPieces().size() != cppPattern.getPieces().size()) {
			return false;
		}

		if (!new BooleanExpressionComparatorJC().compare(javaPattern.getPieceByType(PieceType.EXPRESSION),
				cppPattern.getPieceByType(PieceType.EXPRESSION))) {
			return false;
		}

		return true;
	}



}
