package codeconverter.codepieces;

import codeconverter.ICodePiece;
import codeconverter.PieceType;
import codeconverter.ICodePiece.ICodePieceMatch;


public class CodeSequence extends ICodePiece {

	private ICodePiece element;
	private String div;

	public CodeSequence(ICodePiece element, String div) {
		super();
		this.element=element;
		this.div=div;
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
		CodeSequence cloneSequence=new CodeSequence(null,",");
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
					cloneSequence.pieces.add(new Word(PieceType.KEYWORD,",",null));
				cloneSequence.pieces.add((ICodePiece) match.getDataPiece());
				position=nextIndex;
			}
			while (position<datac.length && isDiv(datac[position])) {
				position++;
			}
		} while (nextIndex != -1);

		return new ICodePieceMatch(position,cloneSequence);
	}
	
}
