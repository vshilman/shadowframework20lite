package codeconverter.java.codelines;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.codepieces.BestAlternativeCode;
import codeconverter.codepieces.UniqueKeyword;
import codeconverter.java.JavaAlgebraicExpression;
import codeconverter.java.JavaArrayContent;
import codeconverter.java.JavaArrayDeclaration;
import codeconverter.java.JavaBitwiseExpression;
import codeconverter.java.JavaMethodEvaluation;
import codeconverter.java.JavaNewStatement;
import codeconverter.java.JavaVariable;
import codeconverter.java.jogl.JoglMethodEvaluation;

public class JavaVariableDeclarationAndAssignment extends CodePattern {

	public JavaVariableDeclarationAndAssignment() {
		addCodePiece(new JavaVariable(), new UniqueKeyword("="), new BestAlternativeCode(true,
				new JoglMethodEvaluation("."), new JavaAlgebraicExpression(), new JavaBitwiseExpression(),
				new JavaNewStatement(), new JavaMethodEvaluation("."), new JavaArrayContent(),
				new JavaArrayDeclaration()));
		addPatternType(PatternType.VARIABLE_DECLARATION, PatternType.VARIABLE_ASSIGNMENT,
				PatternType.ASSIGNMENT, PatternType.LINE_OF_CODE);
	}
}
