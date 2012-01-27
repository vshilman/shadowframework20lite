package codeconverter.java.codelines;

import codeconverter.CodePattern;
import codeconverter.Expression;
import codeconverter.ICodeElement;
import codeconverter.OptionalCode;
import codeconverter.PatternType;
import codeconverter.StaticKeyword;
import codeconverter.UninterpretedEvaluation;

public class JavaElse  extends CodePattern{

	private Expression expressionCloned;
	
	public JavaElse() {
		super("assignment");
		
		addCodePiece(new OptionalCode(new StaticKeyword("}")),new StaticKeyword("else"),
				new OptionalCode(new StaticKeyword("if")),
				new OptionalCode(new StaticKeyword("(")),
				new UninterpretedEvaluation(new StaticKeyword(")")),
				new OptionalCode(new StaticKeyword(")")));
		addCodePattern(PatternType.ASSIGNMENT,PatternType.LINE_OF_CODE);
	}
	
	@Override
	public ICodeElement cloneCodePiece() {
		JavaElse pattern=new JavaElse();
		return pattern;
	}
	
	@Override
	public String toString() {
		return "\t	if("+"){";
	}
}
