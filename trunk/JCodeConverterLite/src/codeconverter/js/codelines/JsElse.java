package codeconverter.js.codelines;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.codepieces.OptionalCode;
import codeconverter.codepieces.UniqueKeyword;
import codeconverter.js.JsBooleanExpression;

public class JsElse  extends CodePattern{

	public JsElse() {
		
		addCodePiece(new OptionalCode(new UniqueKeyword("}")),new UniqueKeyword("else"),
				new OptionalCode(new UniqueKeyword("if")),
				new OptionalCode(new UniqueKeyword("(")),
				new JsBooleanExpression(),
				new OptionalCode(new UniqueKeyword(")")));
		addCodePattern(PatternType.ELSE,PatternType.ELSE_IF,PatternType.ASSIGNMENT,PatternType.LINE_OF_CODE);
	}
	
}
