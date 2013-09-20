package codeconverter.javaCppComparator.codePatterns;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.PieceType;
import codeconverter.comparator.CodePatternComparator;
import codeconverter.javaCppComparator.codePieces.ExpressionComparatorJC;
import codeconverter.javaCppComparator.codePieces.NameComparatorJC;
import codeconverter.javaCppComparator.codePieces.NewStatementComparatorJC;
import codeconverter.javaJsComparator.codePieces.ExpressionComparator;
import codeconverter.javaJsComparator.codePieces.NameComparator;
import codeconverter.javaJsComparator.codePieces.NewStatementComparator;

public class AttributeAssignmentComparatorJC implements CodePatternComparator{

	@Override
	public boolean compare(CodePattern javaPattern, CodePattern cppPattern) {

		if(cppPattern.getPatternType().get(0)!=PatternType.ATTRIBUTE_ASSIGNMENT){
			return false;
		}

		if(javaPattern.getPatternType().get(0)==PatternType.ATTRIBUTE_DECLARATION){
			if(javaPattern.getPatternType().size()<2){
				return false;
			}
			if(javaPattern.getPatternType().get(1)!=PatternType.ATTRIBUTE_ASSIGNMENT){
				return false;
			}

			if (!new NameComparatorJC().compare(javaPattern.getPieceByType(PieceType.VARIABLE).getPieceByType(PieceType.NAME),
					cppPattern.getPieceByType(PieceType.NAME))) {
				return false;
			}

		} else {
			if(javaPattern.getPatternType().get(0)!=PatternType.ATTRIBUTE_ASSIGNMENT){
				return false;
			}
			System.out.println("I'm in for" + cppPattern.toString()+" vs "+javaPattern.toString()+"\n\n");

			if (!new NameComparatorJC().compare(javaPattern.getPieceByType(PieceType.NAME),
					cppPattern.getPieceByType(PieceType.NAME))) {
				System.out.println("chupa chupa");
				return false;
			}

		}

		if(javaPattern.getPieces().get(3).getPieceType()!=cppPattern.getPieces().get(3).getPieces().get(1).getPieceType()){
			System.out.println(javaPattern.getPieces().get(3).getPieceType()+"   "+cppPattern.getPieces().get(3).getPieces().get(1).getPieceType());
			return false;
		}

		if (javaPattern.getPieces().get(3).getPieceType() == PieceType.EXPRESSION) {
			if (!new ExpressionComparatorJC().compare(javaPattern.getPieceByType(PieceType.EXPRESSION),
					cppPattern.getPieces().get(3).getPieces().get(1))) {
				System.out.println(javaPattern.getPieceByType(PieceType.EXPRESSION).toString()+"    "+cppPattern.getPieces().get(3).getPieces().get(1).toString());
				//System.out.println("billi billi");
				return false;
			}
		}

		if (javaPattern.getPieces().get(3).getPieceType() == PieceType.NEW_STATEMENT) {
			if (!new NewStatementComparatorJC().compare(javaPattern.getPieceByType(PieceType.NEW_STATEMENT),
					cppPattern.getPieces().get(3).getPieces().get(1))) {
				System.out.println("sada sada");
				return false;
			}
		}

		//where are arrays???

		return true;
	}



}
