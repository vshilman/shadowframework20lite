package codeconverter.comparator;

import java.util.List;

import codeconverter.CodePattern;

public interface CodePatternComparator {

	/*TODO : not sure of the requirement of passing all the list of patterns,
	would be more generic like this; and also of returning the boolean*/
	public boolean compare(CodePattern lang1Pattern,CodePattern lang2Pattern);

//	public int[][] compare(List<CodePattern> javaCodePatterns, int javaIndex,
//			List<CodePattern> jsCodePatterns, int jsIndex);


}
