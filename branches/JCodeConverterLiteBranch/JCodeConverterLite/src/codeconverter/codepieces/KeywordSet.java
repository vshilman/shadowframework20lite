package codeconverter.codepieces;

import java.util.ArrayList;

import codeconverter.PieceType;

public class KeywordSet extends Keyword{

	private ArrayList<String> alternatives=new ArrayList<String>(1);

	public KeywordSet(PieceType type,String... keywords) {
		for (String string : keywords) {
			alternatives.add(string);
		}
		setPieceType(type);
	}
	
	public KeywordSet(String... keywords) {
		for (String string : keywords) {
			alternatives.add(string);
		}
	}

	@Override
	public ArrayList<String> getAlternatives() {
		return alternatives;
	}

}
