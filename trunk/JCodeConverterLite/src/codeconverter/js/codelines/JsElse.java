package codeconverter.js.codelines;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.codepieces.CompositeCodePiece;
import codeconverter.codepieces.OptionalCode;
import codeconverter.codepieces.UniqueKeyword;
import codeconverter.js.JsBooleanExpression;

public class JsElse  extends CodePattern{

	public JsElse() {
		
		addCodePiece(new OptionalCode(new UniqueKeyword("}")),new UniqueKeyword("else"),
				new OptionalCode(new CompositeCodePiece(
						new UniqueKeyword("if"),
						new UniqueKeyword("("),
						new JsBooleanExpression(),
						new UniqueKeyword(")")))
				);
		addPatternType(PatternType.ELSE,PatternType.ELSE_IF,PatternType.ASSIGNMENT,PatternType.LINE_OF_CODE);
	}
	
}
