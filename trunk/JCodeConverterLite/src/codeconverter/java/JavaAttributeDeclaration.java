package codeconverter.java;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.codepieces.CodeSequence;
import codeconverter.codepieces.CompositeCodePiece;
import codeconverter.codepieces.Number;
import codeconverter.codepieces.OptionalCode;
import codeconverter.codepieces.UniqueKeyword;

public class JavaAttributeDeclaration extends CodePattern {

	public JavaAttributeDeclaration() {
		addCodePiece(new CodeSequence(true, new JavaModifier(), " "), new JavaType(), new CodeSequence(
				new CompositeCodePiece(new JavaName(), new OptionalCode(new CompositeCodePiece(
						new UniqueKeyword("="), new Number()))), ","));
		addPatternType(PatternType.ATTRIBUTE_DECLARATION);
	}
}
