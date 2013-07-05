package codeconverter.cpp;

import codeconverter.PieceType;
import codeconverter.codepieces.CompositeCodePiece;

public class CppArrayDeclaration extends CompositeCodePiece{

	public CppArrayDeclaration() {
		add(new CppNewStatement(), new CppArrayContent());
		setPieceType(PieceType.ARRAY_DECLARATION);
	}

}
