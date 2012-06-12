package codeconverter.javaJsComparator;

import java.util.List;

import codeconverter.CodePattern;
import codeconverter.ICodePiece;
import codeconverter.PatternType;
import codeconverter.PieceType;
import codeconverter.javaJsComparator.special.ArrayContentComparator;

public class ComparatorUtilities {

	public static int[][] findArrayDecalation(List<CodePattern> javaCodePatterns, int javaIndex, int jsIndex,
			CodePattern jsPattern, ICodePiece call, String vectorName, int compositeIndex) {
		for (int i = 0; i < javaCodePatterns.size(); i++) {
			if (javaCodePatterns.get(i).getPatternType().get(0) == PatternType.VARIABLE_DECLARATION
					&& javaCodePatterns.get(i).getPatternType().get(1) == PatternType.VARIABLE_ASSIGNMENT) {
				if (javaCodePatterns.get(i).getPieceByType(PieceType.VARIABLE).getPieceByType(PieceType.NAME)
						.toString().trim().equals(vectorName)) {
					if (javaCodePatterns.get(i).getPieceByType(PieceType.VARIABLE)
							.getPieceByType(PieceType.TYPE).toString().equals("float[]")
							&& call.getPieceByType(PieceType.COMPOSITE).toString().trim()
									.equals("BufferUtil.newFloatBuffer")) {
						if (new ArrayContentComparator().compare(
								javaCodePatterns.get(i + 1),
								jsPattern.getPieces().get(compositeIndex).getPieceByType(PieceType.COMPOSITE)
										.getPieceByType(PieceType.SEQUENCE)
										.getPieceByType(PieceType.ARRAY_CONTENT))) {
							return new int[][] { new int[] { javaIndex, i, i + 1 }, new int[] { jsIndex } };
						}
					}
				}
			}
			if (javaCodePatterns.get(i).getPatternType().get(0) == PatternType.VARIABLE_ASSIGNMENT) {
				if (javaCodePatterns.get(i).getPieceByType(PieceType.NAME).toString().trim()
						.equals(vectorName)) {
					if (javaCodePatterns.get(i).getPieceByType(PieceType.NEW_STATEMENT) != null) {
						if (javaCodePatterns.get(i).getPieceByType(PieceType.NEW_STATEMENT)
								.getPieceByType(PieceType.TYPE).getPieceByType(PieceType.TYPE).toString()
								.equals("float")
								&& call.getPieceByType(PieceType.COMPOSITE).toString().trim()
										.equals("BufferUtil.newFloatBuffer")) {
							if (new ArrayContentComparator().compare(
									javaCodePatterns.get(i + 1),
									jsPattern.getPieces().get(3).getPieceByType(PieceType.COMPOSITE)
											.getPieceByType(PieceType.SEQUENCE)
											.getPieceByType(PieceType.ARRAY_CONTENT))) {
								return new int[][] { new int[] { javaIndex, i, i + 1 }, new int[] { jsIndex } };
							}
						}
					}
				}
			}
		}
		return null;
	}
}
