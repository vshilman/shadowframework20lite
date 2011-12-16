package codeconverter.java;

import codeconverter.ICodePiece;
import codeconverter.Keyword;
import codeconverter.elements.Modifier;

public class JavaModifier extends Keyword{

	private static String modifiers[]={
		"public",
		"private",
		"protected",
		"static",
		"final",
		"abstract"
	};
	
	Modifier modifier;
	
	@Override
	public ICodePiece cloneCodePiece() {
		JavaModifier modifier=new JavaModifier();
		
		modifier=new JavaModifier();
		modifier.modifier=new Modifier(modifiers[keywordIndex]);
		
		return modifier;
	}
	
	@Override
	public String[] getAlternatives() {
		return modifiers;
	}

	public Modifier getModifier() {
		return modifier;
	}
	
	@Override
	public String toString() {
		return modifier.getName();
	}
}
