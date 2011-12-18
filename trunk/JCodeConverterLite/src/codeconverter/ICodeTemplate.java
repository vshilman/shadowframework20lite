package codeconverter;

import java.util.List;

public interface ICodeTemplate extends ICodeElement{

	
	/**
	 * @return the list of all the pattern being part of this element
	 */
	public List<PatternType> getPatternType();
}
