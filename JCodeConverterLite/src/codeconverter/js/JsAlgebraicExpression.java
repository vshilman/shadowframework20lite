package codeconverter.js;

import java.util.Collections;

import codeconverter.ICodePiece;
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
		JsBitwiseExpression bitwiseExpression=new JsBitwiseExpression(true);
		JsMethodEvaluation jsMethodEvaluation=new JsMethodEvaluation(".",this,bitwiseExpression);
		WebGlMethodEvaluation webGlMethodEvaluation=new WebGlMethodEvaluation(".",this,bitwiseExpression);
		JsName name=new JsName(this, bitwiseExpression);
		generate(jsMethodEvaluation,webGlMethodEvaluation,name);
		bitwiseExpression.generate(jsMethodEvaluation, webGlMethodEvaluation,name);
	}
	
	public JsAlgebraicExpression(boolean notGenerate) {
		super();
	}
	
	public JsAlgebraicExpression(JsMethodEvaluation jsMethod,WebGlMethodEvaluation webGlMethod,JsName name) {
		super();
		generate(jsMethod,webGlMethod,name);
	}

	public void generate(JsMethodEvaluation jsMethod,WebGlMethodEvaluation webGlMethod,JsName name) {
		ICodePiece piece=new CompositeCodePiece(
				new UniqueKeyword("("),this,new UniqueKeyword(")"));
		Collections.addAll(this.pieces,name,
				new Number(),piece,jsMethod,webGlMethod);//
	}


	@Override
	public String[] getExpressionSeparators() {
		return algebraicSymbols;
	}
	
}
