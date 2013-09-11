package codeconverter.comparator;

import java.util.List;

import codeconverter.ICodePiece;

public abstract class CodePieceComparator {

	protected ICodePiece lang1Father;
	protected ICodePiece lang2Father;

	public boolean compare(ICodePiece lang1CodePiece, ICodePiece lang2CodePiece) {
		lang1Father = lang1CodePiece;
		lang2Father = lang2CodePiece;
		if (lang1CodePiece == null || lang2CodePiece == null) {
			return false;
		}
		if (lang1CodePiece.getPieceType() != lang2CodePiece.getPieceType()) {
			return false;
		}
		return internalCompare(lang1CodePiece.getPieces(), lang2CodePiece.getPieces());
	}

	protected abstract boolean internalCompare(List<ICodePiece> lang1Pieces, List<ICodePiece> lang2Pieces);

}
