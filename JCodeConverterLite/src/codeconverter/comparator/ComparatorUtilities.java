package codeconverter.comparator;

import java.util.List;

import codeconverter.CodePattern;
import codeconverter.ICodePiece;
import codeconverter.PatternType;
import codeconverter.PieceType;
import codeconverter.javaJsComparator.codePieces.ArrayDeclarationComparator;

public class ComparatorUtilities {

	public static int[][] findArrayDecalation(List<CodePattern> javaCodePatterns, int javaIndex, int jsIndex,
			CodePattern jsPattern, ICodePiece call, String vectorName) {
		for (int i = javaIndex - 1; i >= 0; i--) {
			if ((javaCodePatterns.get(i).getPatternType().get(0) == PatternType.VARIABLE_DECLARATION && javaCodePatterns
					.get(i).getPatternType().get(1) == PatternType.VARIABLE_ASSIGNMENT)
					|| (javaCodePatterns.get(i).getPatternType().get(0) == PatternType.VARIABLE_ASSIGNMENT)) {
				if (javaCodePatterns.get(i).getPieceByType(PieceType.NAME).toString().trim()
						.equals(vectorName)) {
					if (javaCodePatterns.get(i).getPieceByType(PieceType.ARRAY_DECLARATION)
							.getPieceByType(PieceType.TYPE).getPieceByType(PieceType.TYPE).toString()
							.equals("float")
							&& call.getPieceByType(PieceType.NAME).toString()
									.equals("BufferUtil.newFloatBuffer")) {
						if (new ArrayDeclarationComparator().compare(
								javaCodePatterns.get(i).getPieceByType(PieceType.ARRAY_DECLARATION),
								jsPattern.getPieceByType(PieceType.ARRAY_DECLARATION))) {
							return new int[][] { new int[] { javaIndex, i }, new int[] { jsIndex } };
						}
					}
				}
			}
		}
		return null;
	}
}
