package codeconverter.java.codelines;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.PieceType;
import codeconverter.codepieces.AlgebraicExpression;
import codeconverter.codepieces.Name;
import codeconverter.codepieces.UniqueKeyword;

public class JavaAssignment  extends CodePattern{

	public JavaAssignment() {
		addCodePiece(new Name(PieceType.VARIABLE),new UniqueKeyword("="),new AlgebraicExpression());
		addCodePattern(PatternType.ASSIGNMENT,PatternType.LINE_OF_CODE);
	}
	
}
