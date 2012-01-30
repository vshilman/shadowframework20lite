package codeconverter.java;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.codepieces.AlgebraicExpression;
import codeconverter.codepieces.CodeSequence;
import codeconverter.codepieces.UniqueKeyword;

public class JavaAttributeDeclarationAndAssignemnt extends CodePattern {

	public JavaAttributeDeclarationAndAssignemnt() {
		addCodePiece(new CodeSequence(new JavaModifier()," "),new JavaVariable(),
				new UniqueKeyword("="),
				new AlgebraicExpression());
		addCodePattern(PatternType.ATTRIBUTE_DECLARATION);
	}

}
