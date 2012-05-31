package codeconverter.java;

import codeconverter.codepieces.KeywordSet;

public class JavaModifier extends KeywordSet{

	private static String modifiers[]={
		"public",
		"private",
		"protected",
		"static",
		"final",
		"abstract"
	};
	
	public JavaModifier() {
		super(modifiers);
	}
	
}
