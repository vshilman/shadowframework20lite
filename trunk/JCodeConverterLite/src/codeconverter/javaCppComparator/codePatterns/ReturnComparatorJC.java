package codeconverter.javaCppComparator.codePatterns;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.PieceType;
import codeconverter.comparator.CodePatternComparator;
import codeconverter.javaCppComparator.codePieces.ExpressionComparatorJC;
import codeconverter.javaCppComparator.codePieces.MethodComparatorJC;
import codeconverter.javaJsComparator.codePieces.ExpressionComparator;
import codeconverter.javaJsComparator.codePieces.MethodComparator;

public class ReturnComparatorJC implements CodePatternComparator{

	@Override
	public boolean compare(CodePattern javaPattern, CodePattern cppPattern) {

		if (javaPattern.getPatternType().get(0) != PatternType.RETURN) {
			return false;
		}
		if (javaPattern.getPatternType().get(0) != cppPattern
				.getPatternType().get(0)) {
			return false;
		}

		if(javaPattern.getPieces().get(2).getPieceType()!=cppPattern.getPieces().get(2).getPieceType()){
			return false;
		}

		if (javaPattern.getPieces().get(2).getPieceType() == PieceType.EXPRESSION) {
			if (!new ExpressionComparatorJC().compare(javaPattern.getPieceByType(PieceType.EXPRESSION),
					cppPattern.getPieceByType(PieceType.EXPRESSION))) {
				return false;
			}
		}

		if (javaPattern.getPieces().get(2).getPieceType() == PieceType.CALL) {
			if (!new MethodComparatorJC().compare(javaPattern.getPieceByType(PieceType.CALL),
					cppPattern.getPieceByType(PieceType.CALL))) {
				return false;
			}
		}


		return true;
	}



}
