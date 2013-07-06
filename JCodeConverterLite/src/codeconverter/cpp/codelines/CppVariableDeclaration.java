package codeconverter.cpp.codelines;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.cpp.CppVariable;

public class CppVariableDeclaration extends CodePattern{

	public CppVariableDeclaration() {
		addCodePiece(new CppVariable());
		addPatternType(PatternType.VARIABLE_DECLARATION,PatternType.LINE_OF_CODE);
	}

}
