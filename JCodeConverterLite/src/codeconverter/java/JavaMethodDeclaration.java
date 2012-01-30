package codeconverter.java;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.codepieces.CodeSequence;
import codeconverter.codepieces.OptionalCode;
import codeconverter.codepieces.UniqueKeyword;

public class JavaMethodDeclaration extends CodePattern{

	public JavaMethodDeclaration() {
		addCodePiece(new OptionalCode(new UniqueKeyword("@Override")),
				new CodeSequence(new JavaModifier()," "),
				new JavaVariable(),new UniqueKeyword("("),
				new JavaMethodVariables(),
				new UniqueKeyword(")"),
				new OptionalCode(new JavaThrowsDeclaration()));
		addCodePattern(PatternType.METHOD_DECLARATION);
	}

}
