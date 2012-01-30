package codeconverter.java;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.PieceType;
import codeconverter.codepieces.CodeSequence;
import codeconverter.codepieces.Name;
import codeconverter.codepieces.UniqueKeyword;

public class JavaImportDeclaration extends CodePattern {
	
	public JavaImportDeclaration() {
		addCodePiece(new UniqueKeyword("import "),new CodeSequence(new Name(PieceType.NAME),"."));
		addCodePattern(PatternType.LIBRARY_DECLARATION);
	}
	
}
