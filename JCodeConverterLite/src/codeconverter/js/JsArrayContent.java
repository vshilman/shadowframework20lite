package codeconverter.js;

import codeconverter.codepieces.AlternativeCode;
import codeconverter.codepieces.CodeSequence;
import codeconverter.codepieces.CompositeCodePiece;
import codeconverter.codepieces.UniqueKeyword;

public class JsArrayContent extends CompositeCodePiece {

	public JsArrayContent() {
		generate(new JsAlgebraicExpression(),new JsBitwiseExpression());
	}
	
	public JsArrayContent(JsAlgebraicExpression algebraicExpression,JsBitwiseExpression bitwiseExpression) {
		generate(algebraicExpression,bitwiseExpression);
	}

	private void generate(JsAlgebraicExpression algebraicExpression,JsBitwiseExpression bitwiseExpression) {
		add(new UniqueKeyword("["),new CodeSequence(
				new AlternativeCode(true,algebraicExpression,bitwiseExpression), ","),
				new UniqueKeyword("]"));
	}

}
