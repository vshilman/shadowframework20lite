package codeconverter.js;

import codeconverter.ICodePiece;

public class JsName implements ICodePiece{

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
			
			if(JsKeywords.isKeyword(this.data))
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
		JsName name = new JsName();
		name.data=new String(data);
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