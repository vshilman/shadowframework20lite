package codeconverter.javaCppComparator.codePatterns;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.PieceType;
import codeconverter.comparator.CodePatternComparator;
import codeconverter.javaCppComparator.codePieces.MethodComparatorJC;
import codeconverter.javaJsComparator.codePieces.MethodComparator;

public class MethodAccessComparatorJC implements CodePatternComparator{

	@Override
	public boolean compare(CodePattern javaPattern, CodePattern cppPattern) {


		if (javaPattern.getPatternType().get(0) != PatternType.CALL) {
			return false;
		}
		if (javaPattern.getPatternType().get(0) != cppPattern
				.getPatternType().get(0)) {
			return false;
		}

		if (!new MethodComparatorJC().compare(javaPattern.getPieceByType(PieceType.CALL),
				cppPattern.getPieceByType(PieceType.CALL))) {
			return false;
		}

		return true;


	}



}
