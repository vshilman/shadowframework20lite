package codeconverter.codepieces;

import codeconverter.ICodePiece;
import codeconverter.PieceType;
import codeconverter.ICodePiece.ICodePieceMatch;


/**
 * An alternative Between a List of Code
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
	}
	
	@Override
	public PieceType getPieceType() {
		return PieceType.SEQUENCE;
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