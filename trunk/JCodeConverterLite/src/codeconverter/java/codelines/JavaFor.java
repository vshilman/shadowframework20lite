package codeconverter.java.codelines;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.PieceType;
import codeconverter.codepieces.AlternativeCode;
import codeconverter.codepieces.CodeSequence;
import codeconverter.codepieces.CompositeCodePiece;
import codeconverter.codepieces.KeywordSet;
import codeconverter.codepieces.Name;
import codeconverter.codepieces.OptionalCode;
import codeconverter.codepieces.UniqueKeyword;
import codeconverter.java.JavaAlgebraicExpression;
import codeconverter.java.JavaName;

public class JavaFor  extends CodePattern{

	public JavaFor() {
		
		CompositeCodePiece initialization=new CompositeCodePiece(new OptionalCode(new JavaName(PieceType.TYPE)));
		initialization.add(new JavaName(PieceType.VARIABLE),new UniqueKeyword("="),new JavaAlgebraicExpression());
		
		CodeSequence update=new CodeSequence(
					new CompositeCodePiece(
							new JavaName(PieceType.VARIABLE),
							new AlternativeCode(true,
									new UniqueKeyword("++"),
									new UniqueKeyword("--"),
									new UniqueKeyword("-="),
									new UniqueKeyword("+=")
							),
							new OptionalCode(new JavaAlgebraicExpression())
					),", ");
		
		addCodePiece(new UniqueKeyword("for"),new UniqueKeyword("("),
				initialization,
				new UniqueKeyword(";"),
				new JavaName(PieceType.VALUE),
				new KeywordSet("<=",">=",">","<"),
				new JavaAlgebraicExpression(),
				new UniqueKeyword(";"),
				update,new UniqueKeyword(")"));
		addCodePattern(PatternType.ASSIGNMENT,PatternType.LINE_OF_CODE);
	}
	
}