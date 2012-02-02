package codeconverter.java;

import java.util.Collections;

import codeconverter.ICodePiece;
import codeconverter.codepieces.CompositeCodePiece;
import codeconverter.codepieces.Expression;
import codeconverter.codepieces.Number;
import codeconverter.codepieces.OptionalCode;
import codeconverter.codepieces.UniqueKeyword;

public class JavaAlgebraicExpression extends Expression{

	public static String[] algebraicSymbols={
		"*","+","/","-","++","--"
	};
	
	public JavaAlgebraicExpression() {
		super();
		generate(new JavaMethodEvaluation(".",this));
	}
	
	JavaAlgebraicExpression(JavaMethodEvaluation methodEvaluation) {
		super();
		generate(methodEvaluation);
	}

	public void generate(JavaMethodEvaluation methodEvaluation) {
		ICodePiece piece=new CompositeCodePiece(
				new UniqueKeyword("("),this,new UniqueKeyword(")"));
		Collections.addAll(this.pieces,
		new CompositeCodePiece(new OptionalCode(//casting
					new CompositeCodePiece(new UniqueKeyword("("),new JavaType(),
							new UniqueKeyword(")"))
				),new JavaName()),
				new Number(),piece,methodEvaluation);//
	}


	@Override
	public String[] getExpressionSeparators() {
		return algebraicSymbols;
	}
	
}
