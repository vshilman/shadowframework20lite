package codeconverter.javaJsComparator.codePatterns;

import java.util.List;

import codeconverter.CodePattern;
import codeconverter.ICodePiece;
import codeconverter.PatternType;
import codeconverter.PieceType;
import codeconverter.javaJsComparator.CodePatternComparator;
import codeconverter.javaJsComparator.codePieces.NameComparator;

public class AttributeAndVariableDeclarationComparator implements CodePatternComparator {

	@Override
	public int[][] compare(List<CodePattern> javaCodePatterns, int javaIndex,
			List<CodePattern> jsCodePatterns, int jsIndex) {
		if(javaCodePatterns.get(javaIndex).getPatternType().size()>1){
			return null;
		}
		if (javaCodePatterns.get(javaIndex).getPatternType().get(0) != PatternType.ATTRIBUTE_DECLARATION) {
			return null;
		}
		if (jsCodePatterns.get(jsIndex).getPatternType().get(0) != PatternType.VARIABLE_DECLARATION
				|| jsCodePatterns.get(jsIndex).getPatternType().get(1) == PatternType.VARIABLE_ASSIGNMENT) {
			return null;
		}
		CodePattern javaPattern = javaCodePatterns.get(javaIndex);
		CodePattern jsPattern = jsCodePatterns.get(jsIndex);

		ICodePiece javaPiece = javaPattern.getPieces().get(2).getPieces().get(0).getPieces().get(0);
		javaPiece.setPieceType(PieceType.NAME);

		if (!new NameComparator().compare(javaPiece, jsPattern.getPieceByType(PieceType.VARIABLE)
				.getPieceByType(PieceType.NAME))) {
			return null;
		}

		return new int[][] { new int[] { javaIndex }, new int[] { jsIndex } };
	}

}
