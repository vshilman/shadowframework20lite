package codeconverter.js;

import codeconverter.PieceType;
import codeconverter.codepieces.CompositeCodePiece;
import codeconverter.codepieces.UniqueKeyword;

public class JsVariable extends CompositeCodePiece{

	public JsVariable() {
		add(new UniqueKeyword("var"),new JsName(PieceType.NAME));
	}
	
	@Override
	public PieceType getPieceType() {
		return PieceType.VARIABLE;
	}
}
