package codeconverter.java;

import java.util.Collections;

import codeconverter.PieceType;
import codeconverter.codepieces.CompositeCodePiece;
import codeconverter.codepieces.Expression;
import codeconverter.codepieces.Number;
import codeconverter.codepieces.UniqueKeyword;
import codeconverter.java.jogl.JoglMethodEvaluation;

public class JavaBooleanExpression extends Expression {

	public static String[] booleanSymbols = { "||", "&&", "==", "<=", ">=", ">", "<0", "<", "!=" };

	public JavaBooleanExpression() {
		super();
		generate(new JavaName(), new JavaName(), new JavaMethodEvaluation("."));
	}

	public JavaBooleanExpression(JavaAlgebraicExpression algebraicExpression) {
		super();
		JavaBitwiseExpression bitwiseExpression = new JavaBitwiseExpression(true);
		JavaMethodEvaluation javaMethodEvaluation = new JavaMethodEvaluation(".", algebraicExpression,
				bitwiseExpression);
		JoglMethodEvaluation joglMethodEvaluation = new JoglMethodEvaluation(".", algebraicExpression,
				bitwiseExpression);
		bitwiseExpression.generate(javaMethodEvaluation, joglMethodEvaluation, new JavaName(
				algebraicExpression, bitwiseExpression),new JavaNewStatement(algebraicExpression, new JavaName(algebraicExpression, bitwiseExpression)));

		generate(new JavaName(algebraicExpression, bitwiseExpression), new JavaName(algebraicExpression,
				bitwiseExpression), new JavaMethodEvaluation(".", algebraicExpression, bitwiseExpression));
	}

	private void generate(JavaName name1, JavaName name2, JavaMethodEvaluation methodEvaluation) {
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
