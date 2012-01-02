package tests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import temp.javatojs.JavaToJavascriptConverter;

import codeconverter.CodeConverterUtilities;
import codeconverter.CodeMatch;
import codeconverter.CodePattern;
import codeconverter.CodeTemplate;
import codeconverter.FileStringUtility;
import codeconverter.java.JavaCodePatterns;
import codeconverter.java.templates.JavaClassTemplate;
import codeconverter.java.templates.JavaConstructorTemplate;
import codeconverter.java.templates.JavaMethodTemplate;

public class Test008 {

	/**
	 * Fast Java To Javascript conversion
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		List<String> list=FileStringUtility.loadTextFile("src/testPackage/House.java");

		List<CodePattern> patterns=JavaCodePatterns.getPatterns();
		
		List<CodeMatch> matches=new ArrayList<CodeMatch>();
		
		for (Iterator<CodePattern> iterator=patterns.iterator(); iterator
				.hasNext();) {
			CodePattern pattern=(CodePattern) iterator.next();
			matches.addAll(pattern.matchPattern(list));
		}
		
		Collections.sort(matches);
		
		CodeTemplate template=new JavaMethodTemplate();
		CodeConverterUtilities.removeOldAddNewMatch(matches,template.matchPattern(matches));
		for (int i=0; i < matches.size(); i++) {
			System.out.println("unmatched line (After Method Match) "+matches.get(i));
		}
		template=new JavaConstructorTemplate();
		CodeConverterUtilities.removeOldAddNewMatch(matches,template.matchPattern(matches));
		for (int i=0; i < matches.size(); i++) {
			System.out.println("unmatched line (After Constructor Match) "+matches.get(i));
		}
		template=new JavaClassTemplate();
		CodeConverterUtilities.removeOldAddNewMatch(matches,template.matchPattern(matches));
		for (int i=0; i < matches.size(); i++) {
			System.out.println("unmatched line (After Class Match) "+matches.get(i));
		}		
		
		JavaToJavascriptConverter converter=new JavaToJavascriptConverter();
		converter.covertMatches(list,matches);
	}
}