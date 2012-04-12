package codeconverter.javaJsComparator.codePieces;

import java.util.List;

import codeconverter.ICodePiece;
import codeconverter.PieceType;
import codeconverter.javaJsComparator.CodePieceComparator;

public class MethodVariablesComparator extends CodePieceComparator {

	@Override
	protected boolean internalCompare(List<ICodePiece> javaPieces, List<ICodePiece> jsPieces) {
		if (javaPieces.size() != jsPieces.size()) {
			return false;
		}
		for (int j = 0; j < javaPieces.size(); j++) {
			if (javaPieces.get(j).getPieceType() == PieceType.VARIABLE) {
				if (!javaPieces.get(j).getPieces().get(1).toString().equals(jsPieces.get(j).toString())) {
					return false;
				}
			}
		}
		return true;
	}

}
