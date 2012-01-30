package codeconverter.js;

import codeconverter.CodePattern;
import codeconverter.ICodeElement;
import codeconverter.PatternType;
import codeconverter.codepieces.UniqueKeyword;
import codeconverter.elements.Variable;

public class JsAttributeAssignmentPattern extends CodePattern{

	private JsName attributeName=new JsName();
	private JsName variableName=new JsName();
	private Variable attribute;
	private Variable variable;
	
	public JsAttributeAssignmentPattern() {
		super("assignment");
		addCodePiece(new UniqueKeyword("this."),attributeName,new UniqueKeyword("="),
				variableName,new UniqueKeyword(";"));
		addCodePattern(PatternType.ASSIGNMENT);
	}
	
	@Override
	public ICodeElement cloneCodePiece() {
		JsAttributeAssignmentPattern pattern=new JsAttributeAssignmentPattern();
		pattern.attribute=new Variable(null,attributeName.getData());
		pattern.variable=new Variable(null,variableName.getData());
		return pattern;
	}
	
	@Override
	public String toString() {
		return "\tthis."+attribute.getName()+"="+variable.getName()+";";
	}
}
