package codeconverter.java;

import codeconverter.CodeSequence;
import codeconverter.elements.Variable;
import codeconverter.elements.VariableList;

public class JavaVariablesList {

	private CodeSequence sequence=new CodeSequence(new JavaVariable(),", ");
	private VariableList variables=new VariableList();
	
	public void loadVariablesList(CodeSequence sequence) {
		for (int i=0; i < sequence.getPieces().size(); i++) {
			JavaVariable variable=(JavaVariable)(sequence.getPieces().get(i).cloneCodePiece());
			System.out.println("loading variable "+variable.getVariable().getType().getName()+" "+variable.getVariable().getName());
			getVariables().add(variable.getVariable());
		}
	}

	public String toString() {
		String tmp="";
		
		for (Variable va : getVariables()) {
			tmp+=va.toString()+",";
		}
		return tmp;
	}
	

	public void setSequence(CodeSequence sequence) {
		this.sequence = sequence;
	}

	public CodeSequence getSequence() {
		return sequence;
	}

	public VariableList getVariables() {
		return variables;
	}

	public void setVariables(VariableList variables) {
		this.variables=variables;
	}

}
