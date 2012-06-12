package codeconverter.java;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.codepieces.UniqueKeyword;

public class JavaImportDeclaration extends CodePattern {
	
	public JavaImportDeclaration() {
		addCodePiece(new UniqueKeyword("import"),new JavaName());
		addPatternType(PatternType.LIBRARY_DECLARATION);
	}
	
}
