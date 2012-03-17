package codeconverter.js.old;

import codeconverter.ICodePieceSequencer;
import codeconverter.codepieces.CodeSequence;
import codeconverter.elements.Variable;
import codeconverter.elements.VariableList;

public class JsVariablesList {

	private CodeSequence sequence=new CodeSequence(new JsName(),", ");
	private VariableList variables=new VariableList();
	
	public void loadVariablesList(ICodePieceSequencer sequence) {
		for (int i=0; i < sequence.getPieces().size(); i++) {
			JsName name=(JsName)(sequence.getPieces().get(i).cloneCodePiece());
			getVariables().add(new Variable(null, name.getData()));
		}
	}

	public String toString() {
		String tmp="";
		
		for (Variable va : getVariables()) {
			tmp+=va.getName()+" ";
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
