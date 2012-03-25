package codeconverter.js;

import java.util.Collections;

import codeconverter.PieceType;
import codeconverter.codepieces.CompositeCodePiece;
import codeconverter.codepieces.Expression;
import codeconverter.codepieces.Number;
import codeconverter.codepieces.UniqueKeyword;
import codeconverter.js.webgl.WebGlMethodEvaluation;

public class JsBooleanExpression extends Expression {

	public static String[] booleanSymbols = { "||", "&&", "===", "==", "<=", ">=", ">", "<0", "<", "!=" };

	public JsBooleanExpression() {
		super();
		generate(new JsName(), new JsName(), new JsMethodEvaluation("."));
	}

	public JsBooleanExpression(JsAlgebraicExpression algebraicExpression) {
		super();
		JsBitwiseExpression bitwiseExpression = new JsBitwiseExpression(true);
		JsMethodEvaluation jsMethodEvaluation = new JsMethodEvaluation(".", algebraicExpression,
				bitwiseExpression);
		WebGlMethodEvaluation webGlMethodEvaluation = new WebGlMethodEvaluation(".", algebraicExpression,
				bitwiseExpression);
		bitwiseExpression.generate(jsMethodEvaluation, webGlMethodEvaluation, new JsName(algebraicExpression,
				bitwiseExpression));

		generate(new JsName(algebraicExpression, bitwiseExpression), new JsName(algebraicExpression,
				bitwiseExpression), new JsMethodEvaluation(".", algebraicExpression, bitwiseExpression));
	}

	private void generate(JsName name1, JsName name2, JsMethodEvaluation methodEvaluation) {
		name1.setPieceType(PieceType.VARIABLE);
		name2.setPieceType(PieceType.VARIABLE);
		Collections.addAll(this.pieces, name1, new Number(), methodEvaluation);//
		this.pieces.add(new CompositeCodePiece(new UniqueKeyword("("), this, new UniqueKeyword(")")));
		this.pieces.add(new CompositeCodePiece(new UniqueKeyword("!"), name2));
		this.pieces.add(new CompositeCodePiece(new UniqueKeyword("!"), new UniqueKeyword("("), this,
				new UniqueKeyword(")")));
	}

	@Override
	public String[] getExpressionSeparators() {
		return booleanSymbols;
	}

	// @Override
	// public List<ICodePiece> getPieces() {
	// throw new
	// RuntimeException("Not yet implemented, missing also clone method");
	// }

}
