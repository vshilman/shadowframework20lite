package codeconverter.js;

import codeconverter.codepieces.CompositeCodePiece;
import codeconverter.codepieces.UniqueKeyword;

public class JsTernaryOperator extends CompositeCodePiece {

	public JsTernaryOperator() {
		generate(new JsAlgebraicExpression(this));
	}

	public JsTernaryOperator(boolean notGenerate) {
	}

	public JsTernaryOperator(JsAlgebraicExpression algebraicExpression) {
		generate(algebraicExpression);
	}

	public void generate(JsAlgebraicExpression algebraicExpression) {
		add(new JsBooleanExpression(algebraicExpression), new UniqueKeyword("?"), algebraicExpression, new UniqueKeyword(":"),
				algebraicExpression);
	}
}
