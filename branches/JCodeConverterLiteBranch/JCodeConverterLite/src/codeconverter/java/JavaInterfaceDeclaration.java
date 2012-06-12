package codeconverter.java;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.codepieces.CodeSequence;
import codeconverter.codepieces.OptionalCode;
import codeconverter.codepieces.UniqueKeyword;

public class JavaInterfaceDeclaration  extends CodePattern{

	public JavaInterfaceDeclaration() {
		addCodePiece(new CodeSequence(new JavaModifier(),""),
				new UniqueKeyword("interface"),new JavaName(),
				new OptionalCode(new JavaExtendsDeclaration()));
		addPatternType(PatternType.CLASS_DECLARATION);
	}
		
}
