package codeconverter.java.codelines;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.PieceType;
import codeconverter.codepieces.AlgebraicExpression;
import codeconverter.codepieces.Name;
import codeconverter.codepieces.UniqueKeyword;

public class JavaAttributeAssignmentPattern extends CodePattern{

	public JavaAttributeAssignmentPattern() {
		addCodePiece(new UniqueKeyword("this."),new Name(PieceType.NAME),new UniqueKeyword("="),
				new AlgebraicExpression());
		addCodePattern(PatternType.ASSIGNMENT,PatternType.LINE_OF_CODE);
	}
	

}
