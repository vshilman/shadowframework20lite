package codeconverter.javaJsComparator.codePatterns;

import codeconverter.CodePattern;
import codeconverter.ICodePiece;
import codeconverter.PatternType;
import codeconverter.PieceType;
import codeconverter.comparator.CodePatternComparator;
import codeconverter.javaJsComparator.codePieces.NameComparator;

public class AttributeAndVariableDeclarationComparator implements CodePatternComparator {

	@Override
	public boolean compare(CodePattern javaPattern, CodePattern jsPattern) {
	
//	public int[][] compare(List<CodePattern> javaCodePatterns, int javaIndex,
//			List<CodePattern> jsCodePatterns, int jsIndex) {
		if(javaPattern.getPatternType().size()>1){
			return false;
		}
		if (javaPattern.getPatternType().get(0) != PatternType.ATTRIBUTE_DECLARATION) {
			return false;
		}
		if (jsPattern.getPatternType().get(0) != PatternType.VARIABLE_DECLARATION
				|| jsPattern.getPatternType().get(1) == PatternType.VARIABLE_ASSIGNMENT) {
			return false;
		}
		
		ICodePiece javaPiece = javaPattern.getPieces().get(2).getPieces().get(0).getPieces().get(0); //TODO Dovrebe prendere il JavaNamePart
		javaPiece.setPieceType(PieceType.NAME);

		if (!new NameComparator().compare(javaPiece, jsPattern.getPieceByType(PieceType.VARIABLE)
				.getPieceByType(PieceType.NAME))) {
			return false;
		}

		return true;
	}

}
