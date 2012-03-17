package codeconverter.js.codelines;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.codepieces.AlternativeCode;
import codeconverter.codepieces.OptionalCode;
import codeconverter.codepieces.UniqueKeyword;
import codeconverter.js.JsAlgebraicExpression;
import codeconverter.js.JsBitwiseExpression;
import codeconverter.js.JsMethodEvaluation;
import codeconverter.js.webgl.WebGlMethodEvaluation;

public class JsReturnPattern extends CodePattern{

	public JsReturnPattern() {
		addCodePiece(new UniqueKeyword("return"),
				new OptionalCode(new UniqueKeyword("this.")),
				new AlternativeCode(true,new JsAlgebraicExpression(),new JsBitwiseExpression(),
						new WebGlMethodEvaluation("."),new JsMethodEvaluation(".")));
		addCodePattern(PatternType.RETURN,PatternType.LINE_OF_CODE);
	}
	
}