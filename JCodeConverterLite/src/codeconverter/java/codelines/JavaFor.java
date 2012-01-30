package codeconverter.java.codelines;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.PieceType;
import codeconverter.codepieces.AlgebraicExpression;
import codeconverter.codepieces.AlternativeCode;
import codeconverter.codepieces.BooleanExpression;
import codeconverter.codepieces.CodeSequence;
import codeconverter.codepieces.CompositeCodePiece;
import codeconverter.codepieces.Expression;
import codeconverter.codepieces.Name;
import codeconverter.codepieces.OptionalCode;
import codeconverter.codepieces.UniqueKeyword;

public class JavaFor  extends CodePattern{

	private Expression expressionCloned;
	
	public JavaFor() {
		
		CompositeCodePiece initialization=new CompositeCodePiece(new OptionalCode(new Name(PieceType.TYPE)));
		initialization.add(new Name(PieceType.VARIABLE),new UniqueKeyword("="),new AlgebraicExpression());
		
		CodeSequence update=new CodeSequence(
					new CompositeCodePiece(
							new Name(PieceType.VARIABLE),
							new AlternativeCode(true,
									new UniqueKeyword("++"),
									new UniqueKeyword("--"),
									new UniqueKeyword("-="),
									new UniqueKeyword("+=")
							),
							new OptionalCode(new AlgebraicExpression())
					),", ");
		
		addCodePiece(new UniqueKeyword("for"),new UniqueKeyword("("),
				initialization,
				new UniqueKeyword(";"),
				new BooleanExpression(),
				new UniqueKeyword(";"),
				update,new UniqueKeyword(")"));
		addCodePattern(PatternType.ASSIGNMENT,PatternType.LINE_OF_CODE);
	}
	
}
