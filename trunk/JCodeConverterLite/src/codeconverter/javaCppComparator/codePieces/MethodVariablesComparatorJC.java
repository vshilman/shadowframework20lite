package codeconverter.javaCppComparator.codePieces;

import java.util.List;

import codeconverter.ICodePiece;
import codeconverter.PieceType;
import codeconverter.comparator.CodePieceComparator;
import codeconverter.javaJsComparator.codePieces.VariableComparator;

public class MethodVariablesComparatorJC extends CodePieceComparator{

	@Override
	protected boolean internalCompare(List<ICodePiece> javaPieces,
			List<ICodePiece> cppPieces) {
		if (javaPieces.size() != cppPieces.size()) {
			return false;
		}
		for (int j = 0; j < javaPieces.size(); j++) {
			if (javaPieces.get(j).getPieceType() == PieceType.VARIABLE) {
				if (!new VariableComparatorJC().compare(javaPieces.get(j),cppPieces.get(j))) {
					return false;
				}
			}
		}
		return true;
	}

}
