package codeconverter.javaCppComparator.codePatterns;

import codeconverter.CodePattern;
import codeconverter.ICodePiece;
import codeconverter.PatternType;
import codeconverter.PieceType;
import codeconverter.comparator.CodePatternComparator;
import codeconverter.javaCppComparator.codePieces.ArrayContentComparatorJC;
import codeconverter.javaCppComparator.codePieces.ArrayDeclarationCompartorJC;
import codeconverter.javaCppComparator.codePieces.ExpressionComparatorJC;
import codeconverter.javaCppComparator.codePieces.MethodComparatorJC;
import codeconverter.javaCppComparator.codePieces.NameComparatorJC;
import codeconverter.javaCppComparator.codePieces.NewStatementComparatorJC;
import codeconverter.javaJsComparator.codePieces.ArrayContentComparator;
import codeconverter.javaJsComparator.codePieces.ArrayDeclarationComparator;
import codeconverter.javaJsComparator.codePieces.ExpressionComparator;
import codeconverter.javaJsComparator.codePieces.MethodComparator;
import codeconverter.javaJsComparator.codePieces.NameComparator;
import codeconverter.javaJsComparator.codePieces.NewStatementComparator;
import codeconverter.javaJsComparator.codePieces.OpenGlMethodComparator;

public class VariableAssignmentComparatorJC implements CodePatternComparator{

	@Override
	public boolean compare(CodePattern javaPattern, CodePattern cppPattern) {
		if (javaPattern.getPatternType().get(0) != PatternType.VARIABLE_ASSIGNMENT) {
			return false;
		}
		if (javaPattern.getPatternType().get(0) != cppPattern
				.getPatternType().get(0)) {
			return false;
		}

		if (!new NameComparatorJC().compare(javaPattern.getPieceByType(PieceType.NAME),
				cppPattern.getPieceByType(PieceType.NAME))) {
			return false;
		}

		if (javaPattern.getPieces().get(1).getPieceType() != cppPattern.getPieces().get(1).getPieceType()) {
			return false;
		}

		if (javaPattern.getPieces().get(1).getPieceType() == PieceType.KEYWORD) {
			if (!javaPattern.getPieces().get(1).toString().equals(cppPattern.getPieces().get(1).toString())) {
				return false;
			}
		}


		if (javaPattern.getPieces().get(3).getPieceType() != cppPattern.getPieces().get(3).getPieceType()) {
			return false;
		}

		if (javaPattern.getPieces().get(3).getPieceType() == PieceType.EXPRESSION) {
			if (!new ExpressionComparatorJC().compare(javaPattern.getPieceByType(PieceType.EXPRESSION),
					cppPattern.getPieceByType(PieceType.EXPRESSION))) {
				return false;
			}
		}

		if (javaPattern.getPieces().get(3).getPieceType() == PieceType.CALL) {
			if (!new MethodComparatorJC().compare(javaPattern.getPieceByType(PieceType.CALL),
					cppPattern.getPieceByType(PieceType.CALL))) {
				return false;
			}
		}


		if (javaPattern.getPieces().get(3).getPieceType() == PieceType.ARRAY_CONTENT) {
			if (!new ArrayContentComparatorJC().compare(javaPattern.getPieceByType(PieceType.ARRAY_CONTENT),
					cppPattern.getPieceByType(PieceType.ARRAY_CONTENT))) {
				return false;
			}
		}

		if (javaPattern.getPieces().get(2).getPieceType() == PieceType.ARRAY_DECLARATION) {
			if (!new ArrayDeclarationCompartorJC().compare(
					javaPattern.getPieceByType(PieceType.ARRAY_DECLARATION),
					cppPattern.getPieceByType(PieceType.ARRAY_DECLARATION))) {
				return false;
			}
		}

		if (javaPattern.getPieces().get(3).getPieceType() == PieceType.NEW_STATEMENT) {
			if (!new NewStatementComparatorJC().compare(javaPattern.getPieceByType(PieceType.NEW_STATEMENT),
					cppPattern.getPieceByType(PieceType.NEW_STATEMENT))) {
				return false;
			}
		}

		return true;
	}
}

