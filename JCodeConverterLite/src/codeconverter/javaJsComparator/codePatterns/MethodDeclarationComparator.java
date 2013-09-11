package codeconverter.javaJsComparator.codePatterns;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.PieceType;
import codeconverter.comparator.CodePatternComparator;
import codeconverter.javaJsComparator.codePieces.MethodVariablesComparator;

public class MethodDeclarationComparator implements CodePatternComparator {

	@Override
	public boolean compare(CodePattern javaPattern, CodePattern jsPattern) {
//	public int[][] compare(List<CodePattern> javaCodePatterns, int javaIndex,
//			List<CodePattern> jsCodePatterns, int jsIndex) {
		if (javaPattern.getPatternType().get(0) != PatternType.METHOD_DECLARATION) {
			return false;
		}
		if (javaPattern.getPatternType().get(0) != jsPattern
				.getPatternType().get(0)) {
			return false;
		}

		if (!javaPattern.getPieces().get(2).getPieces().get(1).toString().trim()
				.equals(jsPattern.getPieces().get(1).toString().trim())) {
			return false;
		}
		if (!new MethodVariablesComparator().compare(javaPattern.getPieceByType(PieceType.METHOD_VARIABLES),
				jsPattern.getPieceByType(PieceType.METHOD_VARIABLES))) {
			return false;
		}

		return true;
	}

}
