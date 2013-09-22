package codeconverter.cpp;


import codeconverter.codepieces.KeywordSet;

public class CppSpecifier extends KeywordSet{

	public static String specifiers[]={
			"public",
			"private",
			"protected",
			"static",
			"virtual",
			"friend"
	};

	public CppSpecifier() {
		super(specifiers);
	}

}
