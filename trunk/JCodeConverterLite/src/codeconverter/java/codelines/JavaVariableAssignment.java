package codeconverter.java.codelines;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.PieceType;
import codeconverter.codepieces.BestAlternativeCode;
import codeconverter.codepieces.UniqueKeyword;
import codeconverter.java.JavaAlgebraicExpression;
import codeconverter.java.JavaBitwiseExpression;
import codeconverter.java.JavaMethodEvaluation;
import codeconverter.java.JavaName;
import codeconverter.java.JavaNewStatement;
import codeconverter.java.jogl.JoglMethodEvaluation;

public class JavaVariableAssignment extends CodePattern{

	public JavaVariableAssignment() {
		addCodePiece(new JavaName(PieceType.NAME),new UniqueKeyword("="),
				new BestAlternativeCode(true,new JoglMethodEvaluation("."), new JavaMethodEvaluation("."),
						new JavaAlgebraicExpression(),new JavaBitwiseExpression(),new JavaNewStatement()));
		addCodePattern(PatternType.VARIABLE_ASSIGNMENT,PatternType.ASSIGNMENT,PatternType.LINE_OF_CODE);
	}
}
