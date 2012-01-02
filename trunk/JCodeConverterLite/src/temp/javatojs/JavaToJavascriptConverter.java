package temp.javatojs;

import java.util.Iterator;
import java.util.List;

import codeconverter.CodeMatch;

public class JavaToJavascriptConverter {

	public void covertMatches(List<String> data,List<CodeMatch> matches){
		
		for (Iterator iterator=matches.iterator(); iterator.hasNext();) {
			CodeMatch codeMatch=(CodeMatch) iterator.next();
			System.out.println("codeMatch "+codeMatch.getMatcher().getClass());
		}
		
	}
	
}
