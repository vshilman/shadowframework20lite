package codeconverter.java;

import java.util.List;

import codeconverter.BlockDataInterpreter;
import codeconverter.CodePattern;

public class JavaCodePatternInterpreter extends GenericCodePatternInterpreter implements BlockDataInterpreter{
	
	private List<CodePattern> patterns=JavaCodePatterns.getPatterns();

	@Override
	public List<CodePattern> getPatterns() {
		return patterns;
	}

}
