package codeconverter.codepieces;

import codeconverter.ICodePiece;
import codeconverter.PieceType;

/**
 * A Generic Word which can represent different Code Piece
 * 
 * @author Alessandro Martinelli
 */
public class Word extends ICodePiece{

	private PieceType pieceType;
	private String word;
	private ICodePiece wordBuilder=null; 
	
	public Word(PieceType pieceType, String word, ICodePiece wordBuilder) {
		super();
		this.pieceType=pieceType;
		this.word=word;
		this.wordBuilder=wordBuilder;
	}

	@Override
	public ICodePieceMatch elementMatch(String data, int matchPosition) {
		if(wordBuilder!=null){
			return wordBuilder.elementMatch(data,matchPosition);
		}
		return new ICodePieceMatch(-1,null);
	}

	public PieceType getPieceType() {
		return pieceType;
	}

	public void setPieceType(PieceType pieceType) {
		this.pieceType=pieceType;
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
