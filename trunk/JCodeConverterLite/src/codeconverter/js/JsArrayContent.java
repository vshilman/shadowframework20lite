package codeconverter.js;

import codeconverter.codepieces.AlternativeCode;
import codeconverter.codepieces.CodeSequence;
import codeconverter.codepieces.CompositeCodePiece;
import codeconverter.codepieces.UniqueKeyword;

public class JsArrayContent extends CompositeCodePiece {

	public JsArrayContent() {
		add(new UniqueKeyword("["),new CodeSequence(
				new AlternativeCode(true,new JsAlgebraicExpression(),new JsBitwiseExpression()), ","),
				new UniqueKeyword("]"));
	}

}
