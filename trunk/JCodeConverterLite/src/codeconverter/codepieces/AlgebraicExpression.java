package codeconverter.codepieces;

import java.util.Collections;

import codeconverter.ICodePiece;

public class AlgebraicExpression extends Expression{

	public static String[] algebraicSymbols={
		"*","+","/","-"
	};
	
	public AlgebraicExpression() {
		super();
		ICodePiece piece=new CompositeCodePiece(new UniqueKeyword("("),this,new UniqueKeyword(")"));
		Collections.addAll(this.pieces,new Name(),new Number(),piece,new MethodEvaluation(".",this));//
	}
	
	AlgebraicExpression(MethodEvaluation methodEvaluation) {
		super();
		ICodePiece piece=new CompositeCodePiece(new UniqueKeyword("("),this,new UniqueKeyword(")"));
		Collections.addAll(this.pieces,new Name(),new Number(),piece,methodEvaluation);//
	}


	@Override
	public String[] getExpressionSeparators() {
		return algebraicSymbols;
	}
	
}
