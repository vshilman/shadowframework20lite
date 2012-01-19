package codeconverter.java.codelines;

import codeconverter.AlternativeCode;
import codeconverter.CodePattern;
import codeconverter.CodeSequence;
import codeconverter.ICodeElement;
import codeconverter.Number;
import codeconverter.PatternType;
import codeconverter.StaticKeyword;
import codeconverter.elements.Variable;
import codeconverter.java.JavaName;

public class JavaSuperPattern extends CodePattern{

	private JavaName variableName=new JavaName();
	private Number number=new Number();
	private Variable variable;
	
	public JavaSuperPattern() {
		super("super");
		addCodePiece(new StaticKeyword("super"),new StaticKeyword("("),
				new CodeSequence(new AlternativeCode(variableName,number),", "),
				new StaticKeyword(")"));
		addCodePattern(PatternType.LINE_OF_CODE);
		addCodePattern(PatternType.SUPER);
	}

	@Override
	public ICodeElement cloneCodePiece() {
		JavaSuperPattern pattern=new JavaSuperPattern();
		pattern.variable=new Variable(null,variableName.getData());
		return pattern;
	}
	
	@Override
	public String toString() {
		if(variable.getName().trim().length()==0)
			return "\t super("+number.getData()+");";
		return "\t super("+variable.getName()+");";
	}
}