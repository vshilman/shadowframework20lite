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
		//System.out.println("Computo");
		if (javaCodePatterns.get(javaIndex).getPatternType().get(0) != PatternType.FOR) {
			//System.out.println("fallito1");
			return null;
		}
		if (javaCodePatterns.get(javaIndex).getPatternType().get(0) != jsCodePatterns.get(jsIndex)
				.getPatternType().get(0)) {
			//System.out.println("fallito2");
			return null;
		}
		CodePattern javaPattern = javaCodePatterns.get(javaIndex);
		CodePattern jsPattern = jsCodePatterns.get(jsIndex);

		ICodePiece javaPiece = javaPattern.getPieces().get(2);
		ICodePiece jsPiece = jsPattern.getPieces().get(2);

		if (javaPiece.getPieces().get(0).getPieceType() != jsPiece.getPieces().get(0).getPieceType()) {
			//System.out.println("fallito3");
			return null;
		}

		if (javaPiece.getPieces().get(0).getPieceType() == PieceType.VARIABLE) {
			if (!new VariableComparator().compare(javaPiece.getPieceByType(PieceType.VARIABLE),
					jsPiece.getPieceByType(PieceType.VARIABLE))) {
				//System.out.println("fallito4");
				return null;
			}
		} else {
			if (!new NameComparator().compare(javaPiece.getPieceByType(PieceType.NAME),
					jsPiece.getPieceByType(PieceType.NAME))) {
				//System.out.println("fallito5");
				return null;
			}
		}

		if (!new ExpressionComparator().compare(javaPiece.getPieceByType(PieceType.EXPRESSION),
				jsPiece.getPieceByType(PieceType.EXPRESSION))) {
			//System.out.println("fallito6");
			return null;
		}

		if (!new NameComparator().compare(javaPattern.getPieceByType(PieceType.VALUE),
				jsPattern.getPieceByType(PieceType.VALUE))) {
			//System.out.println("fallito7");
			return null;
		}

		if (!javaPattern.getPieces().get(5).toString().equals(jsPattern.getPieces().get(5).toString())) {
			//System.out.println("fallito8");
			return null;
		}

		if (!new ExpressionComparator().compare(javaPattern.getPieceByType(PieceType.EXPRESSION),
				jsPattern.getPieceByType(PieceType.EXPRESSION))) {
			//System.out.println("fallito9");
			return null;
		}

		List<ICodePiece> javaCompList = javaPattern.getPieces().get(8).getPieces();
		List<ICodePiece> jsCompList = jsPattern.getPieces().get(8).getPieces();

		if (javaCompList.size() != jsCompList.size()) {
			//System.out.println("fallito10");
			return null;
		}

		for (int i = 0; i < javaCompList.size(); i++) {
			if (javaCompList.get(i).getPieceType() == PieceType.COMPOSITE) {
				if (!new NameComparator().compare(javaCompList.get(i).getPieceByType(PieceType.VARIABLE),
						jsCompList.get(i).getPieceByType(PieceType.VARIABLE))) {
					//System.out.println("fallito11");
					return null;
				}
				if (!javaCompList.get(i).getPieceByType(PieceType.KEYWORD).toString()
						.equals(jsCompList.get(i).getPieceByType(PieceType.KEYWORD).toString())) {
					//System.out.println("fallito12");
					return null;
				}
				ICodePiece a = javaCompList.get(i).getPieceByType(PieceType.EXPRESSION);
				ICodePiece b = jsCompList.get(i).getPieceByType(PieceType.EXPRESSION);
				if (a == null && b != null || a != null && b == null) {
					//System.out.println("fallito13");
					return null;
				}
				if (a != null && b != null) {
					if (!new ExpressionComparator().compare(
							javaCompList.get(i).getPieceByType(PieceType.EXPRESSION), jsCompList.get(i)
									.getPieceByType(PieceType.EXPRESSION))) {
						//System.out.println("fallito14");
						return null;
					}
				}
			}
		}

		return new int[][] { new int[] { javaIndex }, new int[] { jsIndex } };
	}

}
