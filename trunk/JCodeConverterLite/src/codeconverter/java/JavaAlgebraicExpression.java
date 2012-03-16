package codeconverter.java;

import java.util.Collections;

import codeconverter.ICodePiece;
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
		JavaBitwiseExpression javaBitwiseExpression=new JavaBitwiseExpression(true);
		JavaMethodEvaluation javaMethodEvaluation=new JavaMethodEvaluation(".",this,javaBitwiseExpression);
		JoglMethodEvaluation joglMethodEvaluation=new JoglMethodEvaluation(".",this,javaBitwiseExpression);
		JavaName name=new JavaName(this, javaBitwiseExpression);
		generate(javaMethodEvaluation,joglMethodEvaluation,name);
		javaBitwiseExpression.generate(javaMethodEvaluation, joglMethodEvaluation,name);
	}
	
	public JavaAlgebraicExpression(boolean notGenerate) {
		super();
	}
	
	public JavaAlgebraicExpression(JavaMethodEvaluation javaMethod,JoglMethodEvaluation joglMethod,JavaName name) {
		super();
		generate(javaMethod,joglMethod,name);
	}

	public void generate(JavaMethodEvaluation javaMethod,JoglMethodEvaluation joglMethod,JavaName name) {
		ICodePiece piece=new CompositeCodePiece(
				new UniqueKeyword("("),this,new UniqueKeyword(")"));
		Collections.addAll(this.pieces,
		new CompositeCodePiece(new OptionalCode(//casting
					new CompositeCodePiece(new UniqueKeyword("("),new JavaType(),
							new UniqueKeyword(")"))
				),name),
				new Number(),piece,javaMethod,joglMethod);//
	}


	@Override
	public String[] getExpressionSeparators() {
		return algebraicSymbols;
	}
	
}
