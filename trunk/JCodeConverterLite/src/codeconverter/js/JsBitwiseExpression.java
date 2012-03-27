package codeconverter.js;

import java.util.Collections;

import codeconverter.ICodePiece;
import codeconverter.PieceType;
import codeconverter.codepieces.CompositeCodePiece;
import codeconverter.codepieces.Expression;
import codeconverter.codepieces.Number;
import codeconverter.codepieces.UniqueKeyword;
import codeconverter.js.webgl.WebGlConstant;
import codeconverter.js.webgl.WebGlMethodEvaluation;

public class JsBitwiseExpression extends Expression{

	public static String[] algebraicSymbols={
		"&","|","^","~","<<",">>",">>>"
	};
	
	public JsBitwiseExpression() {
		super();
		JsAlgebraicExpression algebraicExpression=new JsAlgebraicExpression(true);
		JsMethodEvaluation jsMethodEvaluation=new JsMethodEvaluation(".",algebraicExpression,this);
		WebGlMethodEvaluation webGlMethodEvaluation=new WebGlMethodEvaluation(".",algebraicExpression,this);
		JsName name=new JsName(algebraicExpression, this);
		JsNewStatement newStatement = new JsNewStatement(algebraicExpression, name, new JsArrayContent(
				algebraicExpression, this));
		generate(jsMethodEvaluation,webGlMethodEvaluation,name,newStatement);
		algebraicExpression.generate(jsMethodEvaluation, webGlMethodEvaluation,name,new JsTernaryOperator(algebraicExpression),newStatement);
	}
	
	public JsBitwiseExpression(boolean notGenerate) {
		super();
	}
	
	public JsBitwiseExpression(JsMethodEvaluation jsMethod,WebGlMethodEvaluation webGlMethod,JsName name,JsNewStatement newStatement) {
		super();
		generate(jsMethod,webGlMethod,name,newStatement);
	}

	public void generate(JsMethodEvaluation jsMethod, WebGlMethodEvaluation webGlMethod, JsName name,
			JsNewStatement newStatement) {
		name.setPieceType(PieceType.VARIABLE);
		ICodePiece piece=new CompositeCodePiece(
				new UniqueKeyword("("),this,new UniqueKeyword(")"));
		Collections.addAll(this.pieces,name,
				new Number(),newStatement,piece,new WebGlConstant(),jsMethod,webGlMethod);//
	}


	@Override
	public String[] getExpressionSeparators() {
		return algebraicSymbols;
	}
	
}
