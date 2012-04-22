package codeconverter.javaJsComparator.codePatterns;

import java.util.List;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.PieceType;
import codeconverter.javaJsComparator.CodePatternComparator;

public class OpenGlGenBuffersMethodComparator implements CodePatternComparator {

	@Override
	public int[][] compare(List<CodePattern> javaCodePatterns, int javaIndex,
			List<CodePattern> jsCodePatterns, int jsIndex) {
		if (javaCodePatterns.get(javaIndex).getPatternType().get(0) != PatternType.OPENGL_CALL) {
			return null;
		}
		if (jsCodePatterns.get(jsIndex).getPatternType().get(0) != PatternType.VARIABLE_ASSIGNMENT) {
			return null;
		}
		CodePattern javaPattern = javaCodePatterns.get(javaIndex);
		CodePattern jsPattern = jsCodePatterns.get(jsIndex);

		if (jsPattern.getPieceByType(PieceType.EXPRESSION) == null) {
			return null;
		}
		if (!javaPattern.getPieceByType(PieceType.OPENGL_CALL).getPieces().get(3).toString()
				.equals("GenBuffers")
				|| !jsPattern.getPieceByType(PieceType.EXPRESSION).getPieceByType(PieceType.OPENGL_CALL)
						.getPieces().get(1).toString().equals("createBuffer")) {
			return null;
		}

		String javaName = javaPattern.getPieceByType(PieceType.OPENGL_CALL).getPieces().get(5).getPieces()
				.get(2).toString().trim();
		String jsName = jsPattern.getPieceByType(PieceType.NAME).getPieces().get(0).toString().trim();
		if (!javaName.equals(jsName)) {
			return null;
		}

		javaName = javaPattern.getPieceByType(PieceType.OPENGL_CALL).getPieces().get(5).getPieces().get(0)
				.toString().trim();
		if (!javaName.equals("1")) {
			return null;
		}

		javaName = javaPattern.getPieceByType(PieceType.OPENGL_CALL).getPieces().get(5).getPieces().get(4)
				.toString().trim();
		jsName = jsPattern.getPieceByType(PieceType.NAME).getPieces().get(1).getPieces().get(1).toString()
				.trim();
		if (!javaName.equals(jsName)) {
			return null;
		}

		return new int[][] { new int[] { javaIndex }, new int[] { jsIndex } };
	}

}
