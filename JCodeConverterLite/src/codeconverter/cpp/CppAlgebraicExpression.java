package codeconverter.cpp;

import java.util.Collections;

import codeconverter.ICodePiece;
import codeconverter.PieceType;
import codeconverter.codepieces.BestAlternativeCode;
import codeconverter.codepieces.CompositeCodePiece;
import codeconverter.codepieces.Expression;
import codeconverter.codepieces.Number;
import codeconverter.codepieces.OptionalCode;
import codeconverter.codepieces.UniqueKeyword;

public class CppAlgebraicExpression extends Expression{

	public static String[] algebraicSymbols={"*","+","/","-","++","--"};

	public CppAlgebraicExpression() {
		super();
		generate2(new CppTernaryOperator(this));
	}

	public CppAlgebraicExpression(CppTernaryOperator ternaryOperator) {
		super();
		generate2(ternaryOperator);
	}

	public CppAlgebraicExpression(boolean notGenerate) {
		super();
	}

	public CppAlgebraicExpression(CppMethodEvaluation cppMethod, CppName name, CppTernaryOperator ternaryOperator, CppNewStatement newStatement) {
		super();
		generate(cppMethod, name, ternaryOperator, newStatement);
	}


	public void generate (CppMethodEvaluation cppMethod, CppName name, CppTernaryOperator ternaryOperator, CppNewStatement newStatement){
		name.setPieceType(PieceType.VARIABLE);
		ICodePiece piece=new CompositeCodePiece(new UniqueKeyword("("),
												this,
												new UniqueKeyword(")"));
		Collections.addAll(this.pieces, new CompositeCodePiece(new OptionalCode(/*casting*/new CompositeCodePiece(new UniqueKeyword("("),
																												  new CppCompositeType(),
																												   new UniqueKeyword(")"))),
																new BestAlternativeCode(true, name,piece)),
										new Number(),
									    newStatement,
									    ternaryOperator,
										cppMethod);
	}

	public void generate2 (CppTernaryOperator ternaryOperator){
		CppBitwiseExpression cppBitwiseExpression=new CppBitwiseExpression(true);
		CppMethodEvaluation cppMethodEvaluation=new CppMethodEvaluation("->",this,cppBitwiseExpression);
		CppName name=new CppName(this,cppBitwiseExpression);
		CppNewStatement newStatement=new CppNewStatement(this, new CppCompositeType());
		generate(cppMethodEvaluation,name,ternaryOperator,newStatement);
		cppBitwiseExpression.generate(cppMethodEvaluation,name,newStatement);
	}




	@Override
	public String[] getExpressionSeparators() {
		return algebraicSymbols;
	}




}
