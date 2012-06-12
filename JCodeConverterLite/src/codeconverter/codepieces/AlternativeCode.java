package codeconverter.codepieces;

import codeconverter.ICodePiece;
import codeconverter.PieceType;


/**
 * An alternative Between a List of Code
 * 
 * This piece will match when any of its sub-pieces matches
 * 
 * @author Alessandro Martinelli
 */
public class AlternativeCode extends ICodePiece{

	private boolean mandatory;
	
	public AlternativeCode(boolean mandatory,ICodePiece... pieces){
		for (ICodePiece iCodePiece : pieces) {
			this.pieces.add(iCodePiece);
		}
		this.mandatory=mandatory;
		setPieceType(PieceType.SEQUENCE);
	}
	
	@Override
	public ICodePieceMatch elementMatch(String data, int matchPosition) {
	
		for (ICodePiece piece : pieces) {
			ICodePieceMatch match=piece.elementMatch(data,matchPosition);
			if(match.getMatchPosition()!=-1){
				return match;
			}
		}
		if(mandatory){
			return new ICodePieceMatch(-1,null);
		}else{
			return new ICodePieceMatch(matchPosition,new Word(PieceType.IGNORED,"",null));
		}
	}	
}