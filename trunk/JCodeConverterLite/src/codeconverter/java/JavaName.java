package codeconverter.java;

import codeconverter.ICodePiece;
import codeconverter.Name;

public class JavaName implements ICodePiece{

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
			
			if(JavaKeywords.isKeyword(this.data))
				return -1;
			
			return matchPosition;
		}
		
		return -1;
	}
	
	private boolean stays(char eval,char min,char max){
		return (min<=eval && eval <=max);
	}

	@Override
	public ICodePiece cloneCodePiece() {
		JavaName name = new JavaName();
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
