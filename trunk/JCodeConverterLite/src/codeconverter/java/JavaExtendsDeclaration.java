package codeconverter.java;

import codeconverter.PieceType;
import codeconverter.codepieces.CodeSequence;
import codeconverter.codepieces.CompositeCodePiece;
import codeconverter.codepieces.UniqueKeyword;

public class JavaExtendsDeclaration extends CompositeCodePiece{

	public JavaExtendsDeclaration() {
		add(new UniqueKeyword("extends"),new CodeSequence(new JavaName(),","));
	}
}
