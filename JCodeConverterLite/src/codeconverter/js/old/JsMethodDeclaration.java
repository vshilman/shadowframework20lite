package codeconverter.js.old;

import codeconverter.CodePattern;
import codeconverter.ICodeElement;
import codeconverter.ICodePieceSequencer;
import codeconverter.PatternType;
import codeconverter.codepieces.UniqueKeyword;
import codeconverter.elements.Variable;

public class JsMethodDeclaration extends CodePattern{

	private JsName name=new JsName();
	private JsVariablesList jsVariablesList=new JsVariablesList();
	private Variable variable;
	
	public JsMethodDeclaration() {
		super("attribute declaration");
		addCodePiece(name,new UniqueKeyword(":"),new UniqueKeyword("function"),new UniqueKeyword("("),
				jsVariablesList.getSequence(),new UniqueKeyword(")"),new UniqueKeyword("{"));
		addCodePattern(PatternType.CONSTRUCTOR_DECLARATION);
	}
	
	@Override
	public ICodeElement cloneCodePiece() {
		JsMethodDeclaration pattern=new JsMethodDeclaration();
		pattern.variable=new Variable(null,new String(name.getData()));
		ICodePieceSequencer sequence=((ICodePieceSequencer )this.jsVariablesList.getSequence().cloneCodePiece());
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
