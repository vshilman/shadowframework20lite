package codeconverter.codepieces;

import codeconverter.ICodePiece;
import codeconverter.PieceType;

/**
 * A Sequence of Code Piece. 
 * 
 * This Piece will match when an array is found of elements matching 
 * the same element CodePiece, separated by a division char.
 * So it matches sequence like
 * 
 * 				element,element,element
 * 
 * Note: all element must match the same match 
 * 
 * @author Alessandro Martinelli
 */
public class CodeSequence extends ICodePiece {

	private ICodePiece element;
	private String div;
	private boolean mandatory=false;

	public CodeSequence(boolean mandatory,ICodePiece element, String div) {
		super();
		this.element=element;
		this.div=div;
		this.mandatory=mandatory;
		setPieceType(PieceType.SEQUENCE);
	}
	
	public CodeSequence(ICodePiece element, String div) {
		super();
		this.element=element;
		this.div=div;
		setPieceType(PieceType.SEQUENCE);
	}

	private boolean isDiv(char c) {
		for (int i=0; i < div.length(); i++) {
			if (div.charAt(i) == c)
				return true;
		}

		return false;
	}
	
	@Override
	public ICodePieceMatch elementMatch(String data, int matchPosition) {
		CodeSequence cloneSequence=new CodeSequence(false,null,div);
		cloneSequence.setPieceType(getPieceType());
		char[] datac=data.toCharArray();
		int position=matchPosition;
		int nextIndex=0;
		do {
			ICodePieceMatch match=element.elementMatch(data,position);
			nextIndex=match.getMatchPosition();
			if(nextIndex==position){
				break;
			}
			if (nextIndex != -1) {
				if(position!=matchPosition)
					cloneSequence.pieces.add(new Word(PieceType.KEYWORD,div,null));
				cloneSequence.pieces.add((ICodePiece) match.getDataPiece());
				position=nextIndex;
			}
			while (position<datac.length && isDiv(datac[position])) {
				position++;
			}
		} while (nextIndex != -1);

		if(mandatory)
			if(cloneSequence.getPieces().size()==0)
				return new ICodePieceMatch(-1,null);
		
		return new ICodePieceMatch(position,cloneSequence);
	}
	
}
