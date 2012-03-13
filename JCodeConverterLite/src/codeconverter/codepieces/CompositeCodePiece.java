package codeconverter.codepieces;

import codeconverter.ICodePiece;
import codeconverter.PieceType;

/**
 * 
 * This piece will match when all its sub-pieces matches 
 * one after the other
 * 
 * @author Alessandro 
 */
public class CompositeCodePiece extends ICodePiece{

	public CompositeCodePiece(ICodePiece... pieces){
		for (ICodePiece iCodePiece : pieces) {
			this.pieces.add(iCodePiece);
		}
	}
	
	@Override
	public ICodePieceMatch elementMatch(String data, int matchPosition) {
	
		char[] dataChars=data.toCharArray();
		CompositeCodePiece clone=new CompositeCodePiece();
		clone.setPieceType(getPieceType());
		
		for (ICodePiece piece : pieces) {
			ICodePieceMatch match=piece.elementMatch(data,matchPosition);
			if(match.getMatchPosition()==-1){
				return new ICodePieceMatch(-1,null);
			}
			clone.pieces.add(match.getDataPiece());
			matchPosition=match.getMatchPosition();
			while(matchPosition< dataChars.length && 
					(dataChars[matchPosition]==' ' || dataChars[matchPosition]=='\t')){
				clone.pieces.add(new Word(PieceType.IGNORED,dataChars[matchPosition]+" ",null));
				matchPosition++;
			}
		}
		return new ICodePieceMatch(matchPosition,clone);
	}
	
}
