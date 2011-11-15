package codeconverter.java;

import codeconverter.ICodeElement;
import codeconverter.ICodePiece;
import codeconverter.Name;
import codeconverter.VariableDeclarion;
import codeconverter.elements.Variable;

public class JavaVariable extends VariableDeclarion{

	private static JavaType staticName=new JavaType();
	private Variable variable;
	
	@Override
	public ICodeElement cloneCodePiece() {
		JavaVariable variable=new JavaVariable();
		Name name=(Name)this.name.cloneCodePiece();
		ICodePiece type=(ICodePiece)variable.type.cloneCodePiece();
		variable.variable=new Variable(((JavaType)type).getType(),name.getData());
		return variable;
	}
	
	@Override
	public ICodePiece getTypeCode() {
		return staticName;
	}

	public Variable getVariable() {
		return variable;
	}

	public void setVariable(Variable variable) {
		this.variable=variable;
	}
	
	@Override
	public String toString() {
		return variable.getType().getName()+" "+variable.getName();
	}
}
