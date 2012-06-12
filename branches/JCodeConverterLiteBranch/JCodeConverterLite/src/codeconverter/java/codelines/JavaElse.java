package codeconverter.java.codelines;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.codepieces.CompositeCodePiece;
import codeconverter.codepieces.OptionalCode;
import codeconverter.codepieces.UniqueKeyword;
import codeconverter.java.JavaBooleanExpression;

public class JavaElse  extends CodePattern{

	public JavaElse() {
		
		addCodePiece(new OptionalCode(new UniqueKeyword("}")),new UniqueKeyword("else"),
				new OptionalCode(new CompositeCodePiece(
						new UniqueKeyword("if"),
						new UniqueKeyword("("),
						new JavaBooleanExpression(),
						new UniqueKeyword(")")))
				);
		addPatternType(PatternType.ELSE,PatternType.ELSE_IF,PatternType.ASSIGNMENT,PatternType.LINE_OF_CODE);
	}
	
}
