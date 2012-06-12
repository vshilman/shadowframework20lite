package codeconverter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import codeconverter.ICodePiece.ICodePieceMatch;
import codeconverter.codepieces.OptionalCode;

/**
 * A CodePattern is a Sequence of {@link ICodePiece} which can be used to identify a line of code. 
 * 
 * @author Alessandro Martinelli
 */
public class CodePattern {

	/*
	 * a Pattern for a line of code. all translation will be based on
	 * equivalence rules between Patterns
	 */
	private ArrayList<ICodePiece> elements=new ArrayList<ICodePiece>();
	private List<PatternType> patternType=new LinkedList<PatternType>();

	public CodePattern() {
		super();
	}

	/**
	 * Add code pieces to this Pattern
	 * @param piece
	 */
	public void addCodePiece(ICodePiece... piece) {
		for (int i=0; i < piece.length; i++) {
			elements.add(piece[i]);
		}
	}
	
	/**
	 * Add Pattern Types to this Pattern. Pattern Types are used to
	 * classify patterns.  
	 * @param piece
	 */
	public void addPatternType(PatternType... type) {
		for (int i=0; i < type.length; i++) {
			patternType.add(type[i]);
		}
	}

	/**
	 * @return the List of all Pattern Types
	 */
	public List<PatternType> getPatternType() {
		return patternType;
	}
	
	/**
	 * @return the List of all Code Pieces
	 */
	public List<ICodePiece> getPieces() {
		return elements;
	}
	
	/**
	 * Find a Code Piece given a type. The first 
	 * code piece in codePieces List is returned.
	 * If no piece is found a null pointer is returned.
	 * 
	 * @param type The type of the desired piece
	 * @return
	 */
	public ICodePiece getPieceByType(PieceType type){
		for (int i = 0; i < elements.size(); i++) {
			if(elements.get(i).getPieceType()==type){
				return elements.get(i);
			}
		}
		return null;
	}
	
	/**
	 * Verify if a line of Code match this Code Pattern
	 * If there is a match, a new CodePattern is returned,
	 * which is a description of the match.
	 * 
	 * @param lineCode the line of code to be matched
	 * @return a new descriptive CodePattern or null if there is no match 
	 */
	public CodePattern match(String lineCode) {
		
		CodePattern pattern=new CodePattern();
		pattern.patternType.addAll(this.patternType);
		try {
			char[] lineCodeChars=lineCode.toCharArray();
			int index=0;
			int elementIndex=0;
		
			while (index < lineCodeChars.length && elementIndex < elements.size()) {
				if (lineCodeChars[index] == ' ' || lineCodeChars[index] == '\t') {
					index++;
				} else {
					ICodePiece piece=elements.get(elementIndex);
					ICodePieceMatch pieceMatch=piece.elementMatch(lineCode,index);
					int result=pieceMatch.getMatchPosition();
					
					if (result == -1) {
						return null;
					} else {
						index=result;
						pattern.elements.add(pieceMatch.getDataPiece());
						elementIndex++;
					}
				}
			}
			if (elementIndex == elements.size() && index == lineCodeChars.length) {
				return pattern;
			}else if(index == lineCodeChars.length){
				for (int i = elementIndex; i < elements.size(); i++) {
					//TODO: Fa schifo
					if(!(elements.get(i) instanceof OptionalCode)){
						return null;
					}
				}
				return pattern;
			}
		} catch (Exception e) {
			System.err.println("Exception while trying to match ["+lineCode+"]");
			e.printStackTrace();
		}
		
		return null;
	}	
	
	public String toString(){
		String data="";
		if(elements.size()>0) {
			data+=elements.get(0).toString();
			for (int i=1; i < elements.size(); i++) {
				data+=" "+elements.get(i).toString();
			}
		}
		return data;
	}
	
	public String printTypes(){
		String data="";
		if(elements.size()>0) {
			data+=elements.get(0).printTypes(0);
			for (int i=1; i < elements.size(); i++) {
				data+=elements.get(i).printTypes(0);
			}
		}
		return data;
	}
}