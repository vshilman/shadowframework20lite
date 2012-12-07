package codeconverter.codepieces;

import java.util.List;

import codeconverter.ICodePiece;

/**
 * Values are pieces with a specific character pattern.
 * 
 * @author Alessandro Martinelli
 */
public abstract class Value extends ICodePiece{

	protected static class CharInterval{
		protected char minChar;
		protected char maxChar;
		public CharInterval(char minChar, char maxChar) {
			super();
			this.minChar=minChar;
			this.maxChar=maxChar;
		}
		protected boolean stays(char eval) {
			return (minChar<=eval && eval <=maxChar);
		}
	}
	
	public abstract List<CharInterval> getAvailableIntervals(int position);
	public abstract List<CharInterval> getEndCharacter();
	
	protected String data="";

	private boolean isCharacterContained(char c,List<CharInterval> intervals){
		for (CharInterval charInterval : intervals) {
			if(charInterval.stays(c))
				return true;
		}
		return false;
	}
	
	@Override
	public ICodePieceMatch elementMatch(String data, int matchPosition) {
		char[] datac=data.toCharArray();
		int startingPosition=matchPosition;
		List<CharInterval> endCharacters=getEndCharacter();
		List<CharInterval> availableIntervals=getAvailableIntervals(matchPosition-startingPosition);
		if(matchPosition<datac.length){
			if (isCharacterContained(datac[matchPosition],availableIntervals)) {
				while (matchPosition<datac.length && isCharacterContained(datac[matchPosition],availableIntervals) 
						&& !isCharacterContained(datac[matchPosition],endCharacters)) {
					matchPosition++;
					availableIntervals=getAvailableIntervals(matchPosition-startingPosition);
				}
				String word=data.substring(startingPosition,matchPosition);
				return new ICodePieceMatch(matchPosition,new Word(getPieceType(),word));
			}	
		}
		
		return new ICodePieceMatch(-1,null);
	}

	public Value() {
		super();
	}

	
	

}