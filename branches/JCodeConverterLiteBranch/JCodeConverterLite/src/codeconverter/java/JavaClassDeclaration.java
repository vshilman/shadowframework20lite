package codeconverter.java;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.PieceType;
import codeconverter.codepieces.CodeSequence;
import codeconverter.codepieces.Name;
import codeconverter.codepieces.OptionalCode;
import codeconverter.codepieces.UniqueKeyword;

public class JavaClassDeclaration extends CodePattern{

	public JavaClassDeclaration() {
		addCodePiece(new CodeSequence(new JavaModifier()," "),
				new UniqueKeyword("class"),new Name(PieceType.NAME),
				new OptionalCode(new JavaExtendsDeclaration()),
				new OptionalCode(new JavaImplementsDeclaration()));
		addCodePattern(PatternType.CLASS_DECLARATION);
	}

}
