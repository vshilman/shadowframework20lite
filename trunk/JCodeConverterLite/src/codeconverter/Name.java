package codeconverter;

public class Name extends Value{

	@Override
	public int elementMatch(String data, int matchPosition) {
		char[] datac=data.toCharArray();
		int startingPosition=matchPosition;
		if (stays(datac[matchPosition],'a','z')
				|| stays(datac[matchPosition],'A','Z')) {
			while (stays(datac[matchPosition],'a','z')
					|| stays(datac[matchPosition],'A','Z')
					|| stays(datac[matchPosition],'0','9')
					|| stays(datac[matchPosition],'_','_')) {
				matchPosition++;
			}
			this.data=data.substring(startingPosition,matchPosition);
			return matchPosition;
		}
		
		return -1;
	}
	
	@Override
	public PieceType getPieceType() {
		return PieceType.NAME;
	}
}
