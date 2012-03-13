package codeconverter.java;

import codeconverter.CodePattern;
import codeconverter.codepieces.CodeSequence;

public class JavaArrayContentDeclaration extends CodePattern{

	public JavaArrayContentDeclaration() {
		addCodePiece(new CodeSequence(new JavaAlgebraicExpression(),","));
	}
}