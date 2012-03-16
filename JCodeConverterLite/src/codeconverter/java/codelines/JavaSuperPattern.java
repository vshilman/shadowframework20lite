package codeconverter.java.codelines;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.codepieces.AlternativeCode;
import codeconverter.codepieces.CodeSequence;
import codeconverter.codepieces.OptionalCode;
import codeconverter.codepieces.UniqueKeyword;
import codeconverter.java.JavaAlgebraicExpression;
import codeconverter.java.JavaBitwiseExpression;

public class JavaSuperPattern extends CodePattern{

	public JavaSuperPattern() {
		addCodePiece(new UniqueKeyword("super"),new UniqueKeyword("("),
				new OptionalCode(new CodeSequence(new AlternativeCode(true,new JavaAlgebraicExpression(),new JavaBitwiseExpression()),", ")),
				new UniqueKeyword(")"));
		addCodePattern(PatternType.SUPER,PatternType.LINE_OF_CODE);
	}

}