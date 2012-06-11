package codeconverter.js;

import java.util.List;

import codeconverter.BlockDataInterpreter;
import codeconverter.CodePattern;
import codeconverter.java.GenericCodePatternInterpreter;

public class JsCodePatternInterpreter extends GenericCodePatternInterpreter implements BlockDataInterpreter{
	
	public List<CodePattern> patterns=JsCodePatterns.getPatterns();

	public List<CodePattern> getPatterns() {
		return patterns;
	}

}
