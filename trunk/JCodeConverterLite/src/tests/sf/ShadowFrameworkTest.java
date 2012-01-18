package tests.sf;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import codeconverter.CodeConverterUtilities;
import codeconverter.CodeMatch;
import codeconverter.CodePattern;
import codeconverter.FileStringUtility;
import codeconverter.java.JavaCodePatterns;

public class ShadowFrameworkTest {

	public static void main(String[] args) {

		//File f=new File("../ShadowFramework2.0/src/shadow/system/data/SFOutputStream.java");
		//checkFile(f);
		File f=new File("../ShadowFramework2.0/src");
		checkDirectory(f);
		
		//f=new File("../ShadowFramework2.0_OpenGL20/src");
		//checkDirectory(f);
		
		//After creating this test case
					//TotalLoC 4654,	 TotalUnMatch 2465	 Rap 0.5296519123334765
		//After adding JavaInterface: 
					//TotalLoC 4654,	 TotalUnMatch 2457	 Rap 0.5279329608938548
		//After including '<' and '>' in names
					//TotalLoC 4654,	 TotalUnMatch 2396	 Rap 0.5148259561667383
		//After adding ';' as alternative to '{' at the end of method declaration
					//TotalLoC 4654,	 TotalUnMatch 2301	 Rap 0.49441340782122906
		//After adding '@Ovverride' into a new 'JavaIsolatedKeywords'
					//TotalLoC 4654,	 TotalUnMatch 2195	 Rap 0.4716373012462398
		//After adding UnInterpretedCode, UnInterpretedEvaluation and JavaMethodAccess
					//TotalLoC 4654,	 TotalUnMatch 1934	 Rap 0.41555651052857756
		//After adding JavaAttributeDeclarationAndAssignement
					//TotalLoC 4654,	 TotalUnMatch 1645	 Rap 0.35345938977223895
		//After JavaIf and JavaSuperPattern
					//TotalLoC 4654,	 TotalUnMatch 1585	 Rap 0.3405672539750752
		//After an Unidentified Code to manage throws in JavaMethodDeclaration		
					//TotalLoC 4654,	 TotalUnMatch 1567	 Rap 0.33669961323592607
		//After JavaFor
					//TotalLoC 4654,	 TotalUnMatch 1471	 Rap 0.3160721959604641
		//After Asking CodePattern to evaluate on more Lines of Code 
					//TotalLoC 4654,	 TotalUnMatch 1452	 Rap 0.31198968629136226
		//Adding 'static{' to JavaIsolatedKeywords
					//TotalLoC 4654,	 TotalUnMatch 1439	 Rap 0.3091963902019768
		//Adding a Code Sequence to JavaSuperPattern to match a greater number of case
					//TotalLoC 4654,	 TotalUnMatch 1401	 Rap 0.3010313708637731
		//After (ehm...) solving a Bug in this test itself...
					//TotalLoC 4654,	 TotalUnMatch 658	 Rap 0.14138375590889557
		//After adding '['']' on array declaration to make check array types.	
					//TotalLoC 4654,	 TotalUnMatch 628	 Rap 0.13493768801031372
		//After involving also "ShadowFramework20_GL20" (Much more Lines of Code)
					//TotalLoC 10324,	 TotalUnMatch 1705	 Rap 0.16514916698953894
		//After adding else as static keyword
					//TotalLoC 10324,	 TotalUnMatch 1689	 Rap 0.16359938008523828
	
		System.err.println("TotalLoC "+totalLoC+",\t TotalUnMatch "+totalUnmatched+"\t Rap "+
				(1.0*totalUnmatched)/(1.0*totalLoC));
		
	}

	public static void checkDirectory(File f) {
		File[] files=f.listFiles();
		for (int i=0; i < files.length; i++) {
			if (files[i].isDirectory()) {
				checkDirectory(files[i]);
			} else if(files[i].getName().endsWith(".java")){
				checkFile(files[i]);
			}
		}
	}
	
	private static List<CodePattern> patterns=JavaCodePatterns.getPatterns();

	private static boolean stampOutput=true;
	
	private static int totalUnmatched=0;
	private static int totalLoC=0;
	
	public static void checkFile(File f){
		
		List<String> list=FileStringUtility.loadTextFile(f.getAbsolutePath());

		List<CodeMatch> matches=new ArrayList<CodeMatch>();
		
		//System.err.println(patterns.size());
		
		for (Iterator<CodePattern> iterator=patterns.iterator(); iterator
				.hasNext();) {
			CodePattern pattern=(CodePattern) iterator.next();
			try {
				List<CodeMatch> tmpMatches=pattern.matchPattern(list);
				//System.err.println("patternName "+pattern+" "+tmpMatches.size());	
				matches.addAll(tmpMatches);
			} catch (Exception e) {
				//nothing to do, will appear into code pattern check..
			}
		}
		
		Collections.sort(matches);

		//System.err.println(matches.size());
		
		boolean[] matched=CodeConverterUtilities.findMatchedLines(list,matches);
			
		int unmatchCount=0;
		for (int i=0; i < matched.length; i++) {
			if(!matched[i]){
				unmatchCount++;
				System.err.println("Cannot Read Line Of Code \t ["+f.getName()+"."+(i)+"]:\t\t"+list.get(i));
			}
		}
		
		totalUnmatched+=unmatchCount;
		
		
		int index=0;
		int loc=0;
		while(index<list.size()){
			
			if(list.size()>index && list.get(index).trim().length()>0 && !list.get(index).trim().startsWith("//")){
				if(list.get(index).trim().startsWith("/*")){
					while(list.size()>index && !list.get(index).trim().startsWith("*/")){
						index++;
					}
				}else{
					loc++;	
				}
			}
			index++;
			
		}
		
		totalLoC+=loc;
	}
}
