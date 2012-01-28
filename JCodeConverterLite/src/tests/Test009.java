package tests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
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

public class Test009 {

	/**
	 * Fast Java To Javascript conversion
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		List<String> list=new LinkedList<String>();

		list.add("@Override\tpublic String close()");
		
		List<CodePattern> patterns=JavaCodePatterns.getPatterns();
		
		List<CodeMatch> matches=new ArrayList<CodeMatch>();
		
		for (Iterator<CodePattern> iterator=patterns.iterator(); iterator
				.hasNext();) {
			CodePattern pattern=(CodePattern) iterator.next();
			matches.addAll(pattern.matchPattern(list));
		}
		Collections.sort(matches);

		System.out.println("Doing... ");
		
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