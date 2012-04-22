package codeconverter.javaJsComparator.codePatterns;

import java.util.List;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.PieceType;
import codeconverter.javaJsComparator.CodePatternComparator;
import codeconverter.javaJsComparator.codePieces.OpenGlMethodComparator;

public class OpenGlMethodAccessComparator implements CodePatternComparator {

	@Override
	public int[][] compare(List<CodePattern> javaCodePatterns, int javaIndex, List<CodePattern> jsCodePatterns,
			int jsIndex) {
		if (javaCodePatterns.get(javaIndex).getPatternType().get(0) != PatternType.OPENGL_CALL) {
			return null;
		}
		if (javaCodePatterns.get(javaIndex).getPatternType().get(0) != jsCodePatterns.get(jsIndex)
				.getPatternType().get(0)) {
			return null;
		}
		CodePattern javaPattern = javaCodePatterns.get(javaIndex);
		CodePattern jsPattern = jsCodePatterns.get(jsIndex);

		if (!new OpenGlMethodComparator().compare(javaPattern.getPieceByType(PieceType.OPENGL_CALL),
				jsPattern.getPieceByType(PieceType.OPENGL_CALL))) {
			return null;
		}

		return new int[][] { new int[] { javaIndex }, new int[] { jsIndex } };
	}

}
