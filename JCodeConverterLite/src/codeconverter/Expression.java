package codeconverter;

import java.util.ArrayList;
import java.util.List;

public abstract class Expression implements ICodePiece{

	public abstract String[] getExpressionSeparators();
	
	public abstract char getOpenBracketSymbol();
	
	public abstract char getClosedBracketSymbol();

	protected List<ICodePiece> availablePieces=new ArrayList<ICodePiece>(); 
	private AlternativeCode alternatives;
	
	private int keywordIndex;
	
	public Expression(ICodePiece... piece){
		for (ICodePiece iCodePiece : piece) {
			availablePieces.add(iCodePiece);
		}
	}
	

	private int checkSeparators(String data, int matchPosition,
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
	
	
	private int matchSymbol(String data,char[] dataChars, int matchPosition){
		String[] separators=getExpressionSeparators();
		return checkSeparators(data, matchPosition, separators);
	}
	
	private int alternativeMatch(String data,char[] dataChars, int matchPosition){
		
		char open=getOpenBracketSymbol();
		while(matchPosition<dataChars.length && (dataChars[matchPosition]==open || dataChars[matchPosition]==' '))
			matchPosition++;
		
		matchPosition = alternatives.elementMatch(data,matchPosition+1);
		
		if(matchPosition!=-1){
			char closed=getClosedBracketSymbol();
			while(matchPosition<dataChars.length && (dataChars[matchPosition]==closed || dataChars[matchPosition]==' '))
				matchPosition++;
		}
		
		return -1;
	}
	
	@Override
	public int elementMatch(String data, int matchPosition) {
		char[] dataChars=data.toCharArray();
		
		if(alternatives==null){
			alternatives=new AlternativeCode();
			for (ICodePiece piece : availablePieces) {
				alternatives.add(piece);
			}
		}
		
		matchPosition=alternativeMatch(data,dataChars,matchPosition);
		if(matchPosition==-1){
			return -1;
		}
		
		int matchAlternative=matchPosition;
		while(matchAlternative!=-1){
			matchPosition=matchAlternative;
			matchAlternative=matchSymbol(data,dataChars,matchAlternative);
			matchAlternative=alternativeMatch(data,dataChars,matchAlternative);
		}
		
		return matchAlternative;
	}
	
	@Override
	public ICodeElement cloneCodePiece() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
