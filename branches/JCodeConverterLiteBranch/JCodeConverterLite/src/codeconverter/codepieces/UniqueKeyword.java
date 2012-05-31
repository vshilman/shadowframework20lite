package codeconverter.codepieces;

import java.util.ArrayList;

import codeconverter.PieceType;

/**
 * A Keyword with a mandatory unique value.
 * For example in java language are kewords 'class' 
 * or 'new'
 * 
 * @author Alessandro Martinelli
 */
public class UniqueKeyword extends Keyword {

	private ArrayList<String> alternatives=new ArrayList<String>(1);

	public UniqueKeyword(String keyword) {
		alternatives.add(keyword);
	}
	
	public UniqueKeyword(PieceType type,String keyword) {
		alternatives.add(keyword);
		setPieceType(type);
	}

	@Override
	public ArrayList<String> getAlternatives() {
		return alternatives;
	}

	@Override
	public String toString() {
		return alternatives.get(0);
	}
}
