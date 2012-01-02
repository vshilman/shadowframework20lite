package codeconverter;

public abstract class UnInterpretedCode implements ICodePiece{

	public abstract boolean isCharacterOk(char c);
	
	public abstract char lastCharacter();
	
	private ICodePiece followingPiece;

	public UnInterpretedCode(ICodePiece followingPiece) {
		super();
		this.followingPiece=followingPiece;
	}
	
	@Override
	public int elementMatch(String data, int matchPosition) {
	
		char[] dataC=data.toCharArray();
		
		boolean go=true;
		
		while(go && matchPosition<data.length()){
			if(!isCharacterOk(dataC[matchPosition])){
				int matching=followingPiece.elementMatch(data,matchPosition);
				if(matching==-1){
					return -1;
				}
				go=false;
			}else{
				matchPosition++;
			}
		}
		if(matchPosition==data.length())
			if(dataC[matchPosition-1]!=lastCharacter())
				return -1;
		
		return matchPosition;
	}
}
