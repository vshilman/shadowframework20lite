package codeconverter.cpp;

import codeconverter.PieceType;
import codeconverter.codepieces.CompositeCodePiece;
import codeconverter.codepieces.UniqueKeyword;

public class CppTernaryOperator extends CompositeCodePiece{

	public CppTernaryOperator() {
		generate(new CppAlgebraicExpression(this));
	}

	public CppTernaryOperator(boolean notGenerate) {

	}

	public CppTernaryOperator(CppAlgebraicExpression algebraicExpression){
		generate(algebraicExpression);
	}

	public void generate(CppAlgebraicExpression algebraicExpression){
		setPieceType(PieceType.TERNARY_OPERATOR);
		add(new CppBooleanExpression(algebraicExpression),
				new UniqueKeyword("?"),
				algebraicExpression,
				new UniqueKeyword(":"),
				algebraicExpression);

	}


}


