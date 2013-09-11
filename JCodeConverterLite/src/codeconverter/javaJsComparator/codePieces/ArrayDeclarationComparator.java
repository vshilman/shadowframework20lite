package codeconverter.javaJsComparator.codePieces;

import java.util.List;

import codeconverter.ICodePiece;
import codeconverter.PieceType;
import codeconverter.comparator.CodePieceComparator;

public class ArrayDeclarationComparator extends CodePieceComparator {

	@Override
	protected boolean internalCompare(List<ICodePiece> javaPieces, List<ICodePiece> jsPieces) {
		if (lang1Father.getPieceByType(PieceType.TYPE).getPieceByType(PieceType.TYPE).toString()
				.equals("float")
				&& lang2Father.getPieceByType(PieceType.NAME).toString().equals("Float32Array")) {
			if (new ArrayContentComparator().compare(lang1Father.getPieceByType(PieceType.ARRAY_CONTENT),
					lang2Father.getPieceByType(PieceType.ARRAY_CONTENT))) {
				return true;
			}
		}
		return false;
	}

}
