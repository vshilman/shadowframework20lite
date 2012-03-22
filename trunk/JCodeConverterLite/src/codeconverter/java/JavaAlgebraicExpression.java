package codeconverter.java;

import java.util.Collections;

import codeconverter.ICodePiece;
import codeconverter.codepieces.BestAlternativeCode;
import codeconverter.codepieces.CompositeCodePiece;
import codeconverter.codepieces.Expression;
import codeconverter.codepieces.Number;
import codeconverter.codepieces.OptionalCode;
import codeconverter.codepieces.UniqueKeyword;
import codeconverter.java.jogl.JoglMethodEvaluation;

public class JavaAlgebraicExpression extends Expression{

	public static String[] algebraicSymbols={
		"*","+","/","-","++","--"
	};
	
	public JavaAlgebraicExpression() {
		super();
		generate2(new JavaTernaryOperator(this));
	}
	
	public JavaAlgebraicExpression(JavaTernaryOperator ternaryOperator) {
		generate2(ternaryOperator);
	}
	
	public JavaAlgebraicExpression(boolean notGenerate) {
		super();
	}
	
	public JavaAlgebraicExpression(JavaMethodEvaluation javaMethod,JoglMethodEvaluation joglMethod,JavaName name,JavaTernaryOperator ternaryOperator) {
		super();
		generate(javaMethod,joglMethod,name,ternaryOperator);
	}
	
	private void generate2(JavaTernaryOperator ternaryOperator) {
		JavaBitwiseExpression javaBitwiseExpression=new JavaBitwiseExpression(true);
		JavaMethodEvaluation javaMethodEvaluation=new JavaMethodEvaluation(".",this,javaBitwiseExpression);
		JoglMethodEvaluation joglMethodEvaluation=new JoglMethodEvaluation(".",this,javaBitwiseExpression);
		JavaName name=new JavaName(this, javaBitwiseExpression);
		generate(javaMethodEvaluation,joglMethodEvaluation,name,ternaryOperator);
		javaBitwiseExpression.generate(javaMethodEvaluation, joglMethodEvaluation,name);
	}

	public void generate(JavaMethodEvaluation javaMethod,JoglMethodEvaluation joglMethod,JavaName name,JavaTernaryOperator ternaryOperator) {
		ICodePiece piece=new CompositeCodePiece(
				new UniqueKeyword("("),this,new UniqueKeyword(")"));
		Collections.addAll(this.pieces,
		new CompositeCodePiece(new OptionalCode(//casting
					new CompositeCodePiece(new UniqueKeyword("("),new JavaType(),
							new UniqueKeyword(")"))
				),new BestAlternativeCode(true, name,piece)),
				new Number(),ternaryOperator,javaMethod,joglMethod);//
	}


	@Override
	public String[] getExpressionSeparators() {
		return algebraicSymbols;
	}
	
}
