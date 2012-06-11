package tests.tmp;

import java.util.List;

import codeconverter.CodePattern;
import codeconverter.ICodePiece;
import codeconverter.PatternType;
import codeconverter.PieceType;
import codeconverter.javaJsComparator.CodePatternComparator;
import codeconverter.javaJsComparator.codePieces.MethodVariablesComparator;

public class PrototypedMethodDeclarationComparator implements CodePatternComparator {

	@Override
	public int[][] compare(List<CodePattern> javaCodePatterns, int javaIndex,
			List<CodePattern> jsCodePatterns, int jsIndex) {
		CodePattern javaCodePattern=javaCodePatterns.get(javaIndex);
		CodePattern jsCodePattern=jsCodePatterns.get(jsIndex);
		
		if(compare(javaCodePattern, jsCodePattern)){
			return new int[][] { new int[] { javaIndex }, new int[] { jsIndex } };
		}
		return null;
	}

	public boolean compare(CodePattern javaPattern, CodePattern jsPattern) {
		if (javaPattern.getPatternType().get(0) != PatternType.METHOD_DECLARATION) {
			return false;
		}
		if (javaPattern.getPatternType().get(0) != jsPattern
				.getPatternType().get(0)) {
			return false;
		}

		ICodePiece javaName=javaPattern.getPieceByType(PieceType.NAME);
		ICodePiece jsName=jsPattern.getPieceByType(PieceType.NAME);

		if (!javaName.toString().trim().equals(jsName.toString().trim())) {
			return false;
		}
		if (!new MethodVariablesComparator().compare(javaPattern.getPieceByType(PieceType.METHOD_VARIABLES),
				jsPattern.getPieceByType(PieceType.METHOD_VARIABLES))) {
			return false;
		}

		return true;
	}

}
