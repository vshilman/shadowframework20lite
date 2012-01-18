package codeconverter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class CodeConverterUtilities {

	/**
	 * Remove all the CodeMatch which have 
	 * been covered by a new List of CodeMatch,
	 * and add the newMatches to the oldOnes
	 * @param matches the Old Matches
	 * @param newMatches the new Matches which have been identified
	 */
	public static void removeOldAddNewMatch(List<CodeMatch> matches,
			List<CodeMatch> newMatches) {
		for (int j=0; j < newMatches.size(); j++) {
			CodeMatch newMatch=newMatches.get(j);
			for (int i=0; i < matches.size(); i++) {
				CodeMatch match=matches.get(i);
				int lineStart=match.getLineStart();
				if(lineStart>=newMatch.getLineStart() && lineStart<=newMatch.getLineEnd()){
					matches.remove(i);
					i--;
				}
			}
		}
		matches.addAll(newMatches);
		Collections.sort(matches);
	}

	public static List<CodeMatch> findPatterns(List<String> list,
			List<CodePattern> patterns) {
		List<CodeMatch> matches=new ArrayList<CodeMatch>();
	
		for (Iterator<CodePattern> iterator=patterns.iterator(); iterator
				.hasNext();) {
			CodePattern pattern=(CodePattern) iterator.next();
			matches.addAll(pattern.matchPattern(list));
		}
	
		Collections.sort(matches);
		return matches;
	}

	public static void stampMatches(List<CodeMatch> matches) {
		for (CodeMatch codeMatch : matches) {
			System.out.println("["+codeMatch.getLineStart()+"-"+codeMatch.getLineEnd()+"]"+ codeMatch.getMatcher().toString());
		}
	}

	public static boolean[] findMatchedLines(List<String> list,
			List<CodeMatch> matches) {
		boolean matched[]=new boolean[list.size()];
		
		for (int i=0; i < matched.length; i++) {
			matched[i]=false;
		}
		
		for (Iterator<CodeMatch> iterator=matches.iterator(); iterator
				.hasNext();) {
			CodeMatch codeMatch=iterator.next();
			
			for(int j=codeMatch.getLineStart();j<=codeMatch.getLineEnd();j++){
				matched[j]=true;
			}
		}
		
		for (int i=0; i < matched.length; i++) {
			boolean trim0=(list.get(i).trim().length()==0);
			matched[i]=matched[i] || trim0;
			if(list.get(i).trim().startsWith("//")){
				matched[i]=true;
			}else if(list.get(i).trim().startsWith("/*")){
				for(int j=i;j<list.size();j++){
					if(!list.get(j).trim().endsWith("*/")){
						matched[j]=true;
						if(!(j==list.size()-1)){
							matched[j+1]=true;
						}
					}
				}
			}
		}
		return matched;
	}

}
