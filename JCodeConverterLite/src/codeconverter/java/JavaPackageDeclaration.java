package codeconverter.java;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.PieceType;
import codeconverter.codepieces.CodeSequence;
import codeconverter.codepieces.Name;
import codeconverter.codepieces.UniqueKeyword;

public class JavaPackageDeclaration extends CodePattern{

	public JavaPackageDeclaration() {
		addCodePiece(new UniqueKeyword("package "),new CodeSequence(new Name(PieceType.NAME),"."));
		addCodePattern(PatternType.LIBRARY_DECLARATION);
	}
	
}
