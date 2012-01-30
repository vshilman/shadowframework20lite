package codeconverter.java.codelines;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.codepieces.AlgebraicExpression;
import codeconverter.codepieces.Name;
import codeconverter.codepieces.UniqueKeyword;

public class JavaArrayDeclaration extends CodePattern{

	public JavaArrayDeclaration() {
		
		addCodePiece(new Name(),new UniqueKeyword("[]"),new UniqueKeyword("="),new UniqueKeyword("new"),
				new Name(),
				new UniqueKeyword("["),
				new AlgebraicExpression(),
				new UniqueKeyword("]"));
		addCodePattern(PatternType.ASSIGNMENT,PatternType.LINE_OF_CODE);
	}
	
}

