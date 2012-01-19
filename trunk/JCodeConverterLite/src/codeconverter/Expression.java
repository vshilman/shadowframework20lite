package codeconverter;

import java.util.ArrayList;
import java.util.List;

import codeconverter.java.JavaName;

/**
 * A Generic Expresssion
 * @author Alessandro Martinelli
 */
public abstract class Expression implements ICodePiece, ICodePieceSequencer{

	public abstract String[] getExpressionSeparators();
	
	public abstract char getOpenBracketSymbol();
	
	public abstract char getClosedBracketSymbol();

	protected List<ICodePiece> availablePieces=new ArrayList<ICodePiece>(); 
	
	private AlternativeCode alternatives;
	
	protected String representation;
	
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
	
	
	
	@Override
	public PieceType getPieceType() {
		return PieceType.SEQUENCE;
	}
	
	
	private int matchSymbol(String data,char[] dataChars, int matchPosition){
		String[] separators=getExpressionSeparators();
		return checkSeparators(data, matchPosition, separators);
	}
	
	private int alternativeMatch(String data,char[] dataChars, int matchPosition){
		
		char open=getOpenBracketSymbol();
		
		if(!(matchPosition==-1)){
		
			while(matchPosition<dataChars.length && (dataChars[matchPosition]==open || dataChars[matchPosition]==' '))
				matchPosition++;
			
			matchPosition = alternatives.elementMatch(data,matchPosition);
			
			if(matchPosition!=-1){
				char closed=getClosedBracketSymbol();
				while(matchPosition<dataChars.length && (dataChars[matchPosition]==closed || dataChars[matchPosition]==' '))
					matchPosition++;
			}
		}
		
		return matchPosition;
	}
	
	@Override
	public int elementMatch(String data, int matchPosition) {
		
		int startingMatchPosition=matchPosition;
		
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
		
		representation=data.substring(startingMatchPosition,matchPosition);
		
		return matchPosition;
	}
	
	
	@Override
	public String toString() {
		return representation;
	}
	
}
