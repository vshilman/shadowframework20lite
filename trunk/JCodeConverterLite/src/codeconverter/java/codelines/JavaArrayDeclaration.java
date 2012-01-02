package codeconverter.java.codelines;

import codeconverter.CodePattern;
import codeconverter.ICodeElement;
import codeconverter.PatternType;
import codeconverter.StaticKeyword;
import codeconverter.UninterpretedEvaluation;
import codeconverter.java.JavaName;

public class JavaArrayDeclaration extends CodePattern{

	public JavaArrayDeclaration() {
		super("arrayDeclaration");
		
		addCodePiece(new JavaName(),new StaticKeyword("[]"),new StaticKeyword("="),new StaticKeyword("new"),
				new UninterpretedEvaluation(new StaticKeyword("[")),
				new StaticKeyword("["),
				new UninterpretedEvaluation(new StaticKeyword("]")),
				new StaticKeyword("]"),
				new StaticKeyword(";"));
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

