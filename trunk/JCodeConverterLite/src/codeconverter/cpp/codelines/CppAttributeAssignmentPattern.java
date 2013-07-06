package codeconverter.cpp.codelines;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.PieceType;
import codeconverter.codepieces.AlternativeCode;
import codeconverter.codepieces.BestAlternativeCode;
import codeconverter.codepieces.CodeSequence;
import codeconverter.codepieces.CompositeCodePiece;
import codeconverter.codepieces.OptionalCode;
import codeconverter.codepieces.UniqueKeyword;
import codeconverter.cpp.CppAlgebraicExpression;
import codeconverter.cpp.CppArrayContent;
import codeconverter.cpp.CppArrayDeclaration;
import codeconverter.cpp.CppBitwiseExpression;
import codeconverter.cpp.CppName;
import codeconverter.cpp.CppNewStatement;

public class CppAttributeAssignmentPattern extends CodePattern{

	public CppAttributeAssignmentPattern() {
		addCodePiece(new UniqueKeyword("this"),
				     new BestAlternativeCode(true, new UniqueKeyword("->"),new UniqueKeyword(".")),
					 new CppName(PieceType.NAME),
					 new OptionalCode(new UniqueKeyword("=")),
					 new AlternativeCode(true,new CppAlgebraicExpression(),
							 				  new CppBitwiseExpression(),
							 				  new CppNewStatement(),
							 				  new CppArrayContent()),
							 				  new CppArrayDeclaration(),
							 				  new CompositeCodePiece(new UniqueKeyword("("),
								                      new CodeSequence(new OptionalCode(new CppAlgebraicExpression()), ","),
								                      new UniqueKeyword(")")));

		addPatternType(PatternType.ATTRIBUTE_ASSIGNMENT,PatternType.ASSIGNMENT,PatternType.LINE_OF_CODE);
	}


}
