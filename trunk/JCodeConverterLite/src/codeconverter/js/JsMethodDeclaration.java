package codeconverter.js;

import codeconverter.CodePattern;
import codeconverter.CodeSequence;
import codeconverter.ICodeElement;
import codeconverter.PatternType;
import codeconverter.StaticKeyword;
import codeconverter.elements.Variable;

public class JsMethodDeclaration extends CodePattern{

	private JsName name=new JsName();
	private JsVariablesList jsVariablesList=new JsVariablesList();
	private Variable variable;
	
	public JsMethodDeclaration() {
		super("attribute declaration");
		addCodePiece(name,new StaticKeyword(":"),new StaticKeyword("function"),new StaticKeyword("("),
				jsVariablesList.getSequence(),new StaticKeyword(")"),new StaticKeyword("{"));
		addCodePattern(PatternType.CONSTRUCTOR_DECLARATION);
	}
	
	@Override
	public ICodeElement cloneCodePiece() {
		JsMethodDeclaration pattern=new JsMethodDeclaration();
		pattern.variable=new Variable(null,new String(name.getData()));
		CodeSequence sequence=((CodeSequence )this.jsVariablesList.getSequence().cloneCodePiece());
		pattern.jsVariablesList.loadVariablesList(sequence);
		return pattern;
	}

	public JsVariablesList getJsVariablesList() {
		return jsVariablesList;
	}
	
	public void setJsVariablesList(JsVariablesList jsVariablesList) {
		this.jsVariablesList = jsVariablesList;
	}
	
	public Variable getVariable() {
		return variable;
	}
	
	public void setVariable(Variable variable) {
		this.variable = variable;
	}
	
	@Override
	public String toString() {
		return "function "+variable.getName()+"("+jsVariablesList+") {";
	}
}
