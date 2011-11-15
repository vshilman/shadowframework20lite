package codeconverter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class CodePattern implements ICodeElement {

	/*
	 * a Pattern for a line of code. all translation will be based on
	 * equivalence rules between Patterns
	 */
	private String name="";
	private List<ICodePiece> elements=new LinkedList<ICodePiece>();
	private List<PatternType> patternType=new LinkedList<PatternType>();

	public CodePattern(String name/* ,PatternType patternType */) {
		super();
		this.name=name;
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

	private boolean match(String lineCode) {
		char[] lineCodeChars=lineCode.toCharArray();
		int index=0;
		int elementIndex=0;
		while (index < lineCodeChars.length && elementIndex < elements.size()) {
			if (lineCodeChars[index] == ' ' || lineCodeChars[index] == '\t') {
				index++;
			} else {
				int result=elements.get(elementIndex).elementMatch(lineCode,
						index);
				if (result == -1) {
					return false;
				} else {
					index=result;
					elementIndex++;
				}
			}
		}
		if (elementIndex == elements.size() && index == lineCodeChars.length) {
			return true;
		}else if(index == lineCodeChars.length){
			for (int i = elementIndex; i < elements.size(); i++) {
				if(!(elements.get(i) instanceof OptionalCode)){
					return false;
				}
			}
			return true;
		}
		

		return false;
	}

	public List<CodeMatch> matchPattern(List<String> code) {
		List<CodeMatch> match=new ArrayList<CodeMatch>();
		for (int i=0; i < code.size(); i++) {
			if (match(code.get(i))) {
				match.add(new CodeMatch(i,i,cloneCodePiece()));
			}
		}

		return match;
	}

}
