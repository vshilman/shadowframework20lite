package codeconverter.java;

import java.util.List;

import codeconverter.BlockDataInterpreter;
import codeconverter.CodePattern;

public class JavaCodePatternInterpreter implements BlockDataInterpreter{
	
	public List<CodePattern> patterns=JavaCodePatterns.getPatterns();

	@Override
	public CodePattern getBlockDeclarationPattern(String blockDeclaration) {

		for (int i=0; i < patterns.size(); i++) {
			CodePattern pattern=patterns.get(i);
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
