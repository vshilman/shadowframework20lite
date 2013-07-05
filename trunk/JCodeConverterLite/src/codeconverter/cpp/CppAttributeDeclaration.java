package codeconverter.cpp;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.codepieces.CodeSequence;

public class CppAttributeDeclaration extends CodePattern{

	public CppAttributeDeclaration() {
		addCodePiece(new CppType(), new CppName());
		addPatternType(PatternType.ATTRIBUTE_DECLARATION);
	}

	//public: .....
	//Attributes declaration cannot include an assignment?

}
