package codeconverter.javaJsComparator.codePieces;

import java.util.List;

import codeconverter.ICodePiece;
import codeconverter.comparator.CodePieceComparator;

public class OpenGlConstantComparator extends CodePieceComparator {

	@Override
	protected boolean internalCompare(List<ICodePiece> javaPieces, List<ICodePiece> jsPieces) {
		if (!javaPieces.get(2).toString().equals(jsPieces.get(1).toString())) {
			return false;
		}
		return true;
	}

}
