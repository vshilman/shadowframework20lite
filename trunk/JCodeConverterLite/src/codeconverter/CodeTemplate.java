package codeconverter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Alessandro Martinelli
 */
//Note: this is abstract, so that specific pattern methods can be described.
public abstract class CodeTemplate extends AbstractCodeTemplate {
	
	private String name="";
	private List<CodeElementProfile> elements=new ArrayList<CodeElementProfile>();
	
	protected List<CodeMatch> matchedElements=new ArrayList<CodeMatch>();
	
	public CodeTemplate(String name) {
		super();
		this.name=name;
	}
	
	public void addElement(CodeElementProfile... element) {
		for (int i=0; i < element.length; i++) {
			elements.add(element[i]);
		}
	}
	
	public String getName() {
		return name;
	}
	
	
	
	public List<CodeMatch> getMatchedElements() {
		return matchedElements;
	}

	private boolean containsAny(List<PatternType> types,List<PatternType> matching ){
		for (Iterator<PatternType> iterator=matching.iterator(); iterator.hasNext();) {
			PatternType patternType=iterator.next();
			if(types.contains(patternType))
				return true;
		}
		return false;
	}
	
	@Override
	public ICodeElement cloneCodePiece() {
		return this;
	}
	
	public List<CodeMatch> cloneMatchedElements(){
		ArrayList<CodeMatch> matches=new ArrayList<CodeMatch>();
		for (CodeMatch codeMatch : matchedElements) {
			CodeMatch match=new CodeMatch(codeMatch.getLineStart(),codeMatch.getLineEnd(),
					(ICodeTemplate)codeMatch.getMatcher());
			matches.add(match);
		}
		return matches;
	}
	

	public List<CodeMatch> matchPattern(List<CodeMatch> code) {
		
		matchedElements.clear();
		
		List<CodeMatch> matches=new ArrayList<CodeMatch>();
		
		for (int i=0; i < code.size(); i++) {
			CodeMatch match=code.get(i);
			
			int matching=i;
			for (int j=0; j < elements.size(); j++) {
				CodeElementProfile profile=elements.get(j);
				if(containsAny(match.getMatcher().getPatternType(),profile.element.getPatternType())){
					matchedElements.add(match);
					i++;
					if(profile.getCardinality()==ElementCardinality.MORE){
						while(i <code.size()-1){
							match=code.get(i);
							if(containsAny(match.getMatcher().getPatternType(),profile.element.getPatternType())){
								matchedElements.add(match);
								i++;
							}else{
								break;
							}
						}
					}
				}else{
					matching=-1;
					j=elements.size();
				}
				if(i<code.size())//I may be at the end of code!
					match=code.get(i);
			}
			if(matching!=-1){//match have been found!
				i--;
				matches.add(new CodeMatch(code.get(matching).getLineStart(),code.get(i).getLineEnd(),this));
			}
		}
		
		return matches;
	}
}
