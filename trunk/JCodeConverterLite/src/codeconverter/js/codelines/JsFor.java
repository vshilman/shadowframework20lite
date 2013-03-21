package codeconverter.js.codelines;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.PieceType;
import codeconverter.codepieces.AlternativeCode;
import codeconverter.codepieces.CodeSequence;
import codeconverter.codepieces.CompositeCodePiece;
import codeconverter.codepieces.KeywordSet;
import codeconverter.codepieces.OptionalCode;
import codeconverter.codepieces.UniqueKeyword;
import codeconverter.js.JsAlgebraicExpression;
import codeconverter.js.JsName;
import codeconverter.js.JsVariable;

public class JsFor  extends CodePattern{

	public JsFor() {
		CompositeCodePiece initialization=new CompositeCodePiece(
				new AlternativeCode(true,new JsVariable(), new JsName(PieceType.NAME)),
				new UniqueKeyword("="),new JsAlgebraicExpression());
		
		CodeSequence update=new CodeSequence(
					new CompositeCodePiece(
							new JsName(PieceType.VARIABLE),
							new AlternativeCode(true,
									new UniqueKeyword("++"),
									new UniqueKeyword("--"),
									new UniqueKeyword("-="),
									new UniqueKeyword("+=")
							),
							new OptionalCode(new JsAlgebraicExpression())
					),", ");
		
		addCodePiece(new UniqueKeyword("for"),new UniqueKeyword("("),
				initialization,
				new UniqueKeyword(";"),
				new JsName(PieceType.VALUE),
				new KeywordSet("<=",">=",">","<"),
				new JsAlgebraicExpression(),
				new UniqueKeyword(";"),
				update,new UniqueKeyword(")"));
		addPatternType(PatternType.FOR,PatternType.ASSIGNMENT,PatternType.LINE_OF_CODE);
	}
	
}
