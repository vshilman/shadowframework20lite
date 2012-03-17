package codeconverter.js.webgl;

import codeconverter.PieceType;
import codeconverter.codepieces.BestAlternativeCode;
import codeconverter.codepieces.CodeSequence;
import codeconverter.codepieces.CompositeCodePiece;
import codeconverter.codepieces.Name;
import codeconverter.codepieces.UniqueKeyword;
import codeconverter.js.JsAlgebraicExpression;
import codeconverter.js.JsBitwiseExpression;
import codeconverter.js.JsMethodEvaluation;
import codeconverter.js.JsName;

public class WebGlMethodEvaluation extends CompositeCodePiece {

	public WebGlMethodEvaluation(String methodsSyntax) {
		super();
		JsMethodEvaluation methodEvaluation=new JsMethodEvaluation(methodsSyntax,true);
		JsName name=new JsName(true);
		JsAlgebraicExpression algebraicExpression=new JsAlgebraicExpression(methodEvaluation,this,name);
		JsBitwiseExpression bitwiseExpression=new JsBitwiseExpression(methodEvaluation,this,name);
		generate(methodsSyntax,algebraicExpression,bitwiseExpression);
		methodEvaluation.generate(methodsSyntax, algebraicExpression, bitwiseExpression);
		name.generate(null, algebraicExpression, bitwiseExpression);
	}
	
	public WebGlMethodEvaluation(String methodsSyntax,boolean notGenerate) {
		super();
	}
	
	public WebGlMethodEvaluation(String methodsSyntax, JsAlgebraicExpression algebraicExpression, JsBitwiseExpression bitwiseExpression) {
		super();
		generate(methodsSyntax,algebraicExpression,bitwiseExpression);
	}

	public void generate(String methodsSyntax, JsAlgebraicExpression algebraicExpression, JsBitwiseExpression bitwiseExpression) {
		add(	
				new UniqueKeyword("gl."),new Name(PieceType.NAME),new UniqueKeyword("("),
				new CodeSequence(false,new BestAlternativeCode(true,
						algebraicExpression,bitwiseExpression),", "),
				new UniqueKeyword(")")
			);
		setPieceType(PieceType.OPENGL_CALL);
	}
}
