package codeconverter.js;

import codeconverter.PieceType;
import codeconverter.codepieces.CodeSequence;

public class JsMethodVariables extends CodeSequence{

	public JsMethodVariables() {
		super(new JsName(),", ");
		setPieceType(PieceType.METHOD_VARIABLES);
	}
}
