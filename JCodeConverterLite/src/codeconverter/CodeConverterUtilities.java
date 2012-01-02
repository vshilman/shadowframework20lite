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

}
