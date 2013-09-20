package codeconverter.javaCppComparator.codePatterns;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.PieceType;
import codeconverter.comparator.CodePatternComparator;
import codeconverter.javaCppComparator.codePieces.MethodVariablesComparatorJC;
import codeconverter.javaCppComparator.codePieces.NameComparatorJC;

public class ConstructorDeclarationComparatorJCH implements CodePatternComparator{

	@Override
	public boolean compare(CodePattern javaPattern, CodePattern cppPattern) {

		System.out.println(javaPattern.toString()+ "  vs   "+cppPattern.toString());

		if(javaPattern.getPatternType().get(0)!=PatternType.CONSTRUCTOR_DECLARATION){
			return false;
		}
		if(cppPattern.getPatternType().get(0)!=PatternType.CONSTRUCTOR_DECLARATION){
			return false;
		}

		if (!new NameComparatorJC().compare(javaPattern.getPieceByType(PieceType.NAME),
				cppPattern.getPieceByType(PieceType.NAME))) {
			return false;
		}


		if (!new MethodVariablesComparatorJC().compare(javaPattern.getPieceByType(PieceType.METHOD_VARIABLES),
				cppPattern.getPieceByType(PieceType.METHOD_VARIABLES))) {
			return false;
		}

		return true;
	}



}
