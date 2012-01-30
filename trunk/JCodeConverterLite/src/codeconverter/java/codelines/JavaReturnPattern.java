package codeconverter.java.codelines;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.codepieces.AlgebraicExpression;
import codeconverter.codepieces.UniqueKeyword;

public class JavaReturnPattern extends CodePattern{

	public JavaReturnPattern() {
		addCodePiece(new UniqueKeyword("return"),new AlgebraicExpression());
		addCodePattern(PatternType.RETURN,PatternType.LINE_OF_CODE);
	}
	
}