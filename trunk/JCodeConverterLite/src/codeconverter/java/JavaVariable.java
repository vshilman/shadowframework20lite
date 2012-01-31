package codeconverter.java;

import codeconverter.PieceType;
import codeconverter.codepieces.CompositeCodePiece;
import codeconverter.codepieces.Name;

public class JavaVariable extends CompositeCodePiece{

	public JavaVariable() {
		add(new JavaType(),new JavaName(PieceType.NAME));
	}
	
	@Override
	public PieceType getPieceType() {
		return PieceType.VARIABLE;
	}
}
