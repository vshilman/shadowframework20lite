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

public class CppBitwiseExpression extends Expression{

	public static String[] bitwiseSymbols={"&","|","^","~","<<",">>",">>>"};


	public CppBitwiseExpression() {
		super();
		CppAlgebraicExpression algebraicExpression=new CppAlgebraicExpression(true);
		CppMethodEvaluation cppMethodEvaluation=new CppMethodEvaluation();
		CppName name=new CppName();
		CppNewStatement newStatement=new CppNewStatement();

	}

	public CppBitwiseExpression(boolean notGenerate) {
		super();
	}


	public void generate (CppMethodEvaluation cppMethod, CppName name, CppNewStatement newStatement){
		name.setPieceType(PieceType.VARIABLE);
		ICodePiece piece=new CompositeCodePiece(new UniqueKeyword("("),
												this,
												new UniqueKeyword(")"));

		Collections.addAll(this.pieces, new CompositeCodePiece(new OptionalCode(new CompositeCodePiece(new UniqueKeyword("("),
																										new CppType(),
																										new UniqueKeyword(")"))),
										new BestAlternativeCode(true,name, piece)),
										new Number(),
										newStatement,
										cppMethod);
	}



	@Override
	public String[] getExpressionSeparators() {
		return bitwiseSymbols;
	}




}
