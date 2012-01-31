package codeconverter.java.codelines;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.codepieces.UniqueKeyword;
import codeconverter.java.JavaName;
import codeconverter.java.JavaNewStatement;

public class JavaArrayDeclaration extends CodePattern{

	public JavaArrayDeclaration() {
		
		addCodePiece(new JavaName(),new UniqueKeyword("[]"),new UniqueKeyword("="),
				new JavaNewStatement());
		addCodePattern(PatternType.ASSIGNMENT,PatternType.LINE_OF_CODE);
	}
	
}

