package codeconverter.cpp;

import codeconverter.PieceType;
import codeconverter.codepieces.CompositeCodePiece;

public class CppTernaryOperator extends CompositeCodePiece{

	public CppTernaryOperator() {
		//generate
	}

	public void generate(CppAlgebraicExpression algebraicExpression){
		setPieceType(PieceType.TERNARY_OPERATOR);

	}


}


