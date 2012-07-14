package codeconverter.java.codelines;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.PieceType;
import codeconverter.codepieces.AlternativeCode;
import codeconverter.codepieces.UniqueKeyword;
import codeconverter.java.JavaAlgebraicExpression;
import codeconverter.java.JavaArrayContent;
import codeconverter.java.JavaArrayDeclaration;
import codeconverter.java.JavaBitwiseExpression;
import codeconverter.java.JavaName;
import codeconverter.java.JavaNewStatement;

public class JavaAttributeAssignmentPattern extends CodePattern{

	public JavaAttributeAssignmentPattern() {
		addCodePiece(new UniqueKeyword("this."),new JavaName(PieceType.NAME),new UniqueKeyword("="),
				new AlternativeCode(true, new JavaAlgebraicExpression(),
						new JavaBitwiseExpression(), new JavaNewStatement(),new JavaArrayContent(),new JavaArrayDeclaration()));
		addPatternType(PatternType.ATTRIBUTE_ASSIGNMENT,PatternType.ASSIGNMENT,PatternType.LINE_OF_CODE);
	}
	

}
