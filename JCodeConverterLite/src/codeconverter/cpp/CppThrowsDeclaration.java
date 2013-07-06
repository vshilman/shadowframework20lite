package codeconverter.cpp;

import codeconverter.codepieces.CodeSequence;
import codeconverter.codepieces.CompositeCodePiece;
import codeconverter.codepieces.OptionalCode;
import codeconverter.codepieces.UniqueKeyword;

public class CppThrowsDeclaration extends CompositeCodePiece{


	public CppThrowsDeclaration() {
		add(new UniqueKeyword("throw"),
			new UniqueKeyword("("),
			new OptionalCode(new CodeSequence(new CppCompositeType(), ",")),
			new UniqueKeyword(")")); //throw (int)
	}

}
