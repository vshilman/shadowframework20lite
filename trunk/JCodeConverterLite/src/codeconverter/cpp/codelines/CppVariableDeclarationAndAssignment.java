package codeconverter.cpp.codelines;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.codepieces.BestAlternativeCode;
import codeconverter.codepieces.CodeSequence;
import codeconverter.codepieces.CompositeCodePiece;
import codeconverter.codepieces.OptionalCode;
import codeconverter.codepieces.UniqueKeyword;
import codeconverter.cpp.CppAlgebraicExpression;
import codeconverter.cpp.CppArrayContent;
import codeconverter.cpp.CppArrayDeclaration;
import codeconverter.cpp.CppBitwiseExpression;
import codeconverter.cpp.CppMethodEvaluation;
import codeconverter.cpp.CppNewStatement;
import codeconverter.cpp.CppVariable;

public class CppVariableDeclarationAndAssignment extends CodePattern{

	public CppVariableDeclarationAndAssignment() {

		addCodePiece(new CppVariable(),
					new BestAlternativeCode(true,new CompositeCodePiece(new UniqueKeyword("="), new CppMethodEvaluation("->")),
												 new CompositeCodePiece(new UniqueKeyword("="), new CppMethodEvaluation(".")),
												 new CompositeCodePiece(new UniqueKeyword("="),new CppAlgebraicExpression()),
												 new CompositeCodePiece(new UniqueKeyword("="),new CppBitwiseExpression()),
												 new CompositeCodePiece(new UniqueKeyword("="),new CppNewStatement()),
												 new CompositeCodePiece(new UniqueKeyword("="),new CppArrayContent()),
												 new CompositeCodePiece(new UniqueKeyword("="),new CppArrayDeclaration()),
						                         new CompositeCodePiece(new UniqueKeyword("("),
												                      new CodeSequence(new OptionalCode(new CppAlgebraicExpression()), ","),
												                      new UniqueKeyword(")"))));

		addPatternType(PatternType.VARIABLE_DECLARATION,PatternType.VARIABLE_ASSIGNMENT,PatternType.ASSIGNMENT,PatternType.LINE_OF_CODE);
	}
}
