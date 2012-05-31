package codeconverter.js.webgl;

import codeconverter.PieceType;
import codeconverter.codepieces.CompositeCodePiece;
import codeconverter.codepieces.Name;
import codeconverter.codepieces.UniqueKeyword;

public class WebGlConstant extends CompositeCodePiece {

	public WebGlConstant() {
		super();
		add( new UniqueKeyword("gl."), new Name(PieceType.NAME));
		setPieceType(PieceType.OPENGL_CONSTANT);
	}
}
