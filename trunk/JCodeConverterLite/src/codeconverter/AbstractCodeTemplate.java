package codeconverter;

import java.util.LinkedList;
import java.util.List;

public abstract class AbstractCodeTemplate  implements ICodeTemplate {

	private List<PatternType> patternType=new LinkedList<PatternType>();

	public AbstractCodeTemplate() {
		super();
	}

	public void addCodePattern(PatternType... type) {
		for (int i=0; i < type.length; i++) {
			patternType.add(type[i]);
		}
	}

	public List<PatternType> getPatternType() {
		return patternType;
	}

}