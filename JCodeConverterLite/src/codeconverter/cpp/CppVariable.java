package codeconverter.cpp;

import java.util.List;

import codeconverter.PieceType;
import codeconverter.codepieces.CompositeCodePiece;
import codeconverter.codepieces.Value;

public class CppVariable extends CompositeCodePiece{

public CppVariable() {
	add(new CppCompositeType(),new CppName(PieceType.NAME));
	setPieceType(PieceType.VARIABLE);
}


}
