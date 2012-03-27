package codeconverter.javaJsComparator.codePatterns;

import java.util.List;

import codeconverter.CodePattern;
import codeconverter.PieceType;
import codeconverter.javaJsComparator.CodePatternComparator;

public class ClassDeclarationComparator implements CodePatternComparator {

	@Override
	public int[] compare(List<CodePattern> javaCodePatterns, int javaIndex, List<CodePattern> jsCodePatterns,
			int jsIndex) {
		CodePattern javaPattern = javaCodePatterns.get(javaIndex);
		CodePattern jsPattern = jsCodePatterns.get(jsIndex);
		if (!javaPattern.getPieceByType(PieceType.NAME).toString()
				.equals(jsPattern.getPieceByType(PieceType.NAME).toString())) {
			return null;
		}
		return null;
	}

}
