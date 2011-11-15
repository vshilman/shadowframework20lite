package tests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import codeconverter.CodeMatch;
import codeconverter.CodePattern;
import codeconverter.CodeTemplate;
import codeconverter.FileStringUtility;
import codeconverter.java.JavaCodePatterns;

public class Test005 {

	/**
	 * This test recognize main blocks in House.java
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		List<String> list=FileStringUtility
				.loadTextFile("src/testPackage/House.java");

		List<CodePattern> patterns=JavaCodePatterns.getPatterns();

		List<CodeMatch> matches=new ArrayList<CodeMatch>();

		for (Iterator<CodePattern> iterator=patterns.iterator(); iterator
				.hasNext();) {
			CodePattern pattern=(CodePattern) iterator.next();
			matches.addAll(pattern.matchPattern(list));
		}

		Collections.sort(matches);

		CodeTemplate template=new CodeTemplate();

		template.matchPattern(matches);
	}
}
