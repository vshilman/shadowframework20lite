package codeconverter;

import java.util.ArrayList;
import java.util.List;

public class AlternativeCode implements ICodePiece, ICodePieceSequencer{

	private List<ICodePiece> pieces=new ArrayList<ICodePiece>();
	
	public AlternativeCode(ICodePiece... pieces){
		for (ICodePiece iCodePiece : pieces) {
			this.pieces.add(iCodePiece);
		}
	}
	
	public void add(ICodePiece... pieces){
		for (ICodePiece iCodePiece : pieces) {
			this.pieces.add(iCodePiece);
		}
	}
	
	
	
	public List<ICodePiece> getPieces() {
		return pieces;
	}

	@Override
	public PieceType getPieceType() {
		return PieceType.SEQUENCE;
	}
	
	@Override
	public int elementMatch(String data, int matchPosition) {
	
		for (ICodePiece piece : pieces) {
			int match=piece.elementMatch(data,matchPosition);
			if(match!=-1)
				return match;
		}	
		
		return -1;
	}
	
	@Override
	public ICodeElement cloneCodePiece() {
		AlternativeCode alternative=new AlternativeCode();
		
		for (int i=0; i < pieces.size(); i++) {
			ICodePiece cloned=(ICodePiece)pieces.get(i).cloneCodePiece();
			alternative.pieces.add(cloned);
		}
		return alternative;
	}
}
