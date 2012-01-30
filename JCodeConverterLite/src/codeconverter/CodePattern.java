package codeconverter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import codeconverter.ICodePiece.ICodePieceMatch;
import codeconverter.codepieces.OptionalCode;

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

	public void addCodePiece(ICodePiece... piece) {
		for (int i=0; i < piece.length; i++) {
			elements.add(piece[i]);
		}
	}

	public void addCodePattern(PatternType... type) {
		for (int i=0; i < type.length; i++) {
			patternType.add(type[i]);
		}
	}

	public List<PatternType> getPatternType() {
		return patternType;
	}
	
	public List<ICodePiece> getPieces() {
		return elements;
	}
	
	public List<ICodePiece> getPieceByType(PieceType type){
		ArrayList<ICodePiece> pieces=new ArrayList<ICodePiece>();
		for (int i = 0; i < elements.size(); i++) {
			if(elements.get(i).getPieceType()==type){
				pieces.add(elements.get(i));
			}
		}
		return pieces;
	}
	
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
					ICodePieceMatch pieceMatch=elements.get(elementIndex).elementMatch(lineCode,index);
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
//				for (int i = elementIndex; i < elements.size(); i++) {
//					//TODO: Fa schifo
//					if(!(elements.get(i) instanceof OptionalCode)){
//						return null;
//					}
//				}
//				return pattern;
			}
		} catch (Exception e) {
			System.err.println("Exception while tryig to match ["+lineCode+"]");
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
}
