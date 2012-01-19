package codeconverter.java;

import codeconverter.CodeSequence;
import codeconverter.ICodePieceSequencer;
import codeconverter.elements.Modifier;
import codeconverter.elements.ModifiersSet;

public class JavaModifiersSet {
	
	private CodeSequence sequence=new CodeSequence(new JavaModifier()," ");
	private ModifiersSet modifiers=new ModifiersSet();
	
	public void loadModifiersSet(ICodePieceSequencer sequence) {
		for (int i=0; i < sequence.getPieces().size(); i++) {
			JavaModifier modifier=(JavaModifier)(sequence.getPieces().get(i));
			getModifiers().add(modifier.getModifier());
		}
	}

	public String toString() {
		String tmp="";
		for (Modifier modifier : getModifiers()) {
			tmp+=modifier.getName()+" ";
		}
		return tmp;
	}

	public void setSequence(CodeSequence sequence) {
		this.sequence = sequence;
	}

	public CodeSequence getSequence() {
		return sequence;
	}

	public void setModifiers(ModifiersSet modifiers) {
		this.modifiers = modifiers;
	}

	public ModifiersSet getModifiers() {
		return modifiers;
	}
}
