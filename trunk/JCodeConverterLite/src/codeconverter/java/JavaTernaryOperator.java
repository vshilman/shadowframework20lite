package codeconverter.java;

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
		add(new JavaBooleanExpression(algebraicExpression), new UniqueKeyword("?"), algebraicExpression, new UniqueKeyword(":"),
				algebraicExpression);
	}
}
