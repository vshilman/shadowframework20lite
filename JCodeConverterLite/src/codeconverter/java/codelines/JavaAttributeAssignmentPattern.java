package codeconverter.java.codelines;

import codeconverter.CodePattern;
import codeconverter.ICodeElement;
import codeconverter.PatternType;
import codeconverter.StaticKeyword;
import codeconverter.elements.Variable;
import codeconverter.java.JavaName;

public class JavaAttributeAssignmentPattern extends CodePattern{

	private JavaName attributeName=new JavaName();
	private JavaName variableName=new JavaName();
	private Variable attribute;
	private Variable variable;
	
	public JavaAttributeAssignmentPattern() {
		super("assignment");
		addCodePiece(new StaticKeyword("this."),attributeName,new StaticKeyword("="),
				variableName);
		addCodePattern(PatternType.ASSIGNMENT,PatternType.LINE_OF_CODE);
	}
	
	@Override
	public ICodeElement cloneCodePiece() {
		JavaAttributeAssignmentPattern pattern=new JavaAttributeAssignmentPattern();
		pattern.attribute=new Variable(null,attributeName.getData());
		pattern.variable=new Variable(null,variableName.getData());
		return pattern;
	}
	
	@Override
	public String toString() {
		return "\tthis."+attribute.getName()+"="+variable.getName()+";";
	}
}
