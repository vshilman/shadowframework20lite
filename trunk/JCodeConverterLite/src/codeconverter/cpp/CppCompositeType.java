package codeconverter.cpp;

import codeconverter.PieceType;
import codeconverter.codepieces.CompositeCodePiece;
import codeconverter.codepieces.Name;
import codeconverter.codepieces.OptionalCode;
import codeconverter.codepieces.UniqueKeyword;

public class CppCompositeType extends CompositeCodePiece{

	public CppCompositeType() {
		add(new OptionalCode(new CompositeCodePiece(new Name(), new UniqueKeyword("::"))),
		    new CppType());

	}


}
