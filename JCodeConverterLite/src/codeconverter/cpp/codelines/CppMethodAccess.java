package codeconverter.cpp.codelines;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.codepieces.AlternativeCode;
import codeconverter.cpp.CppMethodEvaluation;

public class CppMethodAccess extends CodePattern {

	public CppMethodAccess() {
		addCodePiece(new AlternativeCode(true,new CppMethodEvaluation("->"),new CppMethodEvaluation(".")));

		addPatternType(PatternType.CALL,PatternType.LINE_OF_CODE);
	}

}
