package codeconverter.js;

import codeconverter.PieceType;
import codeconverter.codepieces.BestAlternativeCode;
import codeconverter.codepieces.CodeSequence;
import codeconverter.codepieces.CompositeCodePiece;
import codeconverter.codepieces.OptionalCode;
import codeconverter.codepieces.UniqueKeyword;
import codeconverter.js.webgl.WebGlMethodEvaluation;


public class JsMethodEvaluation extends CompositeCodePiece{

	public JsMethodEvaluation(String methodsSyntax) {
		super();
		WebGlMethodEvaluation methodEvaluation=new WebGlMethodEvaluation(methodsSyntax,true);
		JsName name=new JsName(true);
		JsTernaryOperator ternaryOperator=new JsTernaryOperator(true);
		JsAlgebraicExpression algebraicExpression=new JsAlgebraicExpression(this,methodEvaluation,name,ternaryOperator);
		JsBitwiseExpression bitwiseExpression=new JsBitwiseExpression(this,methodEvaluation,name);
		generate(methodsSyntax,algebraicExpression,bitwiseExpression);
		methodEvaluation.generate(methodsSyntax, algebraicExpression, bitwiseExpression);
		name.generate(null, algebraicExpression, bitwiseExpression);
		ternaryOperator.generate(algebraicExpression);
	}
	
	public JsMethodEvaluation(String methodsSyntax,boolean notGenerate) {
		super();
	}
	
	public JsMethodEvaluation(String methodsSyntax, JsAlgebraicExpression algebraicExpression, JsBitwiseExpression bitwiseExpression) {
		super();
		generate(methodsSyntax,algebraicExpression,bitwiseExpression);
	}

	public void generate(String methodsSyntax, JsAlgebraicExpression algebraicExpression, JsBitwiseExpression bitwiseExpression) {
		add(
				new OptionalCode(//variable or class Name
						new CompositeCodePiece(
								new BestAlternativeCode(true,
										new JsName(PieceType.VALUE,algebraicExpression,bitwiseExpression),
										new JsNewStatement(algebraicExpression,
												new JsName(PieceType.TYPE, algebraicExpression, bitwiseExpression),
												new JsArrayContent(algebraicExpression,bitwiseExpression))),
								new UniqueKeyword(methodsSyntax)
						)),
				new CodeSequence(true,new CompositeCodePiece(
					new JsName(algebraicExpression,bitwiseExpression),new UniqueKeyword("("),
					new CodeSequence(false,new BestAlternativeCode(true,
							algebraicExpression,bitwiseExpression),", "),
					new UniqueKeyword(")")
				),methodsSyntax)	
				);
		setPieceType(PieceType.CALL);
	}	
}