package codeconverter.java;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.codepieces.AlternativeCode;
import codeconverter.codepieces.CodeSequence;
import codeconverter.codepieces.UniqueKeyword;

public class JavaAttributeDeclarationAndAssignemnt extends CodePattern {

	public JavaAttributeDeclarationAndAssignemnt() {
		addCodePiece(new CodeSequence(new JavaModifier()," "),new JavaVariable(),
				new UniqueKeyword("="),
				new AlternativeCode(true,new JavaNewStatement(),new JavaAlgebraicExpression(),
						new JavaMethodEvaluation(".")));
		addCodePattern(PatternType.ATTRIBUTE_DECLARATION);
	}

}
