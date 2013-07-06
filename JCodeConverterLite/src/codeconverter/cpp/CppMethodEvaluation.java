package codeconverter.cpp;

import java.util.BitSet;

import codeconverter.PieceType;
import codeconverter.codepieces.BestAlternativeCode;
import codeconverter.codepieces.CodeSequence;
import codeconverter.codepieces.CompositeCodePiece;
import codeconverter.codepieces.OptionalCode;
import codeconverter.codepieces.UniqueKeyword;

public class CppMethodEvaluation extends CompositeCodePiece{


	public CppMethodEvaluation(String methodsSyntax) {
		super();
		CppName name=new CppName(true);
		CppTernaryOperator ternaryOperator=new CppTernaryOperator(true);
		CppAlgebraicExpression algebraicExpression=new CppAlgebraicExpression(true);
		CppNewStatement newStatement=new CppNewStatement(algebraicExpression,name);
		algebraicExpression.generate(this,name, ternaryOperator, newStatement);
		CppBitwiseExpression bitwiseExpression=new CppBitwiseExpression(this,name,newStatement);
		generate(methodsSyntax, algebraicExpression, bitwiseExpression);
		name.generate(null,algebraicExpression,bitwiseExpression);
		ternaryOperator.generate(algebraicExpression);
	}


	public CppMethodEvaluation(String methodsSyntax,boolean notGenerate) {
		super();
	}

	public CppMethodEvaluation(String methodsSyntax,CppAlgebraicExpression algebraicExpression, CppBitwiseExpression bitwiseExpression) {
		super();
		generate(methodsSyntax, algebraicExpression, bitwiseExpression);
	}


	public void generate(String methodsSintax,  CppAlgebraicExpression algebraicExpression, CppBitwiseExpression bitwiseExpression){

		add( new OptionalCode( new CompositeCodePiece( new UniqueKeyword("("),
														new CppCompositeType(),
														new UniqueKeyword(")"))),
								new OptionalCode( new CompositeCodePiece (new BestAlternativeCode(true, new CppName(PieceType.VALUE,algebraicExpression,bitwiseExpression),
																			   					  new CppNewStatement(algebraicExpression, new CppName(PieceType.TYPE,algebraicExpression,bitwiseExpression))),
																					              new UniqueKeyword(methodsSintax))),
				new CodeSequence(true,new CompositeCodePiece(new CppName(algebraicExpression,bitwiseExpression),
				                                             new UniqueKeyword("("),
				                                             new CodeSequence(false, new BestAlternativeCode(true, algebraicExpression,bitwiseExpression),", "),
				                                             new UniqueKeyword(")")), methodsSintax));


		setPieceType(PieceType.CALL);

	}




}
