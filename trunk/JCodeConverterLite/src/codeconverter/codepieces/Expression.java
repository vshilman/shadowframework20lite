package codeconverter.codepieces;

import codeconverter.ICodePiece;
import codeconverter.PieceType;
import codeconverter.ICodePiece.ICodePieceMatch;


/**
 * A Generic Expresssion
 * @author Alessandro Martinelli
 */
public abstract class Expression extends ICodePiece{

	public abstract String[] getExpressionSeparators();
	
	private AlternativeCode alternatives;
	
	public Expression(ICodePiece... piece){
		for (ICodePiece iCodePiece : piece) {
			pieces.add(iCodePiece);
		}
	}

	private int checkSeparators(String data, int matchPosition,
			String[] alternatives) {
		for (int i = 0; i < alternatives.length; i++) {
			String alternative=alternatives[i];
			if(data.length()>=matchPosition+alternative.length()){
				if(data.substring(matchPosition, matchPosition+alternative.length()).equals(alternative)){	
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
		
		//char open=getOpenBracketSymbol();
		
		if(!(matchPosition==-1)){
		
			while(matchPosition<dataChars.length && (dataChars[matchPosition]==' '))
				matchPosition++;
			
			 ICodePieceMatch pieceMatch = alternatives.elementMatch(data,matchPosition);
			 matchPosition=pieceMatch.getMatchPosition();
			
			if(matchPosition!=-1){
				while(matchPosition<dataChars.length && (dataChars[matchPosition]==' '))
					matchPosition++;
			}
		}
		
		return matchPosition;
	}
	
	@Override
	public ICodePieceMatch elementMatch(String data, int matchPosition) {
		
		int startingMatchPosition=matchPosition;
		
		char[] dataChars=data.toCharArray();
		
		if(alternatives==null){
			alternatives=new AlternativeCode(false);
			for (ICodePiece piece : pieces) {
				alternatives.add(piece);
			}
		}
		
		matchPosition=alternativeMatch(data,dataChars,matchPosition);
		if(matchPosition==-1){
			return new ICodePieceMatch(-1,null);
		}
		
		int matchAlternative=matchPosition;
		while(matchAlternative!=-1){
			matchPosition=matchAlternative;
			matchAlternative=matchSymbol(data,dataChars,matchAlternative);
			matchAlternative=alternativeMatch(data,dataChars,matchAlternative);
		}
		
		String representation=data.substring(startingMatchPosition,matchPosition);
		
		return new ICodePieceMatch(matchPosition,new Word(PieceType.VALUE,representation,null));  
	}
	
}
