package codeconverter.cpp.codelines;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.codepieces.AlternativeCode;
import codeconverter.codepieces.CompositeCodePiece;
import codeconverter.codepieces.OptionalCode;
import codeconverter.codepieces.UniqueKeyword;
import codeconverter.cpp.CppAlgebraicExpression;
import codeconverter.cpp.CppBitwiseExpression;
import codeconverter.cpp.CppMethodEvaluation;

public class CppReturnPattern extends CodePattern{

	public CppReturnPattern() {

		addCodePiece(new UniqueKeyword("return"),
					 new OptionalCode(new CompositeCodePiece(new UniqueKeyword("this"),
							                                 new AlternativeCode(true, new UniqueKeyword("->"),new UniqueKeyword(".")))),
				     new AlternativeCode(true, new CppAlgebraicExpression(),
				    		 				   new CppBitwiseExpression(),
				    		 				   new CppMethodEvaluation("->"),
				    		 				   new CppMethodEvaluation(".")));
		addPatternType(PatternType.RETURN,PatternType.LINE_OF_CODE);
	}

}
