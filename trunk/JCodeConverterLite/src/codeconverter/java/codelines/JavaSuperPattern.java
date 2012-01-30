package codeconverter.java.codelines;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.codepieces.AlgebraicExpression;
import codeconverter.codepieces.CodeSequence;
import codeconverter.codepieces.OptionalCode;
import codeconverter.codepieces.UniqueKeyword;

public class JavaSuperPattern extends CodePattern{

	public JavaSuperPattern() {
		addCodePiece(new UniqueKeyword("super"),new UniqueKeyword("("),
				new OptionalCode(new CodeSequence(new AlgebraicExpression(),", ")),
				new UniqueKeyword(")"));
		addCodePattern(PatternType.LINE_OF_CODE);
		addCodePattern(PatternType.SUPER);
	}

}