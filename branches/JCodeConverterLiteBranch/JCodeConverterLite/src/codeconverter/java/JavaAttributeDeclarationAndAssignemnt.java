package codeconverter.java;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.codepieces.AlternativeCode;
import codeconverter.codepieces.CodeSequence;
import codeconverter.codepieces.UniqueKeyword;
import codeconverter.java.jogl.JoglMethodEvaluation;

public class JavaAttributeDeclarationAndAssignemnt extends CodePattern {

	public JavaAttributeDeclarationAndAssignemnt() {
		addCodePiece(new CodeSequence(true,new JavaModifier()," "),new JavaVariable(),
				new UniqueKeyword("="),
				new AlternativeCode(true,new JavaNewStatement(),new JavaAlgebraicExpression(),
						new JavaBitwiseExpression(),new JoglMethodEvaluation("."),new JavaMethodEvaluation(".")));
		addCodePattern(PatternType.ATTRIBUTE_DECLARATION,PatternType.ATTRIBUTE_ASSIGNMENT,PatternType.ASSIGNMENT);
	}

}
