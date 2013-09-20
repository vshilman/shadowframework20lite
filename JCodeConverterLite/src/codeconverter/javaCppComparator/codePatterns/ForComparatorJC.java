package codeconverter.javaCppComparator.codePatterns;

import java.util.List;

import codeconverter.CodePattern;
import codeconverter.ICodePiece;
import codeconverter.PatternType;
import codeconverter.PieceType;
import codeconverter.comparator.CodePatternComparator;
import codeconverter.javaCppComparator.codePieces.ExpressionComparatorJC;
import codeconverter.javaCppComparator.codePieces.NameComparatorJC;
import codeconverter.javaCppComparator.codePieces.VariableComparatorJC;
import codeconverter.javaJsComparator.codePieces.ExpressionComparator;
import codeconverter.javaJsComparator.codePieces.NameComparator;
import codeconverter.javaJsComparator.codePieces.VariableComparator;

public class ForComparatorJC implements CodePatternComparator{

	@Override
	public boolean compare(CodePattern javaPattern, CodePattern cppPattern) {

		if (javaPattern.getPatternType().get(0) != PatternType.FOR) {
			return false;
		}
		if (javaPattern.getPatternType().get(0) != cppPattern
				.getPatternType().get(0)) {
			return false;
		}


		ICodePiece javaPiece = javaPattern.getPieces().get(2);
		ICodePiece cppPiece = cppPattern.getPieces().get(2);

		if (javaPiece.getPieces().get(0).getPieceType() != cppPiece.getPieces().get(0).getPieceType()) {
			return false;
		}

		if (javaPiece.getPieces().get(0).getPieceType() == PieceType.VARIABLE) {
			if (!new VariableComparatorJC().compare(javaPiece.getPieceByType(PieceType.VARIABLE),
					cppPiece.getPieceByType(PieceType.VARIABLE))) {
				return false;
			}
		} else {
			if (!new NameComparatorJC().compare(javaPiece.getPieceByType(PieceType.NAME),
					cppPiece.getPieceByType(PieceType.NAME))) {
				return false;
			}
		}

		if (!new ExpressionComparatorJC().compare(javaPiece.getPieceByType(PieceType.EXPRESSION),
				cppPiece.getPieceByType(PieceType.EXPRESSION))) {
			return false;
		}

		if (!new NameComparatorJC().compare(javaPattern.getPieceByType(PieceType.VALUE),
				cppPattern.getPieceByType(PieceType.VALUE))) {
			return false;
		}

		if (!javaPattern.getPieces().get(5).toString().equals(cppPattern.getPieces().get(5).toString())) {
			return false;
		}

		if (!new ExpressionComparatorJC().compare(javaPattern.getPieceByType(PieceType.EXPRESSION),
				cppPattern.getPieceByType(PieceType.EXPRESSION))) {
			return false;
		}

		List<ICodePiece> javaCompList = javaPattern.getPieces().get(8).getPieces();
		List<ICodePiece> cppCompList = cppPattern.getPieces().get(8).getPieces();

		if (javaCompList.size() != cppCompList.size()) {
			return false;
		}

		for (int i = 0; i < javaCompList.size(); i++) {
			if (javaCompList.get(i).getPieceType() == PieceType.COMPOSITE) {
				if (!new NameComparatorJC().compare(javaCompList.get(i).getPieceByType(PieceType.VARIABLE),
						cppCompList.get(i).getPieceByType(PieceType.VARIABLE))) {
					return false;
				}
				if (!javaCompList.get(i).getPieceByType(PieceType.KEYWORD).toString()
						.equals(cppCompList.get(i).getPieceByType(PieceType.KEYWORD).toString())) {
					return false;
				}
				ICodePiece a = javaCompList.get(i).getPieceByType(PieceType.EXPRESSION);
				ICodePiece b = cppCompList.get(i).getPieceByType(PieceType.EXPRESSION);
				if (a == null && b != null || a != null && b == null) {
					return false;
				}
				if (a != null && b != null) {
					if (!new ExpressionComparatorJC().compare(
							javaCompList.get(i).getPieceByType(PieceType.EXPRESSION), cppCompList.get(i)
									.getPieceByType(PieceType.EXPRESSION))) {
						return false;
					}
				}
			}
		}

		return true;
	}



}
