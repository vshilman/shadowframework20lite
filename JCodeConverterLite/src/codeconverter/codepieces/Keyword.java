package codeconverter.codepieces;

import java.util.ArrayList;

import codeconverter.ICodePiece;
import codeconverter.PieceType;
import codeconverter.ICodePiece.ICodePieceMatch;

/**
 * A KeyWord, that's a specific which can be one of more alternatives.
 * 
 * @author Alessandro Martinelli
 */
public abstract class Keyword extends ICodePiece{

	/**
	 * @return an ArrayList containing all the alternatives. 
	 */
	public abstract ArrayList<String> getAlternatives();
	protected int keywordIndex=0;
	private PieceType pieceType=PieceType.KEYWORD;

	@Override
	public ICodePieceMatch elementMatch(String data, int matchPosition) {
		ArrayList<String> alternatives=getAlternatives();
		int line=checkAlternatives(data,matchPosition,alternatives);
		return new ICodePieceMatch(line,new Word(PieceType.KEYWORD,alternatives.get(keywordIndex),null));
	}

	public PieceType getPieceType() {
		return pieceType;
	}

	public void setPieceType(PieceType pieceType) {
		this.pieceType=pieceType;
	}

	private int checkAlternatives(String data, int matchPosition,
			ArrayList<String> alternatives) {
		keywordIndex=0;
		for (int i = 0; i < alternatives.size(); i++) {
			String alternative=alternatives.get(i);
			if(data.length()>=matchPosition+alternative.length()){
				if(data.substring(matchPosition, matchPosition+alternative.length()).equals(alternative)){	
					keywordIndex=i;
					return alternative.length()+matchPosition;
				}	
			}
		}
		
		return -1;
	}
}
