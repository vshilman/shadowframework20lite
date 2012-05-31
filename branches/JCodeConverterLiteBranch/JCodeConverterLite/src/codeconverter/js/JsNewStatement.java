package codeconverter.js;

import codeconverter.PieceType;
import codeconverter.codepieces.BestAlternativeCode;
import codeconverter.codepieces.CodeSequence;
import codeconverter.codepieces.CompositeCodePiece;
import codeconverter.codepieces.OptionalCode;
import codeconverter.codepieces.UniqueKeyword;

public class JsNewStatement extends CompositeCodePiece{

	public JsNewStatement() {
		JsAlgebraicExpression algebraicExpression =new JsAlgebraicExpression();
		generate(new JsAlgebraicExpression(),new JsName(PieceType.TYPE),new JsArrayContent(algebraicExpression,new JsBitwiseExpression()));
	}
	
	public JsNewStatement(JsAlgebraicExpression algebraicExpression,JsName name,JsArrayContent arrayContent){
		generate(algebraicExpression,name,arrayContent);
	}

	private void generate(JsAlgebraicExpression algebraicExpression,JsName name,JsArrayContent arrayContent) {
		setPieceType(PieceType.NEW_STATEMENT);
		add(new UniqueKeyword("new "));
		add(name);
//		add(new OptionalCode(
//				new CompositeCodePiece(
//						new UniqueKeyword("["),
//						new OptionalCode(new JavaAlgebraicExpression()),
//						new UniqueKeyword("]")
//				)));
		add(new OptionalCode(new CompositeCodePiece(
						new UniqueKeyword("("),
						new CodeSequence(new OptionalCode(
								new BestAlternativeCode(true, algebraicExpression,arrayContent) ),","),
						new UniqueKeyword(")")
				)));
	}
}
