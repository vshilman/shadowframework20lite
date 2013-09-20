package codeconverter.javaCppComparator.codePatterns;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.PieceType;
import codeconverter.comparator.CodePatternComparator;
import codeconverter.javaCppComparator.codePieces.ArrayContentComparatorJC;
import codeconverter.javaCppComparator.codePieces.ArrayDeclarationCompartorJC;
import codeconverter.javaCppComparator.codePieces.ExpressionComparatorJC;
import codeconverter.javaCppComparator.codePieces.MethodComparatorJC;
import codeconverter.javaCppComparator.codePieces.NewStatementComparatorJC;
import codeconverter.javaCppComparator.codePieces.VariableComparatorJC;
import codeconverter.javaJsComparator.codePieces.ArrayContentComparator;
import codeconverter.javaJsComparator.codePieces.ArrayDeclarationComparator;
import codeconverter.javaJsComparator.codePieces.ExpressionComparator;
import codeconverter.javaJsComparator.codePieces.MethodComparator;
import codeconverter.javaJsComparator.codePieces.NewStatementComparator;
import codeconverter.javaJsComparator.codePieces.OpenGlMethodComparator;
import codeconverter.javaJsComparator.codePieces.VariableComparator;

public class VariableDeclarationAndAssignmentComparatorJC implements CodePatternComparator{

	@Override
	public boolean compare(CodePattern javaPattern, CodePattern cppPattern) {

		if (javaPattern.getPatternType().get(0) != PatternType.VARIABLE_DECLARATION
				|| javaPattern.getPatternType().get(1) != PatternType.VARIABLE_ASSIGNMENT) {
			return false;
		}
		if (javaPattern.getPatternType().get(0) != cppPattern
				.getPatternType().get(0)
				|| javaPattern.getPatternType().get(1) != cppPattern
						.getPatternType().get(1)) {
			return false;
		}

		if (!new VariableComparatorJC().compare(javaPattern.getPieceByType(PieceType.VARIABLE),
				cppPattern.getPieceByType(PieceType.VARIABLE))) {
			return false;
		}

		if (javaPattern.getPieces().size() > 2 && cppPattern.getPieces().size()>2) {

			if(javaPattern.getPieces().get(2).getPieceType()!=cppPattern.getPieces().get(2).getPieceType()){
				return false;
			}

			if (javaPattern.getPieces().get(2).getPieceType() == PieceType.EXPRESSION) {
				if (!new ExpressionComparatorJC().compare(javaPattern.getPieceByType(PieceType.EXPRESSION),
						cppPattern.getPieceByType(PieceType.EXPRESSION))) {
					return false;
				}
			}


			if (javaPattern.getPieces().get(2).getPieceType() == PieceType.CALL) {
				if (!new MethodComparatorJC().compare(javaPattern.getPieceByType(PieceType.CALL),
						cppPattern.getPieceByType(PieceType.CALL))) {
					return false;
				}
			}

			if (javaPattern.getPieces().get(2).getPieceType() == PieceType.ARRAY_CONTENT) {
				if (!new ArrayContentComparatorJC().compare(javaPattern.getPieceByType(PieceType.ARRAY_CONTENT),
					  cppPattern.getPieceByType(PieceType.ARRAY_CONTENT))) {
					return false;
				}
			}


			if (javaPattern.getPieces().get(2).getPieceType() == PieceType.ARRAY_DECLARATION) {
				if (!new ArrayDeclarationCompartorJC().compare(javaPattern.getPieceByType(PieceType.ARRAY_DECLARATION),
						cppPattern.getPieceByType(PieceType.ARRAY_DECLARATION))) {
					return false;
				}
			}

			if (javaPattern.getPieces().get(2).getPieceType() == PieceType.NEW_STATEMENT) {
				if (!new NewStatementComparatorJC().compare(
						javaPattern.getPieceByType(PieceType.NEW_STATEMENT),
						cppPattern.getPieceByType(PieceType.NEW_STATEMENT))) {
					return false;
				}
			}

		} else {
			return false;
		}

		return true;
	}



}
