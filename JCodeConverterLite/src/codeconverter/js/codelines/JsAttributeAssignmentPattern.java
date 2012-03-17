package codeconverter.js.codelines;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.PieceType;
import codeconverter.codepieces.AlternativeCode;
import codeconverter.codepieces.UniqueKeyword;
import codeconverter.js.JsAlgebraicExpression;
import codeconverter.js.JsBitwiseExpression;
import codeconverter.js.JsName;

public class JsAttributeAssignmentPattern extends CodePattern{

	public JsAttributeAssignmentPattern() {
		addCodePiece(new UniqueKeyword("this."),new JsName(PieceType.NAME),new UniqueKeyword("="),
				new AlternativeCode(true,new JsAlgebraicExpression(),new JsBitwiseExpression()));
		addCodePattern(PatternType.ATTRIBUTE_ASSIGNMENT,PatternType.ASSIGNMENT,PatternType.LINE_OF_CODE);
	}
	

}
