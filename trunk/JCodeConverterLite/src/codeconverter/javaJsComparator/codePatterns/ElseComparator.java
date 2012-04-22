package codeconverter.javaJsComparator.codePatterns;

import java.util.List;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.PieceType;
import codeconverter.javaJsComparator.CodePatternComparator;
import codeconverter.javaJsComparator.codePieces.BooleanExpressionComparator;

public class ElseComparator implements CodePatternComparator {

	@Override
	public int[][] compare(List<CodePattern> javaCodePatterns, int javaIndex, List<CodePattern> jsCodePatterns,
			int jsIndex) {
		if (javaCodePatterns.get(javaIndex).getPatternType().get(0) != PatternType.ELSE) {
			return null;
		}
		if (javaCodePatterns.get(javaIndex).getPatternType().get(0) != jsCodePatterns.get(jsIndex)
				.getPatternType().get(0)) {
			return null;
		}
		CodePattern javaPattern = javaCodePatterns.get(javaIndex);
		CodePattern jsPattern = jsCodePatterns.get(jsIndex);

		if (javaPattern.getPieces().size() != jsPattern.getPieces().size()) {
			return null;
		}

		if (javaPattern.getPieces().size() > 2) {
			if (javaPattern.getPieces().get(2).getPieceType() != jsPattern.getPieces().get(2).getPieceType()) {
				return null;
			}
			if (javaPattern.getPieces().get(2).getPieceType() != PieceType.IGNORED) {
				if (!new BooleanExpressionComparator().compare(
						javaPattern.getPieces().get(2).getPieceByType(PieceType.EXPRESSION), jsPattern
								.getPieces().get(2).getPieceByType(PieceType.EXPRESSION))) {
					return null;
				}
			}
		}

		return new int[][] { new int[] { javaIndex }, new int[] { jsIndex } };
	}

}
