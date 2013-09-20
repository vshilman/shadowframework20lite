package codeconverter.java;

import codeconverter.PieceType;
import codeconverter.codepieces.CodeSequence;
import codeconverter.codepieces.CompositeCodePiece;
import codeconverter.codepieces.UniqueKeyword;

public class JavaImplementsDeclaration extends CompositeCodePiece{

	public JavaImplementsDeclaration() {
		add(new UniqueKeyword("implements"),new CodeSequence(new JavaName(),","));
	}
}
