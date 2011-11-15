package codeconverter;

import java.util.ArrayList;
import java.util.List;

public class CodeTemplate implements ICodeElement {

	public enum Cardinality {
		ONCE, // [1]
		MORE, // [*]
		AT_LEAST_ONCE, // [1,*]
		NO_MORE_THAN_ONCE
		// [0,1]
	}

	public List<CodeMatch> matchPattern(List<CodeMatch> matches) {
		List<CodeMatch> match=new ArrayList<CodeMatch>();

		int index=0;
		while (index < matches.size()) {
			// if(((CodePattern)(matches.get(index).getMatcher())).getPatternType()==PatternType.METHOD_DECLARATION){
			// System.out.println("I have found a match at "+index);
			// int tmpIndex=index;
			// index = findEnd(matches, index);
			// }

			index++;
		}

		return match;
	}

	private int findEnd(List<CodeMatch> matches, int index) {
		while (index < matches.size()) {
			// if(matches.get(index).getMatcher().getPatternType()==PatternType.BLOCK_CLOSE){
			// System.out.println("I have found the end at "+index);
			// return index;
			// }
			index++;
		}
		return index;
	}

	@Override
	public ICodeElement cloneCodePiece() {
		// TODO Auto-generated method stub
		return null;
	}
}
