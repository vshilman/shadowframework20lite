package codeconverter.javaJsComparator.codePatterns;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.PieceType;
import codeconverter.comparator.CodePatternComparator;
import codeconverter.javaJsComparator.codePieces.ExpressionComparator;
import codeconverter.javaJsComparator.codePieces.NameComparator;
import codeconverter.javaJsComparator.codePieces.NewStatementComparator;

public class AttributeAssignmentComparator implements CodePatternComparator {

	@Override
	public boolean compare(CodePattern javaPattern, CodePattern jsPattern) {
//	public int[][] compare(List<CodePattern> javaCodePatterns, int javaIndex,
//			List<CodePattern> jsCodePatterns, int jsIndex) {
		if (javaPattern.getPatternType().get(0) != PatternType.ATTRIBUTE_ASSIGNMENT) {
			return false;
		}
		if (javaPattern.getPatternType().get(0) != jsPattern
				.getPatternType().get(0)) {
			return false;
		}

		if (!new NameComparator().compare(javaPattern.getPieceByType(PieceType.NAME),
				jsPattern.getPieceByType(PieceType.NAME))) {
			return false;
		}

		if (javaPattern.getPieces().get(3).getPieceType() != jsPattern.getPieces().get(3).getPieceType()) {
			return false;
		}

		if (javaPattern.getPieces().get(3).getPieceType() == PieceType.EXPRESSION) {
			if (!new ExpressionComparator().compare(javaPattern.getPieceByType(PieceType.EXPRESSION),
					jsPattern.getPieceByType(PieceType.EXPRESSION))) {
				return false;
			}
		}

		if (javaPattern.getPieces().get(3).getPieceType() == PieceType.NEW_STATEMENT) {
			if (!new NewStatementComparator().compare(javaPattern.getPieceByType(PieceType.NEW_STATEMENT),
					jsPattern.getPieceByType(PieceType.NEW_STATEMENT))) {
				return false;
			}
		}

		return true;
	}

}
