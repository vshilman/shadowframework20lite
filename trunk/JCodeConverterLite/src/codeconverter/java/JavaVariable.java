package codeconverter.java;

import codeconverter.ICodeElement;
import codeconverter.ICodePiece;
import codeconverter.VariableDeclarion;
import codeconverter.elements.Variable;

public class JavaVariable extends VariableDeclarion{

	private static JavaType staticName=new JavaType();
	private Variable variable;
	
	@Override
	public ICodeElement cloneCodePiece() {
		JavaVariable variable=new JavaVariable();
		ICodePiece type=(ICodePiece)variable.type.cloneCodePiece();
		if(this.variable!=null){
			variable.variable=new Variable(((JavaType)type).getType(),this.variable.getName());
		}else{
			variable.variable=new Variable(((JavaType)type).getType(),this.name.getData());
		}	
		return variable;
	}
	
	@Override
	public ICodePiece getTypeCode() {
		return staticName;
	}

	public Variable getVariable() {
		return variable;
	}
	
	@Override
	public String toString() {
		return variable.getType().getName()+" "+variable.getName();
	}
}
