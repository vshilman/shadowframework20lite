package codeconverter.javaJsComparator.codePatterns;

import java.util.List;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.PieceType;
import codeconverter.javaJsComparator.CodePatternComparator;

public class OpenGlGenTexturesMethodComparator implements CodePatternComparator {

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
		if(jsPattern.getPieceByType(PieceType.EXPRESSION).getPieceByType(PieceType.OPENGL_CALL)==null){
			return null;
		}
		
		if (!javaPattern.getPieceByType(PieceType.OPENGL_CALL).getPieces().get(3).toString()
				.equals("GenTextures")
				|| !jsPattern.getPieceByType(PieceType.EXPRESSION).getPieceByType(PieceType.OPENGL_CALL)
						.getPieces().get(1).toString().equals("createTexture")) {
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
			CodePattern jsFor = jsCodePatterns.get(jsIndex-1);
			if(javaName.equals(jsFor.getPieceByType(PieceType.EXPRESSION).toString())){
				jsName = jsPattern.getPieceByType(PieceType.NAME).getPieces().get(1).getPieces().get(1).toString()
				.trim();
				if(jsName.equals(jsFor.getPieceByType(PieceType.VALUE).toString().trim())){
					return new int[][] { new int[] { javaIndex }, new int[] { jsIndex,jsIndex-1 } };
				}
			}
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
