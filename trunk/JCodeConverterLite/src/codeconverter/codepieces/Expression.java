package codeconverter.codepieces;

import codeconverter.ICodePiece;
import codeconverter.PieceType;


/**
 * A Generic Expresssion
 * @author Alessandro Martinelli
 */
public abstract class Expression extends ICodePiece{

	/**
	 * TODO
	 * @return
	 */
	public abstract String[] getExpressionSeparators();
	
	private BestAlternativeCode alternatives;
	
	public Expression(ICodePiece... piece){
		for (ICodePiece iCodePiece : piece) {
			pieces.add(iCodePiece);
		}
	}

	private int checkSeparators(String data, int matchPosition,
			String[] alternatives,Expression clone) {
		for (int i = 0; i < alternatives.length; i++) {
			String alternative=alternatives[i];
			if(data.length()>=matchPosition+alternative.length()){
				if(data.substring(matchPosition, matchPosition+alternative.length()).equals(alternative)){
					clone.pieces.add(new Word(PieceType.OPERATOR,alternative,null));
					return alternative.length()+matchPosition;
				}	
			}
		}
		
		return -1;
	}
	
	
	
	@Override
	public PieceType getPieceType() {
		return PieceType.EXPRESSION;
	}
	
	
	private int matchSymbol(String data,char[] dataChars, int matchPosition,Expression clone){
		String[] separators=getExpressionSeparators();
		return checkSeparators(data, matchPosition, separators,clone);
	}
	
	private int alternativeMatch(String data,char[] dataChars, int matchPosition,Expression clone){
		
		//char open=getOpenBracketSymbol();
		
		if(!(matchPosition==-1)){
		
			while(matchPosition<dataChars.length && (dataChars[matchPosition]==' '))
				matchPosition++;
			
			ICodePieceMatch pieceMatch = alternatives.elementMatch(data,matchPosition);
			matchPosition=pieceMatch.getMatchPosition();
			clone.pieces.add(pieceMatch.getDataPiece());
			
			if(matchPosition!=-1){
				while(matchPosition<dataChars.length && (dataChars[matchPosition]==' '))
					matchPosition++;
			}
		}
		
		return matchPosition;
	}
	
	@Override
	public ICodePieceMatch elementMatch(String data, int matchPosition) {
		
		//int startingMatchPosition=matchPosition;
		
		char[] dataChars=data.toCharArray();
		
		if(alternatives==null){
			alternatives=new BestAlternativeCode(false);
			for (ICodePiece piece : pieces) {
				alternatives.add(piece);
			}
		}
		Expression clone=new Expression() {
			
			@Override
			public String[] getExpressionSeparators() {
				return null;
			}
		};
		
		matchPosition=alternativeMatch(data,dataChars,matchPosition,clone);
		if(matchPosition==-1){
			return new ICodePieceMatch(-1,null);
		}
		
		int matchAlternative=matchPosition;
		while(matchAlternative!=-1){
			matchPosition=matchAlternative;
			matchAlternative=matchSymbol(data,dataChars,matchAlternative,clone);
			matchAlternative=alternativeMatch(data,dataChars,matchAlternative,clone);
		}
		
		//String representation=data.substring(startingMatchPosition,matchPosition);
		
		return new ICodePieceMatch(matchPosition,clone);
		//return new ICodePieceMatch(matchPosition,new Word(PieceType.EXPRESSION,representation,null));  
	}
	
}
