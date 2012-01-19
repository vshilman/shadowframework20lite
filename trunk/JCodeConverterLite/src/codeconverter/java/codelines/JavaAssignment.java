package codeconverter.java.codelines;

import codeconverter.AlgebraicExpression;
import codeconverter.CodePattern;
import codeconverter.Expression;
import codeconverter.ICodeElement;
import codeconverter.PatternType;
import codeconverter.StaticKeyword;
import codeconverter.elements.Variable;
import codeconverter.java.JavaName;

public class JavaAssignment  extends CodePattern{

	private JavaName attributeName=new JavaName();
	private JavaName variableName=new JavaName();
	private AlgebraicExpression expression;
	private Variable attribute;
	private Expression expressionCloned;
	
	public JavaAssignment() {
		super("assignment");
		
		expression=new AlgebraicExpression(variableName);
		addCodePiece(attributeName,new StaticKeyword("="));
		addCodePattern(PatternType.ASSIGNMENT,PatternType.LINE_OF_CODE);
	}
	
	@Override
	public ICodeElement cloneCodePiece() {
		JavaAssignment pattern=new JavaAssignment();
		pattern.attribute=new Variable(null,attributeName.getData());
		pattern.expressionCloned=(Expression)(expression.cloneCodePiece());
		return pattern;
	}
	
	@Override
	public String toString() {
		return "\t	"+attribute.getName()+"="+expressionCloned+";";
	}
}
