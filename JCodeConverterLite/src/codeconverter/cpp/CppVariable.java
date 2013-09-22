package codeconverter.cpp;

import codeconverter.PieceType;
import codeconverter.codepieces.CompositeCodePiece;

public class CppVariable extends CompositeCodePiece{

public CppVariable() {
	add(new CppCompositeType(),new CppName(PieceType.NAME));
	setPieceType(PieceType.VARIABLE);
}


}
