package codeconverter.java.codelines;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.codepieces.OptionalCode;
import codeconverter.codepieces.UniqueKeyword;
import codeconverter.java.JavaBooleanExpression;

public class JavaElse  extends CodePattern{

	public JavaElse() {
		
		addCodePiece(new OptionalCode(new UniqueKeyword("}")),new UniqueKeyword("else"),
				new OptionalCode(new UniqueKeyword("if")),
				new OptionalCode(new UniqueKeyword("(")),
				new JavaBooleanExpression(),
				new OptionalCode(new UniqueKeyword(")")));
		addCodePattern(PatternType.ASSIGNMENT,PatternType.LINE_OF_CODE);
	}
	
}
