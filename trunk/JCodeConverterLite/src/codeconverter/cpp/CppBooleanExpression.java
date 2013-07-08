package codeconverter.cpp;

import java.util.Collections;

import codeconverter.PieceType;
import codeconverter.codepieces.CompositeCodePiece;
import codeconverter.codepieces.Expression;
import codeconverter.codepieces.Number;
import codeconverter.codepieces.UniqueKeyword;
import codeconverter.java.JavaName;
import codeconverter.java.JavaNewStatement;

public class CppBooleanExpression extends Expression{

	public static String[] booleanSymbols = { "||", "&&", "==" , "<=" , ">=" , ">" , "<0" ,"<" ,"!=" };

	public CppBooleanExpression() {
		super();
		generate(new CppName(),new CppName(),new CppMethodEvaluation("->"));
	}

	public CppBooleanExpression(CppAlgebraicExpression algebraicExpression) {
		super();
		CppBitwiseExpression bitwiseExpression=new CppBitwiseExpression(true);
		CppMethodEvaluation methodEvaluation=new CppMethodEvaluation("->",algebraicExpression,bitwiseExpression);
		bitwiseExpression.generate(methodEvaluation, new CppName(algebraicExpression,bitwiseExpression), new CppNewStatement(algebraicExpression, new CppCompositeType()));
		generate(new CppName(algebraicExpression,bitwiseExpression), new CppName(algebraicExpression,bitwiseExpression), new CppMethodEvaluation("->",algebraicExpression,bitwiseExpression));
	}



	private void generate (CppName name1, CppName name2, CppMethodEvaluation methodEvaluation){
		name1.setPieceType(PieceType.VARIABLE);
		name1.setPieceType(PieceType.VARIABLE);
		Collections.addAll(this.pieces, name1,new Number(), methodEvaluation);
		this.pieces.add(new CompositeCodePiece(new UniqueKeyword("("),this,new UniqueKeyword(")")));
		this.pieces.add(new CompositeCodePiece(new UniqueKeyword("!"),name2));
		this.pieces.add(new CompositeCodePiece(new UniqueKeyword("!"),new UniqueKeyword("("),this, new UniqueKeyword(")")));
	}



	@Override
	public String[] getExpressionSeparators() {
		return booleanSymbols;
	}





}
