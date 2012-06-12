package codeconverter.java;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.PieceType;
import codeconverter.codepieces.CodeSequence;
import codeconverter.codepieces.UniqueKeyword;

public class JavaPackageDeclaration extends CodePattern{

	public JavaPackageDeclaration() {
		addCodePiece(new UniqueKeyword("package "),new CodeSequence(new JavaName(PieceType.NAME),"."));
		addPatternType(PatternType.LIBRARY_DECLARATION);
	}
	
}
