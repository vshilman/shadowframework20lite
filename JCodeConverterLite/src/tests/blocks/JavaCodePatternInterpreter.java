package tests.blocks;

import java.util.ArrayList;
import java.util.List;

import codeconverter.CodePattern;
import codeconverter.java.JavaCodePatterns;

public class JavaCodePatternInterpreter implements BlockDataInterpreter{
	
	public List<CodePattern> patterns=JavaCodePatterns.getPatterns();

	@Override
	public CodePattern getBlockDeclarationPattern(String blockDeclaration) {

		for (int i=0; i < patterns.size(); i++) {
			CodePattern pattern=patterns.get(i);
			if(pattern.match(blockDeclaration)){
				return (CodePattern)pattern.cloneCodePiece();
			}
		}
		
		return null;
	}
	
	@Override
	public CodePattern getLineOfCodePattern(String lineOfCode) {
		
		return getBlockDeclarationPattern(lineOfCode);
	}
}
