package codeconverter.java;

import codeconverter.codepieces.CodeSequence;
import codeconverter.codepieces.CompositeCodePiece;
import codeconverter.codepieces.Name;
import codeconverter.codepieces.UniqueKeyword;

public class JavaImplementsDeclaration extends CompositeCodePiece{

	public JavaImplementsDeclaration() {
		add(new UniqueKeyword("implements"),new CodeSequence(new Name(),","));
	}
}
