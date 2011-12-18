package codeconverter.java.codelines;

import codeconverter.AlgebraicExpression;
import codeconverter.CodePattern;
import codeconverter.ICodeElement;
import codeconverter.PatternType;
import codeconverter.StaticKeyword;
import codeconverter.elements.Variable;
import codeconverter.java.JavaName;

public class JavaAssignment  extends CodePattern{

	private JavaName attributeName=new JavaName();
	private JavaName variableName=new JavaName();
	private Variable attribute;
	private Variable variable;
	
	public JavaAssignment() {
		super("assignment");
		
		addCodePiece(attributeName,new StaticKeyword("="),
				new AlgebraicExpression(variableName),new StaticKeyword(";"));
		addCodePattern(PatternType.ASSIGNMENT,PatternType.LINE_OF_CODE);
	}
	
	@Override
	public ICodeElement cloneCodePiece() {
		JavaAssignment pattern=new JavaAssignment();
		pattern.attribute=new Variable(null,attributeName.getData());
		pattern.variable=new Variable(null,variableName.getData());
		return pattern;
	}
	
	@Override
	public String toString() {
		return "\tthis."+attribute.getName()+"="+variable.getName()+";";
	}
}
