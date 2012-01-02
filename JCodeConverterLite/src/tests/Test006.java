package tests;

import java.util.List;

import codeconverter.CodeConverterUtilities;
import codeconverter.CodeMatch;
import codeconverter.CodePattern;
import codeconverter.FileStringUtility;
import codeconverter.java.JavaCodePatterns;

public class Test006 {

	/**
	 * This test recognize main blocks in House.java
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		List<String> list=FileStringUtility
				.loadTextFile("src/testPackage/Expressions.java");

		List<CodePattern> patterns=JavaCodePatterns.getPatterns();

		List<CodeMatch> matches=CodeConverterUtilities.findPatterns(list,patterns);

		CodeConverterUtilities.stampMatches(matches);		
	}
}