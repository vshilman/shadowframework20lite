package codeconverter.cpp;

import codeconverter.codepieces.CompositeCodePiece;
import codeconverter.codepieces.OptionalCode;
import codeconverter.codepieces.UniqueKeyword;

public class CppCompositeType extends CompositeCodePiece{

	public CppCompositeType() {
		add(new OptionalCode(new CompositeCodePiece(new CppName(), new UniqueKeyword("::"))),
		    new CppType());

	}


}
