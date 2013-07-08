package codeconverter.cpp;

import java.util.List;

import codeconverter.BlockDataInterpreter;
import codeconverter.CodePattern;
import codeconverter.java.GenericCodePatternInterpreter;

public class CppCodePatternInterpreter extends GenericCodePatternInterpreter implements BlockDataInterpreter{




	private List<CodePattern> patterns=CppCodePattern.getPatterns();

	@Override
	public List<CodePattern> getPatterns() {
		return patterns;
	}



}
