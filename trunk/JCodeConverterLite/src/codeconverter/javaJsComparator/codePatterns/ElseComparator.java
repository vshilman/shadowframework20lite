package codeconverter.javaJsComparator.codePatterns;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.PieceType;
import codeconverter.comparator.CodePatternComparator;
import codeconverter.javaJsComparator.codePieces.BooleanExpressionComparator;

public class ElseComparator implements CodePatternComparator {

	@Override
	public boolean compare(CodePattern javaPattern, CodePattern jsPattern) {
//	public int[][] compare(List<CodePattern> javaCodePatterns, int javaIndex, List<CodePattern> jsCodePatterns,
//			int jsIndex) {
		if (javaPattern.getPatternType().get(0) != PatternType.ELSE) {
			return false;
		}
		if (javaPattern.getPatternType().get(0) != jsPattern
				.getPatternType().get(0)) {
			return false;
		}
	
		if (javaPattern.getPieces().size() != jsPattern.getPieces().size()) {
			return false;
		}

		if (javaPattern.getPieces().size() > 2) {
			if (javaPattern.getPieces().get(2).getPieceType() != jsPattern.getPieces().get(2).getPieceType()) {
				return false;
			}
			if (javaPattern.getPieces().get(2).getPieceType() != PieceType.IGNORED) {
				if (!new BooleanExpressionComparator().compare(
						javaPattern.getPieces().get(2).getPieceByType(PieceType.EXPRESSION), jsPattern
								.getPieces().get(2).getPieceByType(PieceType.EXPRESSION))) {
					return false;
				}
			}
		}

		return true;
	}

}
