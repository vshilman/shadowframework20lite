package codeconverter.java.codelines;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.codepieces.BooleanExpression;
import codeconverter.codepieces.Expression;
import codeconverter.codepieces.UniqueKeyword;

public class JavaIf  extends CodePattern{

	private Expression expressionCloned;
	
	public JavaIf() {
		
		addCodePiece(new UniqueKeyword("if"),new UniqueKeyword("("),
				new BooleanExpression(),new UniqueKeyword(")"));
		addCodePattern(PatternType.ASSIGNMENT,PatternType.LINE_OF_CODE);
	}
	
}
