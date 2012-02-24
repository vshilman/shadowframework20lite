package codeconverter;

import java.util.ArrayList;
import java.util.List;

/**
 * A Piece of Code which can identify itself on a String 
 * 
 * @author Alessandro Martinelli
 */
public abstract class ICodePiece{
	
	public class ICodePieceMatch{
		
		private int matcPosition;
		private ICodePiece dataPiece;
		
		public ICodePieceMatch(int line, ICodePiece dataPiece) {
			super();
			this.matcPosition=line;
			this.dataPiece=dataPiece;
		}

		public int getMatchPosition() {
			return matcPosition;
		}

		public ICodePiece getDataPiece() {
			return dataPiece;
		}
	}

	protected List<ICodePiece> pieces=new ArrayList<ICodePiece>();
	protected PieceType pieceType;
	
	/**
	 * Verifiy if this CodePiece is present in the String starting from the matchPosition location
	 * 
	 * Return -1 if there is no match
	 * 
	 * @param data
	 * @param matchPosition
	 * @return
	 */
	public abstract ICodePieceMatch elementMatch(String data, int matchPosition);
	

	public List<ICodePiece> getPieces(){
		return pieces;
	}
	
	protected String writeSonsPieces(){
		String data="";
		if(pieces.size()>0) {
			data+=pieces.get(0).toString();
			for (int i=1; i < pieces.size(); i++) {
				data+=pieces.get(i).toString();
			}
		}
		return data;
	}
	
	
	
	public PieceType getPieceType() {
		return pieceType;
	}


	public void setPieceType(PieceType pieceType) {
		this.pieceType=pieceType;
	}

	public ICodePiece getPieceByType(PieceType type){
		for (ICodePiece codePiece : pieces) {
			if(codePiece.getPieceType()==type)
				return codePiece;
		}
		return null;
	}

	@Override
	public String toString() {
		return writeSonsPieces();
	}

	public void add(ICodePiece... pieces) {
		for (ICodePiece iCodePiece : pieces) {
			this.pieces.add(iCodePiece);
		}
	}
}