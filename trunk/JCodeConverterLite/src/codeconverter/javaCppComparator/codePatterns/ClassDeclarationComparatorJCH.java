package codeconverter.javaCppComparator.codePatterns;

import codeconverter.CodePattern;
import codeconverter.ICodePiece;
import codeconverter.PatternType;
import codeconverter.PieceType;
import codeconverter.comparator.CodePatternComparator;
import codeconverter.javaCppComparator.codePieces.NameComparatorJC;
import codeconverter.javaJsComparator.codePieces.NameComparator;

public class ClassDeclarationComparatorJCH implements CodePatternComparator{

	@Override
	public boolean compare(CodePattern javaPattern, CodePattern cppPattern) {

		System.out.println(javaPattern.toString()+ "  vs   "+cppPattern.toString());

		if(javaPattern.getPatternType().get(0)!=PatternType.CLASS_DECLARATION){
			return false;
		}
		if(cppPattern.getPatternType().get(0)!=PatternType.CLASS_DECLARATION){
			return false;
		}

		if(!javaPattern.getPieceByType(PieceType.NAME).toString().trim().equals(cppPattern.getPieceByType(PieceType.NAME).toString().trim())){
			return false;
		}

		if(javaPattern.getPieces().size()==4){

			if(cppPattern.getPieces().size()<3){
				return false;
			}
			ICodePiece p1=javaPattern.getPieces().get(3).getPieces().get(1).getPieces().get(0);
			p1.setPieceType(PieceType.NAME);

			ICodePiece p2=cppPattern.getPieces().get(2).getPieces().get(1).getPieces().get(0).getPieces().get(1);
			p2.setPieceType(PieceType.NAME);

			if(! new NameComparatorJC().compare(p1,p2)){
				return false;
			}

		}

		//At the moment it isn't supported the comparation between extends Ciao implements Ciop / : Ciao, Ciop

		return true;
	}


}
