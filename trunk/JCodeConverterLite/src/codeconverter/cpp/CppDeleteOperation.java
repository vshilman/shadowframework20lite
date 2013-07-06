package codeconverter.cpp;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.codepieces.UniqueKeyword;

public class CppDeleteOperation extends CodePattern{

	public CppDeleteOperation() {
		addCodePiece(new UniqueKeyword("delete "), new CppName());
		addPatternType(PatternType.DEALLOCATION);
	}


}
