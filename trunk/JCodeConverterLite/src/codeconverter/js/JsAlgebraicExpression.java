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
	
	public JsAlgebraicExpression(JsMethodEvaluation jsMethod,WebGlMethodEvaluation webGlMethod,JsName name,JsTernaryOperator ternaryOperator) {
		super();
		generate(jsMethod,webGlMethod,name,ternaryOperator);
	}
	
	private void generate2(JsTernaryOperator ternaryOperator) {
		JsBitwiseExpression jsBitwiseExpression=new JsBitwiseExpression(true);
		JsMethodEvaluation jsMethodEvaluation=new JsMethodEvaluation(".",this,jsBitwiseExpression);
		WebGlMethodEvaluation webGlMethodEvaluation=new WebGlMethodEvaluation(".",this,jsBitwiseExpression);
		JsName name=new JsName(this, jsBitwiseExpression);
		generate(jsMethodEvaluation,webGlMethodEvaluation,name,ternaryOperator);
		jsBitwiseExpression.generate(jsMethodEvaluation, webGlMethodEvaluation,name);
	}

	public void generate(JsMethodEvaluation jsMethod,WebGlMethodEvaluation webGlMethod,JsName name,JsTernaryOperator ternaryOperator) {
		name.setPieceType(PieceType.VARIABLE);
		ICodePiece piece=new CompositeCodePiece(
				new UniqueKeyword("("),this,new UniqueKeyword(")"));
		Collections.addAll(this.pieces,name,
				new Number(),ternaryOperator,piece,jsMethod,webGlMethod);//
	}


	@Override
	public String[] getExpressionSeparators() {
		return algebraicSymbols;
	}
	
}
