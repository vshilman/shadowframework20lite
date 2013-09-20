package codeconverter.javaCppComparator.codePieces;

import java.util.List;

import codeconverter.ICodePiece;
import codeconverter.comparator.CodePieceComparator;

public class TypeComparatorJC extends CodePieceComparator{

	@Override
	protected boolean internalCompare(List<ICodePiece> javaPieces,
			List<ICodePiece> cppPieces) {

		System.out.println(lang1Father.toString()+"  vs  "+lang2Father.toString());

		if(lang1Father.toString().equals(lang2Father.toString())){
			System.out.println("1");
			return true;
		}

		if(lang1Father.toString().equals("String") && lang2Father.toString().equals("string")){
			System.out.println("2");
			return true;
		}

		if(lang1Father.toString().equals("boolean") && lang2Father.toString().equals("bool")){
			System.out.println("3");
			return true;
		}
		System.out.println("Nada");


		return false;
	}


}
