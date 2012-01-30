package codeconverter.java.codelines;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.codepieces.BooleanExpression;
import codeconverter.codepieces.OptionalCode;
import codeconverter.codepieces.UniqueKeyword;

public class JavaElse  extends CodePattern{

	public JavaElse() {
		
		addCodePiece(new OptionalCode(new UniqueKeyword("}")),new UniqueKeyword("else"),
				new OptionalCode(new UniqueKeyword("if")),
				new OptionalCode(new UniqueKeyword("(")),
				new BooleanExpression(),
				new OptionalCode(new UniqueKeyword(")")));
		addCodePattern(PatternType.ASSIGNMENT,PatternType.LINE_OF_CODE);
	}
	
}
