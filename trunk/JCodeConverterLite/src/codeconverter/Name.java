package codeconverter;

public class Name implements ICodePiece{

	protected String data="";
	
	@Override
	public int elementMatch(String data, int matchPosition) {
		char[] datac=data.toCharArray();
		int startingPosition=matchPosition;
		if (stays(datac[matchPosition],'a','z')
				|| stays(datac[matchPosition],'A','Z')) {
			while (stays(datac[matchPosition],'a','z')
					|| stays(datac[matchPosition],'A','Z')
					|| stays(datac[matchPosition],'0','9')) {
				matchPosition++;
			}
			this.data=data.substring(startingPosition,matchPosition);
			return matchPosition;
		}
		
		return -1;
	}
	
	private boolean stays(char eval,char min,char max){
		return (min<=eval && eval <=max);
	}

	@Override
	public ICodePiece cloneCodePiece() {
		Name name = new Name();
		name.data=data;
		return name;
	}

	public String getData() {
		return data;
	}
	
	@Override
	public String toString() {
		return data;
	}
}
