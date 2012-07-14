package codeconverter.javaJsComparator.codePatterns;

import java.util.List;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.PieceType;
import codeconverter.javaJsComparator.CodePatternComparator;
import codeconverter.javaJsComparator.codePieces.ArrayContentComparator;
import codeconverter.javaJsComparator.codePieces.ExpressionComparator;
import codeconverter.javaJsComparator.codePieces.MethodComparator;
import codeconverter.javaJsComparator.codePieces.NewStatementComparator;
import codeconverter.javaJsComparator.codePieces.OpenGlMethodComparator;
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

		if (javaPattern.getPieces().size() > 3) {

			if (javaPattern.getPieces().get(3).getPieceType() == PieceType.EXPRESSION) {
				if (!new ExpressionComparator().compare(javaPattern.getPieceByType(PieceType.EXPRESSION),
						jsPattern.getPieceByType(PieceType.EXPRESSION))) {
					return null;
				}
			}

			if (javaPattern.getPieces().get(3).getPieceType() == PieceType.CALL) {
				if (!new MethodComparator().compare(javaPattern.getPieceByType(PieceType.CALL),
						jsPattern.getPieceByType(PieceType.CALL))) {
					return null;
				}
			}

			if (javaPattern.getPieces().get(3).getPieceType() == PieceType.OPENGL_CALL) {
				if (!new OpenGlMethodComparator().compare(javaPattern.getPieceByType(PieceType.OPENGL_CALL),
						jsPattern.getPieceByType(PieceType.OPENGL_CALL))) {
					return null;
				}
			}

			if (javaPattern.getPieces().get(3).getPieceType() == PieceType.ARRAY_CONTENT) {
				if (!new ArrayContentComparator().compare(
						javaPattern.getPieceByType(PieceType.ARRAY_CONTENT),
						jsPattern.getPieceByType(PieceType.ARRAY_CONTENT))) {
					return null;
				}
			}

			if (javaPattern.getPieces().get(3).getPieceType() == PieceType.NEW_STATEMENT) {
				if (!new NewStatementComparator().compare(
						javaPattern.getPieceByType(PieceType.NEW_STATEMENT),
						jsPattern.getPieceByType(PieceType.NEW_STATEMENT))) {
					return null;
				}
			}
		}

		return new int[][] { new int[] { javaIndex }, new int[] { jsIndex } };
	}

}
