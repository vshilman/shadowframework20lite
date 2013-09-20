package codeconverter.javaCppComparator.codePatterns;

import codeconverter.CodePattern;
import codeconverter.ICodePiece;
import codeconverter.PatternType;
import codeconverter.PieceType;
import codeconverter.comparator.CodePatternComparator;
import codeconverter.javaCppComparator.codePieces.NameComparatorJC;
import codeconverter.javaCppComparator.codePieces.TypeComparatorJC;

public class AttributeDeclarationComparatorJCH implements CodePatternComparator{

	@Override
	public boolean compare(CodePattern javaPattern, CodePattern cppPattern) {

		System.out.println(javaPattern.toString()+ "  vs   "+cppPattern.toString());

		if(javaPattern.getPatternType().get(0)!=PatternType.ATTRIBUTE_DECLARATION){
			return false;
		}
		if (cppPattern.getPatternType().get(0) != PatternType.ATTRIBUTE_DECLARATION) {
			return false;
		}

		if(javaPattern.getPatternType().size()>1 && javaPattern.getPatternType().get(1)==PatternType.ATTRIBUTE_ASSIGNMENT){
			if(! new TypeComparatorJC().compare(javaPattern.getPieceByType(PieceType.VARIABLE).getPieceByType(PieceType.TYPE),cppPattern.getPieces().get(0).getPieceByType(PieceType.TYPE))){
				return false;
			}
			if(! new NameComparatorJC().compare(javaPattern.getPieceByType(PieceType.VARIABLE).getPieceByType(PieceType.NAME),cppPattern.getPieces().get(1).getPieces().get(0).getPieces().get(0))){
				return false;
			}

		} else {
			if(! new TypeComparatorJC().compare(javaPattern.getPieceByType(PieceType.TYPE),cppPattern.getPieces().get(0).getPieceByType(PieceType.TYPE))){
				return false;
			}
			ICodePiece javaPiece = javaPattern.getPieces().get(2).getPieces().get(0).getPieces().get(0); //TODO Dovrebe prendere il JavaNamePart
			javaPiece.setPieceType(PieceType.NAME);
			if(! new NameComparatorJC().compare(javaPiece,cppPattern.getPieces().get(1).getPieces().get(0).getPieces().get(0))){
				return false;
			}
		}
		return true;
	}



}
