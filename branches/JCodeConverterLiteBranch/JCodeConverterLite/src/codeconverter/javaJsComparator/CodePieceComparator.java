package codeconverter.javaJsComparator;

import java.util.List;

import codeconverter.ICodePiece;

public abstract class CodePieceComparator {

	protected ICodePiece javaFather;
	protected ICodePiece jsFather;

	public boolean compare(ICodePiece javaCodePiece, ICodePiece jsCodePiece) {
		javaFather = javaCodePiece;
		jsFather = jsCodePiece;
		if (javaCodePiece == null || jsCodePiece == null) {
			return false;
		}
		if (javaCodePiece.getPieceType() != jsCodePiece.getPieceType()) {
			return false;
		}
		return internalCompare(javaCodePiece.getPieces(), jsCodePiece.getPieces());
	}

	protected abstract boolean internalCompare(List<ICodePiece> javaPieces, List<ICodePiece> jsPieces);

}
