package codeconverter.java.codelines;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.PieceType;
import codeconverter.codepieces.UniqueKeyword;
import codeconverter.java.JavaAlgebraicExpression;
import codeconverter.java.JavaName;

public class JavaAttributeAssignmentPattern extends CodePattern{

	public JavaAttributeAssignmentPattern() {
		addCodePiece(new UniqueKeyword("this."),new JavaName(PieceType.NAME),new UniqueKeyword("="),
				new JavaAlgebraicExpression());
		addCodePattern(PatternType.ASSIGNMENT,PatternType.LINE_OF_CODE);
	}
	

}
