package codeconverter;

import java.util.HashMap;


public interface CodeTranslator {

	/**
	 * 
	 * @param mainBlock
	 * @param relatedPatterns
	 * @return 
	 */
	public String translateCode(Block mainBlock,HashMap<CodeModule, CodePattern> relatedPatterns);
}
