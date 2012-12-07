package codeconverter.codepieces;

import codeconverter.ICodePiece;
import codeconverter.PieceType;

/**
 * A Generic Word which can represent different Code Piece
 * 
 * @author Alessandro Martinelli
 */
public class Word extends ICodePiece{

	private String word;
	
	public Word(PieceType pieceType, String word) {
		super();
		this.pieceType=pieceType;
		this.word=word;
	}

	@Override
	public ICodePieceMatch elementMatch(String data, int matchPosition) {
		return new ICodePieceMatch(-1,null);
	}
	
	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word=word;
	}

	@Override
	public String toString() {
		return word;
	}
}
