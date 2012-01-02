package codeconverter.java.codelines;

import codeconverter.CodePattern;
import codeconverter.Expression;
import codeconverter.ICodeElement;
import codeconverter.OptionalCode;
import codeconverter.PatternType;
import codeconverter.StaticKeyword;
import codeconverter.UninterpretedEvaluation;
import codeconverter.UninterpretedEvaluationOrAssignment;

public class JavaFor  extends CodePattern{

	private Expression expressionCloned;
	
	public JavaFor() {
		super("assignment");
		
		addCodePiece(new StaticKeyword("for"),new StaticKeyword("("),
				new UninterpretedEvaluationOrAssignment(new StaticKeyword(";")),
				new StaticKeyword(";"),
				new UninterpretedEvaluation(new StaticKeyword(";")),
				new StaticKeyword(";"),
				new UninterpretedEvaluation(new StaticKeyword("{")),
				new StaticKeyword("{"));
		addCodePattern(PatternType.ASSIGNMENT,PatternType.LINE_OF_CODE);
	}
	
	@Override
	public ICodeElement cloneCodePiece() {
		JavaFor pattern=new JavaFor();
		return pattern;
	}
	
	@Override
	public String toString() {
		return "\t	for("+"){";
	}
}
