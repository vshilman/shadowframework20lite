package codeconverter.java.codelines;

import codeconverter.CodePattern;
import codeconverter.Expression;
import codeconverter.ICodeElement;
import codeconverter.OptionalCode;
import codeconverter.PatternType;
import codeconverter.StaticKeyword;
import codeconverter.UninterpretedEvaluation;

public class JavaIf  extends CodePattern{

	private Expression expressionCloned;
	
	public JavaIf() {
		super("assignment");
		
		addCodePiece(new StaticKeyword("if"),new StaticKeyword("("),
				new UninterpretedEvaluation(new StaticKeyword("{")),
				new OptionalCode(new StaticKeyword("{")));
		addCodePattern(PatternType.ASSIGNMENT,PatternType.LINE_OF_CODE);
	}
	
	@Override
	public ICodeElement cloneCodePiece() {
		JavaIf pattern=new JavaIf();
		return pattern;
	}
	
	@Override
	public String toString() {
		return "\t	if("+"){";
	}
}
