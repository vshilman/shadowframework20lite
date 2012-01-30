package codeconverter.java;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.PieceType;
import codeconverter.codepieces.CodeSequence;
import codeconverter.codepieces.Name;
import codeconverter.codepieces.OptionalCode;
import codeconverter.codepieces.UniqueKeyword;

public class JavaInterfaceDeclaration  extends CodePattern{

	
	public JavaInterfaceDeclaration() {
		addCodePiece(new CodeSequence(new JavaModifier(),""),
				new UniqueKeyword("interface"),new Name(PieceType.NAME),
				new OptionalCode(new JavaExtendsDeclaration()));
		addCodePattern(PatternType.CLASS_DECLARATION);
	}
		
}
