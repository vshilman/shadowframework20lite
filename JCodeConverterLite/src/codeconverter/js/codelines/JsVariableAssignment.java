package codeconverter.js.codelines;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.PieceType;
import codeconverter.codepieces.BestAlternativeCode;
import codeconverter.codepieces.KeywordSet;
import codeconverter.codepieces.OptionalCode;
import codeconverter.codepieces.UniqueKeyword;
import codeconverter.js.JsAlgebraicExpression;
import codeconverter.js.JsArrayContent;
import codeconverter.js.JsArrayDeclaration;
import codeconverter.js.JsBitwiseExpression;
import codeconverter.js.JsMethodEvaluation;
import codeconverter.js.JsName;
import codeconverter.js.JsNewStatement;
import codeconverter.js.webgl.WebGlMethodEvaluation;

public class JsVariableAssignment extends CodePattern{

	public JsVariableAssignment() {
		addCodePiece(new JsName(PieceType.NAME),new OptionalCode(new KeywordSet("+","*","-","/")),
				new UniqueKeyword("="),
				new BestAlternativeCode(true,new WebGlMethodEvaluation("."), new JsMethodEvaluation("."),
						new JsAlgebraicExpression(),new JsBitwiseExpression(),new JsNewStatement(),new JsArrayContent(),new JsArrayDeclaration()));
		addPatternType(PatternType.VARIABLE_ASSIGNMENT,PatternType.ASSIGNMENT,PatternType.LINE_OF_CODE);
	}
}
