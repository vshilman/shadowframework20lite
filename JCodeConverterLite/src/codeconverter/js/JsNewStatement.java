package codeconverter.js;

import codeconverter.PieceType;
import codeconverter.codepieces.BestAlternativeCode;
import codeconverter.codepieces.CodeSequence;
import codeconverter.codepieces.CompositeCodePiece;
import codeconverter.codepieces.OptionalCode;
import codeconverter.codepieces.UniqueKeyword;

public class JsNewStatement extends CompositeCodePiece{

	public JsNewStatement() {
		add(new UniqueKeyword("new"));
		add(new JsName(PieceType.TYPE));
//		add(new OptionalCode(
//				new CompositeCodePiece(
//						new UniqueKeyword("["),
//						new OptionalCode(new JavaAlgebraicExpression()),
//						new UniqueKeyword("]")
//				)));
		add(new OptionalCode(new CompositeCodePiece(
						new UniqueKeyword("("),
						new CodeSequence(new OptionalCode(
								new BestAlternativeCode(true, new JsAlgebraicExpression(),new JsArrayContent()) ),","),
						new UniqueKeyword(")")
				)));
	}
}
