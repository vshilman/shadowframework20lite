package codeconverter.js;

import codeconverter.PieceType;
import codeconverter.codepieces.CompositeCodePiece;
import codeconverter.codepieces.UniqueKeyword;

public class JsArrayDeclaration extends CompositeCodePiece {

	public JsArrayDeclaration() {
		add(new UniqueKeyword("new"), new JsName(), new UniqueKeyword("("), new JsArrayContent(),
				new UniqueKeyword(")"));
		setPieceType(PieceType.ARRAY_DECLARATION);
	}
}
