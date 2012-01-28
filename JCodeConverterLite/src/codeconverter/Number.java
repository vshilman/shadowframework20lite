package codeconverter;

public class Number extends Value{

	@Override
	public int elementMatch(String data, int matchPosition) {
		char[] datac=data.toCharArray();
		int startingPosition=matchPosition;
		if(matchPosition<datac.length){
			if (stays(datac[matchPosition],'0','9')) {
				while (matchPosition<datac.length && (stays(datac[matchPosition],'a','z')
						|| stays(datac[matchPosition],'A','Z')
						|| stays(datac[matchPosition],'0','9'))) {
					matchPosition++;
				}
				this.data=data.substring(startingPosition,matchPosition);
				return matchPosition;
			}	
		}
		
		return -1;
	}

	@Override
	public PieceType getPieceType() {
		return PieceType.VALUE;
	}
}