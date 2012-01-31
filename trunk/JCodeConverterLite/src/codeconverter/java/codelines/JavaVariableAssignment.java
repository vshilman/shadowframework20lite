package codeconverter.java.codelines;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.PieceType;
import codeconverter.codepieces.AlternativeCode;
import codeconverter.codepieces.UniqueKeyword;
import codeconverter.java.JavaAlgebraicExpression;
import codeconverter.java.JavaMethodEvaluation;
import codeconverter.java.JavaName;
import codeconverter.java.JavaNewStatement;

public class JavaVariableAssignment extends CodePattern{

	public JavaVariableAssignment() {
		addCodePiece(new JavaName(PieceType.NAME),new UniqueKeyword("="),
				new AlternativeCode(true, new JavaMethodEvaluation("."),
						new JavaAlgebraicExpression(),new JavaNewStatement()));
		addCodePattern(PatternType.LINE_OF_CODE);
	}
}
