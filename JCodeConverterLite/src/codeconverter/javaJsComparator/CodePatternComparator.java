package codeconverter.javaJsComparator;

import java.util.List;

import codeconverter.CodePattern;

public interface CodePatternComparator {

	public int[] compare(List<CodePattern> javaCodePatterns, int javaIndex,
			List<CodePattern> jsCodePatterns, int jsIndex);

}
