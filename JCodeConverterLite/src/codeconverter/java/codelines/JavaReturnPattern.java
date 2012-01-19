package codeconverter.java.codelines;

import codeconverter.AlternativeCode;
import codeconverter.CodePattern;
import codeconverter.ICodeElement;
import codeconverter.Number;
import codeconverter.PatternType;
import codeconverter.StaticKeyword;
import codeconverter.elements.Variable;
import codeconverter.java.JavaName;

public class JavaReturnPattern extends CodePattern{

	private JavaName variableName=new JavaName();
	private Number number=new Number();
	private Variable variable;
	
	public JavaReturnPattern() {
		super("return");
		addCodePiece(new StaticKeyword("return"),new AlternativeCode(variableName,number));
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
		if(variable.getName().trim().length()==0)
			return "\treturn "+number.getData()+";";
		return "\treturn this."+variable.getName()+";";
	}
}