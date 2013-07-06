package codeconverter.cpp;

import java.util.List;

import codeconverter.codepieces.KeywordSet;
import codeconverter.codepieces.Value;

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
