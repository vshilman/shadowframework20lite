package codeconverter.javaCppComparator.codePieces;

import java.util.List;

import codeconverter.ICodePiece;
import codeconverter.PieceType;
import codeconverter.comparator.CodePieceComparator;
import codeconverter.javaJsComparator.codePieces.ArrayContentComparator;

public class ArrayDeclarationCompartorJC extends CodePieceComparator{

	@Override
	protected boolean internalCompare(List<ICodePiece> lang1Pieces,
			List<ICodePiece> lang2Pieces) {

		if (new ArrayContentComparatorJC().compare(lang1Father.getPieceByType(PieceType.ARRAY_CONTENT),
				lang2Father.getPieceByType(PieceType.ARRAY_CONTENT))) {
			return true;
		}

		return false;
	}

}
