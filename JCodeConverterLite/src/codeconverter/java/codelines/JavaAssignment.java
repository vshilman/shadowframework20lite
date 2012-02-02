package codeconverter.java.codelines;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.PieceType;
import codeconverter.codepieces.CodeSequence;
import codeconverter.codepieces.KeywordSet;
import codeconverter.codepieces.OptionalCode;
import codeconverter.codepieces.UniqueKeyword;
import codeconverter.java.JavaAlgebraicExpression;
import codeconverter.java.JavaName;

public class JavaAssignment  extends CodePattern{

	public JavaAssignment() {
		addCodePiece(new JavaName(PieceType.VARIABLE),
				new OptionalCode(new KeywordSet("+","*","-")),new UniqueKeyword("="),
				new CodeSequence(new JavaAlgebraicExpression(),".") );
		addCodePattern(PatternType.ASSIGNMENT,PatternType.LINE_OF_CODE);
	}
	
}
