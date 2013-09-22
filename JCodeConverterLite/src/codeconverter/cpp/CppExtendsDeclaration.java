package codeconverter.cpp;

import codeconverter.codepieces.CodeSequence;
import codeconverter.codepieces.CompositeCodePiece;
import codeconverter.codepieces.UniqueKeyword;

public class CppExtendsDeclaration extends CompositeCodePiece{

	public CppExtendsDeclaration() {
		add(new UniqueKeyword(":"), new CodeSequence( new CompositeCodePiece(new CppSpecifier(), new CppName()),","));
	}


}
