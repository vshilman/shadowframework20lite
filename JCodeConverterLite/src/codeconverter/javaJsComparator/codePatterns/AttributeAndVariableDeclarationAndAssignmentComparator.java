package codeconverter.javaJsComparator.codePatterns;

import java.util.List;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.PieceType;
import codeconverter.javaJsComparator.CodePatternComparator;
import codeconverter.javaJsComparator.codePieces.VariableComparator;

public class AttributeAndVariableDeclarationAndAssignmentComparator implements CodePatternComparator {

	@Override
	public int[][] compare(List<CodePattern> javaCodePatterns, int javaIndex,
			List<CodePattern> jsCodePatterns, int jsIndex) {
		if (javaCodePatterns.get(javaIndex).getPatternType().size() < 2) {
			return null;
		}
		if (javaCodePatterns.get(javaIndex).getPatternType().get(0) != PatternType.ATTRIBUTE_DECLARATION
				|| javaCodePatterns.get(javaIndex).getPatternType().get(1) != PatternType.ATTRIBUTE_ASSIGNMENT) {
			return null;
		}
		if (jsCodePatterns.get(jsIndex).getPatternType().get(0) != PatternType.VARIABLE_DECLARATION
				|| jsCodePatterns.get(jsIndex).getPatternType().get(1) != PatternType.VARIABLE_ASSIGNMENT) {
			return null;
		}
		CodePattern javaPattern = javaCodePatterns.get(javaIndex);
		CodePattern jsPattern = jsCodePatterns.get(jsIndex);

		if (!new VariableComparator().compare(javaPattern.getPieceByType(PieceType.VARIABLE),
				jsPattern.getPieceByType(PieceType.VARIABLE))) {
			return null;
		}

		return new int[][] { new int[] { javaIndex }, new int[] { jsIndex } };
	}

}
