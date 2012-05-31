package codeconverter.js;

import java.util.Collections;

import codeconverter.ICodePiece;
import codeconverter.PieceType;
import codeconverter.codepieces.CompositeCodePiece;
import codeconverter.codepieces.Expression;
import codeconverter.codepieces.Number;
import codeconverter.codepieces.UniqueKeyword;
import codeconverter.js.webgl.WebGlMethodEvaluation;

public class JsAlgebraicExpression extends Expression{

	public static String[] algebraicSymbols={
		"*","+","/","-","++","--"
	};
	
	public JsAlgebraicExpression() {
		super();
		generate2(new JsTernaryOperator(this));
	}
	
	public JsAlgebraicExpression(JsTernaryOperator ternaryOperator) {
		generate2(ternaryOperator);
	}
	
	public JsAlgebraicExpression(boolean notGenerate) {
		super();
	}

	public JsAlgebraicExpression(JsMethodEvaluation jsMethod, WebGlMethodEvaluation webGlMethod, JsName name,
			JsTernaryOperator ternaryOperator, JsNewStatement newStatement) {
		super();
		generate(jsMethod,webGlMethod,name,ternaryOperator,newStatement);
	}
	
	private void generate2(JsTernaryOperator ternaryOperator) {
		JsBitwiseExpression jsBitwiseExpression = new JsBitwiseExpression(true);
		JsMethodEvaluation jsMethodEvaluation = new JsMethodEvaluation(".", this, jsBitwiseExpression);
		WebGlMethodEvaluation webGlMethodEvaluation = new WebGlMethodEvaluation(".", this,
				jsBitwiseExpression);
		JsName name = new JsName(this, jsBitwiseExpression);
		JsNewStatement newStatement = new JsNewStatement(this, name, new JsArrayContent(
				this, jsBitwiseExpression));
		generate(jsMethodEvaluation, webGlMethodEvaluation, name, ternaryOperator,newStatement);
		jsBitwiseExpression.generate(jsMethodEvaluation, webGlMethodEvaluation,name,newStatement);
	}

	public void generate(JsMethodEvaluation jsMethod, WebGlMethodEvaluation webGlMethod, JsName name,
			JsTernaryOperator ternaryOperator,JsNewStatement newStatement) {
		name.setPieceType(PieceType.VARIABLE);
		ICodePiece piece=new CompositeCodePiece(
				new UniqueKeyword("("),this,new UniqueKeyword(")"));
		Collections.addAll(this.pieces,name,
				new Number(),newStatement,ternaryOperator,piece,jsMethod,webGlMethod);//
	}


	@Override
	public String[] getExpressionSeparators() {
		return algebraicSymbols;
	}
	
}
