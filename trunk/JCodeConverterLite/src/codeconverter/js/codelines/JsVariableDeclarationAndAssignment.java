package codeconverter.js.codelines;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.codepieces.BestAlternativeCode;
import codeconverter.codepieces.OptionalCode;
import codeconverter.codepieces.UniqueKeyword;
import codeconverter.js.JsAlgebraicExpression;
import codeconverter.js.JsArrayContent;
import codeconverter.js.JsBitwiseExpression;
import codeconverter.js.JsMethodEvaluation;
import codeconverter.js.JsNewStatement;
import codeconverter.js.JsVariable;
import codeconverter.js.webgl.WebGlMethodEvaluation;

public class JsVariableDeclarationAndAssignment extends CodePattern{

	public JsVariableDeclarationAndAssignment() {
		addCodePiece(new JsVariable(),new UniqueKeyword("="),
				new OptionalCode(new BestAlternativeCode(true,new WebGlMethodEvaluation("."),
						new JsAlgebraicExpression(),new JsBitwiseExpression(),new JsNewStatement(),
						new JsMethodEvaluation("."),new JsArrayContent())));
		addCodePattern(PatternType.VARIABLE_DECLARATION,PatternType.VARIABLE_ASSIGNMENT,PatternType.ASSIGNMENT,PatternType.LINE_OF_CODE);
	}
}
