package codeconverter;

import java.util.ArrayList;
import java.util.List;

/**
 * A Piece of Code which can identify itself on a String 
 * 
 * @author Alessandro Martinelli
 */
public abstract class ICodePiece{
	
	/**
	 * An Object which stores the position at which
	 * a CodePiece has been matched. It also 
	 * contains a data Piece which describes the match 
	 * 
	 * @author Alessandro
	 */
	public class ICodePieceMatch{
		
		private int matchPosition;
		private ICodePiece dataPiece;
		
		public ICodePieceMatch(int line, ICodePiece dataPiece) {
			super();
			this.matchPosition=line;
			this.dataPiece=dataPiece;
		}

		public int getMatchPosition() {
			return matchPosition;
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
	
	/**
	 * The list of all pieces which this piece uses
	 * @return
	 */
	public List<ICodePiece> getPieces(){
		return pieces;
	}
	
	
	protected String writeSonsPieces(String div){
		String data="";
		if(pieces.size()>0) {
			data+=pieces.get(0).toString();
			for (int i=1; i < pieces.size(); i++) {
				data+=div+pieces.get(i).toString();
			}
		}
		return data;
	}
	
	
	/**
	 * @return the type of this piece
	 */
	public PieceType getPieceType() {
		return pieceType;
	}

	/**
	 * @param pieceType the type to set for this piece
	 */
	public void setPieceType(PieceType pieceType) {
		this.pieceType=pieceType;
	}

	/**
	 * Find a Piece having a specific type within this Piece sub-pieces list
	 * @param type
	 * @return
	 */
	public ICodePiece getPieceByType(PieceType type){
		for (ICodePiece codePiece : pieces) {
			if(codePiece.getPieceType()==type)
				return codePiece;
		}
		for (ICodePiece codePiece : pieces) {
			ICodePiece subPiece=codePiece.getPieceByType(type);
			if(subPiece!=null)
				return subPiece;
		}
		return null;
	}

	@Override
	public String toString() {
		return writeSonsPieces("");
	}

	/**
	 * Add sub-pieces to this piece
	 * @param pieces
	 */
	public void add(ICodePiece... pieces) {
		for (ICodePiece iCodePiece : pieces) {
			this.pieces.add(iCodePiece);
		}
	}
	
	public String printTypes(int nt){
		String data="";
		for (int i = 0; i < nt; i++) {
			data+="\t";
		}
		data+=getPieceType()+":"+this+"\n";
		nt++;
		if(pieces.size()>0) {
			for (int i = 0; i < nt; i++) {
				data+="\t";
			}
			data+=pieces.get(0).printTypes(nt);
			for (int i=1; i < pieces.size(); i++) {
				for (int j = 0; j < nt; j++) {
					data+="\t";
				}
				data+=pieces.get(i).printTypes(nt);
			}
		}
		return data;
	}
}