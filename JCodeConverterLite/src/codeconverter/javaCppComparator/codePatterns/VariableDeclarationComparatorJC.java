package codeconverter.javaCppComparator.codePatterns;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.PieceType;
import codeconverter.comparator.CodePatternComparator;
import codeconverter.javaCppComparator.codePieces.VariableComparatorJC;
import codeconverter.javaJsComparator.codePieces.VariableComparator;

public class VariableDeclarationComparatorJC implements CodePatternComparator{

	@Override
	public boolean compare(CodePattern javaPattern, CodePattern cppPattern) {

		if (javaPattern.getPatternType().get(0) != PatternType.VARIABLE_DECLARATION
				|| javaPattern.getPatternType().get(1) == PatternType.VARIABLE_ASSIGNMENT) {
			return false;
		}
		if (javaPattern.getPatternType().get(0) != cppPattern
				.getPatternType().get(0)
				|| javaPattern.getPatternType().get(1) != cppPattern
						.getPatternType().get(1)) {
			return false;
		}

		if (!new VariableComparatorJC().compare(javaPattern.getPieceByType(PieceType.VARIABLE),
				cppPattern.getPieceByType(PieceType.VARIABLE))) {
			return false;
		}

		return true;
	}



}
