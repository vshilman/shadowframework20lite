package codeconverter.java;

import codeconverter.codepieces.CodeSequence;
import codeconverter.codepieces.CompositeCodePiece;
import codeconverter.codepieces.UniqueKeyword;

public class JavaThrowsDeclaration extends CompositeCodePiece{

	public JavaThrowsDeclaration() {
		add(new UniqueKeyword("throws"),new CodeSequence(new JavaName(),","));
	}
}
