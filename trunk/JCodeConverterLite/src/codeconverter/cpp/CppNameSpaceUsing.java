package codeconverter.cpp;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.codepieces.UniqueKeyword;

public class CppNameSpaceUsing extends CodePattern{

	public CppNameSpaceUsing() {
		addCodePiece(new UniqueKeyword("using"),new UniqueKeyword("namespace"),new CppName());
		addPatternType(PatternType.NAMESPACE_USAGE);
	}

}
