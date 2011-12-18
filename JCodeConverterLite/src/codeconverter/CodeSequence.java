package codeconverter;

import java.util.ArrayList;
import java.util.List;

public class CodeSequence implements ICodePiece {

	private ICodePiece element;
	private String div;
	private List<ICodePiece> pieces=new ArrayList<ICodePiece>();

	private CodeSequence() {
		super();
	}

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
	public int elementMatch(String data, int matchPosition) {
		pieces.clear();
		char[] datac=data.toCharArray();
		int position=matchPosition;
		int nextIndex=0;
		do {
			nextIndex=element.elementMatch(data,position);
			if (nextIndex != -1) {
				pieces.add((ICodePiece) element.cloneCodePiece());
				position=nextIndex;
			}
			while (isDiv(datac[position])) {
				position++;
			}
		} while (nextIndex != -1);

		return position;
	}

	public List<ICodePiece> getPieces() {
		return pieces;
	}

	@Override
	public ICodePiece cloneCodePiece() {
		CodeSequence sequence=new CodeSequence();
		for (int i=0; i < pieces.size(); i++) {
			ICodePiece cloned=(ICodePiece)pieces.get(i);
			sequence.pieces.add(cloned);
		}
		//sequence.pieces.addAll(pieces);//something going
		return sequence;
	}
}
