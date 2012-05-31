package codeconverter.java.codelines;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.codepieces.OptionalCode;
import codeconverter.codepieces.UniqueKeyword;
import codeconverter.java.JavaBooleanExpression;

public class JavaIf  extends CodePattern{

	public JavaIf() {
		
		addCodePiece(new OptionalCode(new UniqueKeyword("else")),new UniqueKeyword("if"),
				new UniqueKeyword("("),
				new JavaBooleanExpression(),new UniqueKeyword(")"));
		addCodePattern(PatternType.IF,PatternType.ASSIGNMENT,PatternType.LINE_OF_CODE);
	}
	
}
