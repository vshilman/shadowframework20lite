package codeconverter.javaJsComparator.codePatterns;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.PieceType;
import codeconverter.javaJsComparator.CodePatternComparator;
import codeconverter.javaJsComparator.codePieces.ExpressionComparator;
import codeconverter.javaJsComparator.codePieces.MethodComparator;
import codeconverter.javaJsComparator.codePieces.OpenGlMethodComparator;

public class ReturnComparator implements CodePatternComparator {

	@Override
	public boolean compare(CodePattern javaPattern, CodePattern jsPattern) {
//	public int[][] compare(List<CodePattern> javaCodePatterns, int javaIndex, List<CodePattern> jsCodePatterns,
//			int jsIndex) {
		if (javaPattern.getPatternType().get(0) != PatternType.RETURN) {
			return false;
		}
		if (javaPattern.getPatternType().get(0) != jsPattern
				.getPatternType().get(0)) {
			return false;
		}

		if (javaPattern.getPieces().get(2).getPieceType() != jsPattern.getPieces().get(2).getPieceType()) {
			return false;
		}

		if (javaPattern.getPieces().get(2).getPieceType() == PieceType.EXPRESSION) {
			if (!new ExpressionComparator().compare(javaPattern.getPieceByType(PieceType.EXPRESSION),
					jsPattern.getPieceByType(PieceType.EXPRESSION))) {
				return false;
			}
		}

		if (javaPattern.getPieces().get(2).getPieceType() == PieceType.CALL) {
			if (!new MethodComparator().compare(javaPattern.getPieceByType(PieceType.CALL),
					jsPattern.getPieceByType(PieceType.CALL))) {
				return false;
			}
		}

		if (javaPattern.getPieces().get(2).getPieceType() == PieceType.OPENGL_CALL) {
			if (!new OpenGlMethodComparator().compare(javaPattern.getPieceByType(PieceType.OPENGL_CALL),
					jsPattern.getPieceByType(PieceType.OPENGL_CALL))) {
				return false;
			}
		}

		return true;
	}

}
