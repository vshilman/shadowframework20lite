package codeconverter.java;

import codeconverter.PieceType;
import codeconverter.codepieces.CompositeCodePiece;

public class JavaVariable extends CompositeCodePiece {

	public JavaVariable() {
		add(new JavaType(), new JavaName(PieceType.NAME));
		setPieceType(PieceType.VARIABLE);
	}

}
