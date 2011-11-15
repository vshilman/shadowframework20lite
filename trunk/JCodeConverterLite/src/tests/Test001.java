package tests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import codeconverter.CodeMatch;
import codeconverter.CodePattern;
import codeconverter.FileStringUtility;
import codeconverter.java.JavaCodePatterns;

public class Test001 {

	/**
	 * This test recognize with some patterns all the line of code in House.java
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

		for (Iterator<CodeMatch> iterator=matches.iterator(); iterator
				.hasNext();) {
			CodeMatch codeMatch=iterator.next();

			System.out.println("" + codeMatch.getMatcher().toString());

			// System.out.println("Declaration at line ["+codeMatch.getLineStart()+"] ending at line["+codeMatch.getLineEnd()
			// +"] of type '"+codeMatch.toString()+"'");
			// System.out.println("\t["+codeMatch.getLineStart()+"]:"+list.get(codeMatch.getLineStart()));
		}
	}
}
