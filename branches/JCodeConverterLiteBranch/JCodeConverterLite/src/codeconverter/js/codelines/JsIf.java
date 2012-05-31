package codeconverter.js.codelines;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.codepieces.OptionalCode;
import codeconverter.codepieces.UniqueKeyword;
import codeconverter.js.JsBooleanExpression;

public class JsIf  extends CodePattern{

	public JsIf() {
		
		addCodePiece(new OptionalCode(new UniqueKeyword("else")),new UniqueKeyword("if"),
				new UniqueKeyword("("),
				new JsBooleanExpression(),new UniqueKeyword(")"));
		addCodePattern(PatternType.IF,PatternType.ASSIGNMENT,PatternType.LINE_OF_CODE);
	}
	
}
