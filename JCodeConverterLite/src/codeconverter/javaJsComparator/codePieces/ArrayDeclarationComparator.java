package codeconverter.javaJsComparator.codePieces;

import java.util.List;

import codeconverter.ICodePiece;
import codeconverter.PieceType;
import codeconverter.javaJsComparator.CodePieceComparator;

public class ArrayDeclarationComparator extends CodePieceComparator {

	@Override
	protected boolean internalCompare(List<ICodePiece> javaPieces, List<ICodePiece> jsPieces) {
		if (javaFather.getPieceByType(PieceType.TYPE).getPieceByType(PieceType.TYPE).toString()
				.equals("float")
				&& jsFather.getPieceByType(PieceType.NAME).toString().equals("Float32Array")) {
			if (new ArrayContentComparator().compare(javaFather.getPieceByType(PieceType.ARRAY_CONTENT),
					jsFather.getPieceByType(PieceType.ARRAY_CONTENT))) {
				return true;
			}
		}
		return false;
	}

}
