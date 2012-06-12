package codeconverter.codepieces;

import codeconverter.ICodePiece;
import codeconverter.PieceType;

/**
 * An alternative Between a List of Code
 * 
 * This piece will match when any of its sub-pieces matches
 * 
 * The longest match will always be taken as match
 * 
 * @author Alessandro Martinelli
 */
public class BestAlternativeCode extends ICodePiece {

	private boolean mandatory;

	public BestAlternativeCode(boolean mandatory, ICodePiece... pieces) {
		for (ICodePiece iCodePiece : pieces) {
			this.pieces.add(iCodePiece);
		}
		this.mandatory = mandatory;
		setPieceType(PieceType.SEQUENCE);
	}

	@Override
	public ICodePieceMatch elementMatch(String data, int matchPosition) {

		ICodePieceMatch bestMatch = new ICodePieceMatch(-1, null);
		for (ICodePiece piece : pieces) {
			ICodePieceMatch match = piece.elementMatch(data, matchPosition);
			if (match.getMatchPosition() >= bestMatch.getMatchPosition()) {
				bestMatch = match;
			}
		}
		if (bestMatch.getMatchPosition() == -1 && !mandatory) {
			return new ICodePieceMatch(matchPosition, new Word(PieceType.IGNORED, "", null));
		}

		return bestMatch;
	}
}