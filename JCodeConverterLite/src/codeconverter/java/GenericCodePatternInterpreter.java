package codeconverter.java;

import java.util.List;

import codeconverter.BlockDataInterpreter;
import codeconverter.CodePattern;

public abstract class GenericCodePatternInterpreter implements BlockDataInterpreter{

	public GenericCodePatternInterpreter() {
		super();
	}

	public abstract List<CodePattern> getPatterns();

	@Override
	public CodePattern getBlockDeclarationPattern(String blockDeclaration) {
	
		for (int i=0; i < getPatterns().size(); i++) {
			CodePattern pattern=getPatterns().get(i);
			CodePattern matched=pattern.match(blockDeclaration);
			if(matched!=null){
				return matched;
			}
		}
		//only when a null has been returned from each pattern
		return null;
	}

	@Override
	public CodePattern getLineOfCodePattern(String lineOfCode) {
		
		return getBlockDeclarationPattern(lineOfCode);
	}

}