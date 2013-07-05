package codeconverter.cpp;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.codepieces.CodeSequence;
import codeconverter.codepieces.OptionalCode;

public class CppGenericDirectiveLine extends CodePattern{

	public CppGenericDirectiveLine() {
		addCodePiece(new CppPreprocessorDirective(),
				     new OptionalCode(new CodeSequence(new CppAlgebraicExpression(), ",")));
		addPatternType(PatternType.LIBRARY_DECLARATION);

	}

}
