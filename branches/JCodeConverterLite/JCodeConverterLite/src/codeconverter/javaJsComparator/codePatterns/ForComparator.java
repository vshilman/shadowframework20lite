package codeconverter.javaJsComparator.codePatterns;

import java.util.List;

import codeconverter.CodePattern;
import codeconverter.ICodePiece;
import codeconverter.PatternType;
import codeconverter.PieceType;
import codeconverter.javaJsComparator.CodePatternComparator;
import codeconverter.javaJsComparator.codePieces.ExpressionComparator;
import codeconverter.javaJsComparator.codePieces.NameComparator;
import codeconverter.javaJsComparator.codePieces.VariableComparator;

public class ForComparator implements CodePatternComparator {

	@Override
	public int[][] compare(List<CodePattern> javaCodePatterns, int javaIndex, List<CodePattern> jsCodePatterns,
			int jsIndex) {
		if (javaCodePatterns.get(javaIndex).getPatternType().get(0) != PatternType.FOR) {
			return null;
		}
		if (javaCodePatterns.get(javaIndex).getPatternType().get(0) != jsCodePatterns.get(jsIndex)
				.getPatternType().get(0)) {
			return null;
		}
		CodePattern javaPattern = javaCodePatterns.get(javaIndex);
		CodePattern jsPattern = jsCodePatterns.get(jsIndex);

		ICodePiece javaPiece = javaPattern.getPieces().get(2);
		ICodePiece jsPiece = jsPattern.getPieces().get(2);

		if (javaPiece.getPieces().get(0).getPieceType() != jsPiece.getPieces().get(0).getPieceType()) {
			return null;
		}

		if (javaPiece.getPieces().get(0).getPieceType() == PieceType.VARIABLE) {
			if (!new VariableComparator().compare(javaPiece.getPieceByType(PieceType.VARIABLE),
					jsPiece.getPieceByType(PieceType.VARIABLE))) {
				return null;
			}
		} else {
			if (!new NameComparator().compare(javaPiece.getPieceByType(PieceType.NAME),
					jsPiece.getPieceByType(PieceType.NAME))) {
				return null;
			}
		}

		if (!new ExpressionComparator().compare(javaPiece.getPieceByType(PieceType.EXPRESSION),
				jsPiece.getPieceByType(PieceType.EXPRESSION))) {
			return null;
		}

		if (!new NameComparator().compare(javaPattern.getPieceByType(PieceType.VALUE),
				jsPattern.getPieceByType(PieceType.VALUE))) {
			return null;
		}

		if (!javaPattern.getPieces().get(5).toString().equals(jsPattern.getPieces().get(5).toString())) {
			return null;
		}

		if (!new ExpressionComparator().compare(javaPattern.getPieceByType(PieceType.EXPRESSION),
				jsPattern.getPieceByType(PieceType.EXPRESSION))) {
			return null;
		}

		List<ICodePiece> javaCompList = javaPattern.getPieces().get(8).getPieces();
		List<ICodePiece> jsCompList = jsPattern.getPieces().get(8).getPieces();

		if (javaCompList.size() != jsCompList.size()) {
			return null;
		}

		for (int i = 0; i < javaCompList.size(); i++) {
			if (javaCompList.get(i).getPieceType() == PieceType.COMPOSITE) {
				if (!new NameComparator().compare(javaCompList.get(i).getPieceByType(PieceType.VARIABLE),
						jsCompList.get(i).getPieceByType(PieceType.VARIABLE))) {
					return null;
				}
				if (!javaCompList.get(i).getPieceByType(PieceType.KEYWORD).toString()
						.equals(jsCompList.get(i).getPieceByType(PieceType.KEYWORD).toString())) {
					return null;
				}
				ICodePiece a = javaCompList.get(i).getPieceByType(PieceType.EXPRESSION);
				ICodePiece b = jsCompList.get(i).getPieceByType(PieceType.EXPRESSION);
				if (a == null && b != null || a != null && b == null) {
					return null;
				}
				if (a != null && b != null) {
					if (!new ExpressionComparator().compare(
							javaCompList.get(i).getPieceByType(PieceType.EXPRESSION), jsCompList.get(i)
									.getPieceByType(PieceType.EXPRESSION))) {
						return null;
					}
				}
			}
		}

		return new int[][] { new int[] { javaIndex }, new int[] { jsIndex } };
	}

}
