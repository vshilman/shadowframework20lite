package codeconverter.javaJsComparator.codePatterns;

import java.util.List;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.PieceType;
import codeconverter.javaJsComparator.CodePatternComparator;
import codeconverter.javaJsComparator.codePieces.BooleanExpressionComparator;

public class IfComparator implements CodePatternComparator {

	@Override
	public int[] compare(List<CodePattern> javaCodePatterns, int javaIndex, List<CodePattern> jsCodePatterns,
			int jsIndex) {
		if (javaCodePatterns.get(javaIndex).getPatternType().get(0) != PatternType.IF) {
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

		if (!new BooleanExpressionComparator().compare(javaPattern.getPieceByType(PieceType.EXPRESSION),
				jsPattern.getPieceByType(PieceType.EXPRESSION))) {
			return null;
		}

		return new int[] { javaIndex + 1, jsIndex + 1 };
	}

}
