package codeconverter.java.codelines;

import codeconverter.CodePattern;
import codeconverter.ICodeElement;
import codeconverter.PatternType;
import codeconverter.StaticKeyword;
import codeconverter.elements.Variable;
import codeconverter.java.JavaName;

public class JavaReturnPattern extends CodePattern{

	private JavaName variableName=new JavaName();
	private Variable variable;
	
	public JavaReturnPattern() {
		super("return");
		addCodePiece(new StaticKeyword("return"),variableName,new StaticKeyword(";"));
		addCodePattern(PatternType.RETURN,PatternType.LINE_OF_CODE);
	}

	@Override
	public ICodeElement cloneCodePiece() {
		JavaReturnPattern pattern=new JavaReturnPattern();
		pattern.variable=new Variable(null,variableName.getData());
		return pattern;
	}
	
	@Override
	public String toString() {
		return "\treturn "+variable.getName()+";";
	}
}
