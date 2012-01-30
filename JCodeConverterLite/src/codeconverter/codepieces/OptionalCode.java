package codeconverter.codepieces;

import codeconverter.ICodePiece;
import codeconverter.PieceType;
import codeconverter.ICodePiece.ICodePieceMatch;

/**
 * Note: implements Design Pattern Decorator
 * 
 * @author Alessandro Martinelli
 */
public class OptionalCode  extends ICodePiece {

	private ICodePiece optionaElement;
	
	public OptionalCode(ICodePiece optionaElement) {
		this.optionaElement=optionaElement;
	}

	@Override
	public ICodePieceMatch elementMatch(String data, int matchPosition) {
		
		ICodePieceMatch match=optionaElement.elementMatch(data,matchPosition);
		if(match.getMatchPosition()==-1){
			return new ICodePieceMatch(matchPosition,new Word(PieceType.IGNORED,"",null));
		}
		return match;
	}	
	
	@Override
	public PieceType getPieceType() {
		return optionaElement.getPieceType();
	}
}
