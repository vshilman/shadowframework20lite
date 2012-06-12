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
import codeconverter.java.jogl.JoglConstant;
import codeconverter.java.jogl.JoglMethodEvaluation;

public class JavaBitwiseExpression extends Expression{

	public static String[] bitwiseSymbols={
		"&","|","^","~","<<",">>",">>>"
	};
	
	public JavaBitwiseExpression() {
		super();
		JavaAlgebraicExpression algebraicExpression = new JavaAlgebraicExpression(true);
		JavaMethodEvaluation javaMethodEvaluation = new JavaMethodEvaluation(".", algebraicExpression, this);
		JoglMethodEvaluation joglMethodEvaluation = new JoglMethodEvaluation(".", algebraicExpression, this);
		JavaName name = new JavaName(algebraicExpression, this);
		JavaNewStatement newStatement=new JavaNewStatement(algebraicExpression, name);
		generate(javaMethodEvaluation, joglMethodEvaluation, name,newStatement);
		algebraicExpression.generate(javaMethodEvaluation, joglMethodEvaluation, name,
				new JavaTernaryOperator(algebraicExpression),newStatement);
	}
	
	public JavaBitwiseExpression(boolean notGenerate) {
		super();
	}
	
	public JavaBitwiseExpression(JavaMethodEvaluation javaMethod, JoglMethodEvaluation joglMethod,
			JavaName name, JavaNewStatement newStatement) {
		super();
		generate(javaMethod, joglMethod, name, newStatement);
	}

	public void generate(JavaMethodEvaluation javaMethod, JoglMethodEvaluation joglMethod, JavaName name,
			JavaNewStatement newStatement) {
		name.setPieceType(PieceType.VARIABLE);
		ICodePiece piece=new CompositeCodePiece(
				new UniqueKeyword("("),this,new UniqueKeyword(")"));
		Collections.addAll(this.pieces,
		new CompositeCodePiece(new OptionalCode(//casting
					new CompositeCodePiece(new UniqueKeyword("("),new JavaType(),
							new UniqueKeyword(")"))
				),new BestAlternativeCode(true, name,piece)),
				new Number(),newStatement,new JoglConstant(),javaMethod,joglMethod);//
	}


	@Override
	public String[] getExpressionSeparators() {
		return bitwiseSymbols;
	}
	
}
