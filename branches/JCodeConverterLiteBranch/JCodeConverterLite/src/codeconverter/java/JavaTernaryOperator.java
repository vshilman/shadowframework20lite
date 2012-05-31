package codeconverter.java;

import codeconverter.PieceType;
import codeconverter.codepieces.CompositeCodePiece;
import codeconverter.codepieces.UniqueKeyword;

public class JavaTernaryOperator extends CompositeCodePiece {

	public JavaTernaryOperator() {
		generate(new JavaAlgebraicExpression(this));
	}

	public JavaTernaryOperator(boolean notGenerate) {
	}

	public JavaTernaryOperator(JavaAlgebraicExpression algebraicExpression) {
		generate(algebraicExpression);
	}

	public void generate(JavaAlgebraicExpression algebraicExpression) {
		setPieceType(PieceType.TERNARY_OPERATOR);
		add(new JavaBooleanExpression(algebraicExpression), new UniqueKeyword("?"), algebraicExpression,
				new UniqueKeyword(":"), algebraicExpression);
	}
}
