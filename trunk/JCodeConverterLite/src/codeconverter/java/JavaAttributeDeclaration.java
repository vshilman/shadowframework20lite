package codeconverter.java;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.codepieces.CodeSequence;

public class JavaAttributeDeclaration extends CodePattern {

	public JavaAttributeDeclaration() {
		addCodePiece(new CodeSequence(new JavaModifier()," "),new JavaVariable());
		addCodePattern(PatternType.ATTRIBUTE_DECLARATION);
	}
}
