package codeconverter.javaJsComparator.codePatterns;

import java.util.List;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.PieceType;
import codeconverter.javaJsComparator.CodePatternComparator;
import codeconverter.javaJsComparator.codePieces.MethodVariablesComparator;

public class MethodDeclarationComparator implements CodePatternComparator {

	@Override
	public int[] compare(List<CodePattern> javaCodePatterns, int javaIndex, List<CodePattern> jsCodePatterns,
			int jsIndex) {
		if (javaCodePatterns.get(javaIndex).getPatternType().get(0) != PatternType.METHOD_DECLARATION) {
			return null;
		}
		if (javaCodePatterns.get(javaIndex).getPatternType().get(0) != jsCodePatterns.get(jsIndex)
				.getPatternType().get(0)) {
			return null;
		}
		CodePattern javaPattern = javaCodePatterns.get(javaIndex);
		CodePattern jsPattern = jsCodePatterns.get(jsIndex);

		if (!javaPattern.getPieces().get(2).getPieces().get(1).toString()
				.equals(jsPattern.getPieces().get(1).toString())) {
			return null;
		}
		if (!new MethodVariablesComparator().compare(javaPattern.getPieceByType(PieceType.METHOD_VARIABLES),
				jsPattern.getPieceByType(PieceType.METHOD_VARIABLES))) {
			return null;
		}

		return new int[] { javaIndex + 1, jsIndex + 1 };
	}

}
