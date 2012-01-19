package codeconverter;

public abstract class Keyword implements ICodePiece{

	public abstract String[] getAlternatives();
	
	protected int keywordIndex;
	
	@Override
	public int elementMatch(String data, int matchPosition) {
		String[] alternatives=getAlternatives();
		return checkAlternatives(data, matchPosition, alternatives);
	}

	@Override
	public PieceType getPieceType() {
		return PieceType.KEYWORD;
	}
	
	private int checkAlternatives(String data, int matchPosition,
			String[] alternatives) {
		for (int i = 0; i < alternatives.length; i++) {
			String alternative=alternatives[i];
			if(data.length()>=matchPosition+alternative.length()){
				if(data.substring(matchPosition, matchPosition+alternative.length()).equals(alternative)){	
					keywordIndex=i;
					return alternative.length()+matchPosition;
				}	
			}
		}
		
		return -1;
	}
	
}
