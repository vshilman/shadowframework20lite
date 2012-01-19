package codeconverter.java;

import codeconverter.CodeSequence;
import codeconverter.ICodePieceSequencer;
import codeconverter.elements.Variable;
import codeconverter.elements.VariableList;

public class JavaVariablesList implements CodeSequenceqData {

	private CodeSequence sequence=new CodeSequence(new JavaVariable(),", ");
	private VariableList variables=new VariableList();
	
	public void loadVariablesList(ICodePieceSequencer sequence) {
		for (int i=0; i < sequence.getPieces().size(); i++) {
			JavaVariable variable=(JavaVariable)(sequence.getPieces().get(i).cloneCodePiece());
			getVariables().add(variable.getVariable());
		}
	}

	public String toString() {
		String tmp="";
		
		boolean start=true;
		for (Variable va : getVariables()) {
			if(!start){
				tmp+=",";
			}	
			tmp+=va.toString();
			start=false;
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
