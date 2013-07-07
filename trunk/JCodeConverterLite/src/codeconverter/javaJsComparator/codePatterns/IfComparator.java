package codeconverter.javaJsComparator.codePatterns;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.PieceType;
import codeconverter.javaJsComparator.CodePatternComparator;
import codeconverter.javaJsComparator.codePieces.BooleanExpressionComparator;

public class IfComparator implements CodePatternComparator {

	@Override
	public boolean compare(CodePattern javaPattern, CodePattern jsPattern) {
	
//	public int[][] compare(List<CodePattern> javaCodePatterns, int javaIndex, List<CodePattern> jsCodePatterns,
//			int jsIndex) {
		if (javaPattern.getPatternType().get(0) != PatternType.IF) {
			return false;
		}
		if (javaPattern.getPatternType().get(0) != jsPattern
				.getPatternType().get(0)) {
			return false;
		}
		
		if (javaPattern.getPieces().size() != jsPattern.getPieces().size()) {
			return false;
		}

		if (!new BooleanExpressionComparator().compare(javaPattern.getPieceByType(PieceType.EXPRESSION),
				jsPattern.getPieceByType(PieceType.EXPRESSION))) {
			return false;
		}

		return true;
	}

}
