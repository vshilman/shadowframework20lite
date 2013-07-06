package codeconverter.cpp.codelines;

import java.awt.PageAttributes;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.PieceType;
import codeconverter.codepieces.BestAlternativeCode;
import codeconverter.codepieces.CodeSequence;
import codeconverter.codepieces.CompositeCodePiece;
import codeconverter.codepieces.KeywordSet;
import codeconverter.codepieces.OptionalCode;
import codeconverter.codepieces.UniqueKeyword;
import codeconverter.cpp.CppAlgebraicExpression;
import codeconverter.cpp.CppArrayContent;
import codeconverter.cpp.CppArrayDeclaration;
import codeconverter.cpp.CppBitwiseExpression;
import codeconverter.cpp.CppMethodEvaluation;
import codeconverter.cpp.CppName;
import codeconverter.cpp.CppNewStatement;

public class CppVariableAssignment extends CodePattern{


	public CppVariableAssignment() {

		addCodePiece(new CppName(PieceType.NAME),
					 new OptionalCode(new KeywordSet("+","*","-","/")),
					 new OptionalCode(new UniqueKeyword("=")),
					 new BestAlternativeCode(true, new CppMethodEvaluation("->"),
							                       new CppMethodEvaluation("."),
							                       new CppAlgebraicExpression(),
							                       new CppBitwiseExpression(),
							                       new CppNewStatement(),
							                       new CppArrayContent(),
							                       new CppArrayDeclaration(),
							                       new CompositeCodePiece(new UniqueKeyword("("),
													                      new CodeSequence(new OptionalCode(new CppAlgebraicExpression()), ","),
													                      new UniqueKeyword(")"))));

		addPatternType(PatternType.VARIABLE_ASSIGNMENT,PatternType.ASSIGNMENT,PatternType.LINE_OF_CODE);


	}

}
