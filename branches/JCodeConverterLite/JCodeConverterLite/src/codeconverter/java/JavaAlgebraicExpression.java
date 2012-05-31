package codeconverter.java;

import java.util.Collections;

import codeconverter.ICodePiece;
import codeconverter.PieceType;
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
	
	public JavaAlgebraicExpression(JavaMethodEvaluation javaMethod,JoglMethodEvaluation joglMethod,JavaName name,JavaTernaryOperator ternaryOperator,JavaNewStatement newStatement) {
		super();
		generate(javaMethod,joglMethod,name,ternaryOperator,newStatement);
	}
	
	private void generate2(JavaTernaryOperator ternaryOperator) {
		JavaBitwiseExpression javaBitwiseExpression=new JavaBitwiseExpression(true);
		JavaMethodEvaluation javaMethodEvaluation=new JavaMethodEvaluation(".",this,javaBitwiseExpression);
		JoglMethodEvaluation joglMethodEvaluation=new JoglMethodEvaluation(".",this,javaBitwiseExpression);
		JavaName name=new JavaName(this, javaBitwiseExpression);
		JavaNewStatement newStatement=new JavaNewStatement(this, name);
		generate(javaMethodEvaluation,joglMethodEvaluation,name,ternaryOperator,newStatement);
		javaBitwiseExpression.generate(javaMethodEvaluation, joglMethodEvaluation,name,newStatement);
	}

	public void generate(JavaMethodEvaluation javaMethod, JoglMethodEvaluation joglMethod, JavaName name,
			JavaTernaryOperator ternaryOperator,JavaNewStatement newStatement) {
		name.setPieceType(PieceType.VARIABLE);
		ICodePiece piece=new CompositeCodePiece(
				new UniqueKeyword("("),this,new UniqueKeyword(")"));
		Collections.addAll(this.pieces,
		new CompositeCodePiece(new OptionalCode(//casting
					new CompositeCodePiece(new UniqueKeyword("("),new JavaType(),
							new UniqueKeyword(")"))
				),new BestAlternativeCode(true, name,piece)),
				new Number(),newStatement,ternaryOperator,javaMethod,joglMethod);//
	}


	@Override
	public String[] getExpressionSeparators() {
		return algebraicSymbols;
	}
	
}
