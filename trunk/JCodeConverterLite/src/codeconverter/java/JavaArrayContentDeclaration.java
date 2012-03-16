package codeconverter.java;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.codepieces.AlternativeCode;
import codeconverter.codepieces.CodeSequence;

public class JavaArrayContentDeclaration extends CodePattern{

	public JavaArrayContentDeclaration() {
		addCodePiece(new CodeSequence(new AlternativeCode(true,new JavaAlgebraicExpression(),new JavaBitwiseExpression()) ,","));
		addCodePattern(PatternType.ARRAY_CONTENT_DECLARTION);
	}
}