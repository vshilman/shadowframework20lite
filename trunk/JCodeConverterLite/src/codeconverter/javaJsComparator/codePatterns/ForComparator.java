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
	public boolean compare(CodePattern javaPattern, CodePattern jsPattern) {
//	public int[][] compare(List<CodePattern> javaCodePatterns, int javaIndex, List<CodePattern> jsCodePatterns,
//			int jsIndex) {
		//System.out.println("Computo");
		if (javaPattern.getPatternType().get(0) != PatternType.FOR) {
			//System.out.println("fallito1");
			return false;
		}
		if (javaPattern.getPatternType().get(0) != jsPattern
				.getPatternType().get(0)) {
			//System.out.println("fallito2");
			return false;
		}
		
		
		ICodePiece javaPiece = javaPattern.getPieces().get(2);
		ICodePiece jsPiece = jsPattern.getPieces().get(2);

		if (javaPiece.getPieces().get(0).getPieceType() != jsPiece.getPieces().get(0).getPieceType()) {
			//System.out.println("fallito3");
			return false;
		}

		if (javaPiece.getPieces().get(0).getPieceType() == PieceType.VARIABLE) {
			if (!new VariableComparator().compare(javaPiece.getPieceByType(PieceType.VARIABLE),
					jsPiece.getPieceByType(PieceType.VARIABLE))) {
				//System.out.println("fallito4");
				return false;
			}
		} else {
			if (!new NameComparator().compare(javaPiece.getPieceByType(PieceType.NAME),
					jsPiece.getPieceByType(PieceType.NAME))) {
				//System.out.println("fallito5");
				return false;
			}
		}

		if (!new ExpressionComparator().compare(javaPiece.getPieceByType(PieceType.EXPRESSION),
				jsPiece.getPieceByType(PieceType.EXPRESSION))) {
			//System.out.println("fallito6");
			return false;
		}

		if (!new NameComparator().compare(javaPattern.getPieceByType(PieceType.VALUE),
				jsPattern.getPieceByType(PieceType.VALUE))) {
			//System.out.println("fallito7");
			return false;
		}

		if (!javaPattern.getPieces().get(5).toString().equals(jsPattern.getPieces().get(5).toString())) {
			//System.out.println("fallito8");
			return false;
		}

		if (!new ExpressionComparator().compare(javaPattern.getPieceByType(PieceType.EXPRESSION),
				jsPattern.getPieceByType(PieceType.EXPRESSION))) {
			//System.out.println("fallito9");
			return false;
		}

		List<ICodePiece> javaCompList = javaPattern.getPieces().get(8).getPieces();
		List<ICodePiece> jsCompList = jsPattern.getPieces().get(8).getPieces();

		if (javaCompList.size() != jsCompList.size()) {
			//System.out.println("fallito10");
			return false;
		}

		for (int i = 0; i < javaCompList.size(); i++) {
			if (javaCompList.get(i).getPieceType() == PieceType.COMPOSITE) {
				if (!new NameComparator().compare(javaCompList.get(i).getPieceByType(PieceType.VARIABLE),
						jsCompList.get(i).getPieceByType(PieceType.VARIABLE))) {
					//System.out.println("fallito11");
					return false;
				}
				if (!javaCompList.get(i).getPieceByType(PieceType.KEYWORD).toString()
						.equals(jsCompList.get(i).getPieceByType(PieceType.KEYWORD).toString())) {
					//System.out.println("fallito12");
					return false;
				}
				ICodePiece a = javaCompList.get(i).getPieceByType(PieceType.EXPRESSION);
				ICodePiece b = jsCompList.get(i).getPieceByType(PieceType.EXPRESSION);
				if (a == null && b != null || a != null && b == null) {
					//System.out.println("fallito13");
					return false;
				}
				if (a != null && b != null) {
					if (!new ExpressionComparator().compare(
							javaCompList.get(i).getPieceByType(PieceType.EXPRESSION), jsCompList.get(i)
									.getPieceByType(PieceType.EXPRESSION))) {
						//System.out.println("fallito14");
						return false;
					}
				}
			}
		}

		return true;
	}

}
