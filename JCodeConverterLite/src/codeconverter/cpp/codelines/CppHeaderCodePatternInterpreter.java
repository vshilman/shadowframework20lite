package codeconverter.cpp.codelines;

import java.util.List;

import codeconverter.BlockDataInterpreter;
import codeconverter.CodePattern;
import codeconverter.cpp.CppHeaderCodePatterns;
import codeconverter.java.GenericCodePatternInterpreter;

public class CppHeaderCodePatternInterpreter extends GenericCodePatternInterpreter implements BlockDataInterpreter{


	private List<CodePattern> patterns=CppHeaderCodePatterns.getPatterns();

	@Override
	public List<CodePattern> getPatterns() {
		return patterns;
	}




}
