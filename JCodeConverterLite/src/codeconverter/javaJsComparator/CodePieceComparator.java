package codeconverter.javaJsComparator;

import java.util.List;

import codeconverter.ICodePiece;

public abstract class CodePieceComparator {

	public boolean compare(ICodePiece javaCodePiece, ICodePiece jsCodePiece) {
		if (javaCodePiece.getPieceType() != jsCodePiece.getPieceType()) {
			return false;
		}
		return internalCompare(javaCodePiece.getPieces(), jsCodePiece.getPieces());
	}

	protected abstract boolean internalCompare(List<ICodePiece> javaPieces, List<ICodePiece> jsPieces);

}
