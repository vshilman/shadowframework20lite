package codeconverter.java.codelines;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.codepieces.AlternativeCode;
import codeconverter.codepieces.OptionalCode;
import codeconverter.codepieces.UniqueKeyword;
import codeconverter.java.JavaAlgebraicExpression;
import codeconverter.java.JavaMethodEvaluation;

public class JavaReturnPattern extends CodePattern{

	public JavaReturnPattern() {
		addCodePiece(new UniqueKeyword("return"),
				new OptionalCode(new UniqueKeyword("this.")),
				new AlternativeCode(true,new JavaAlgebraicExpression(),new JavaMethodEvaluation(".")));
		addCodePattern(PatternType.RETURN,PatternType.LINE_OF_CODE);
	}
	
}