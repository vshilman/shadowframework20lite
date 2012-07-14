package codeconverter.java;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.codepieces.BestAlternativeCode;
import codeconverter.codepieces.CodeSequence;
import codeconverter.codepieces.UniqueKeyword;
import codeconverter.java.jogl.JoglMethodEvaluation;

public class JavaAttributeDeclarationAndAssignemnt extends CodePattern {

	public JavaAttributeDeclarationAndAssignemnt() {
		addCodePiece(new CodeSequence(true, new JavaModifier(), " "), new JavaVariable(), new UniqueKeyword(
				"="), new BestAlternativeCode(true, new JavaAlgebraicExpression(),
				new JavaBitwiseExpression(), new JavaNewStatement(), new JoglMethodEvaluation("."), new JavaMethodEvaluation("."),
				new JavaArrayContent(), new JavaArrayDeclaration()));
		addPatternType(PatternType.ATTRIBUTE_DECLARATION, PatternType.ATTRIBUTE_ASSIGNMENT,
				PatternType.ASSIGNMENT);
	}

}
