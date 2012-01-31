package codeconverter.java.codelines;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.codepieces.AlternativeCode;
import codeconverter.codepieces.UniqueKeyword;
import codeconverter.java.JavaAlgebraicExpression;
import codeconverter.java.JavaNewStatement;
import codeconverter.java.JavaVariable;

public class JavaVariableDeclaration extends CodePattern{

	public JavaVariableDeclaration() {
		addCodePiece(new JavaVariable(),new UniqueKeyword("="),
				new AlternativeCode(true,new JavaAlgebraicExpression(),new JavaNewStatement()));
		addCodePattern(PatternType.LINE_OF_CODE,PatternType.VARIABLE_DECLARATION);
	}
}
