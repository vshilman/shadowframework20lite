package codeconverter.java;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.PieceType;
import codeconverter.codepieces.CodeSequence;
import codeconverter.codepieces.Name;
import codeconverter.codepieces.OptionalCode;
import codeconverter.codepieces.UniqueKeyword;

public class JavaConstructorDeclaration extends CodePattern {
	
	public JavaConstructorDeclaration() {
		addCodePiece(new CodeSequence(new JavaModifier()," "),new Name(PieceType.NAME),
				new UniqueKeyword("("),
				new JavaMethodVariables(),new UniqueKeyword(")"),
				new OptionalCode(new JavaThrowsDeclaration()));
		addCodePattern(PatternType.CONSTRUCTOR_DECLARATION);
	}
	
}
