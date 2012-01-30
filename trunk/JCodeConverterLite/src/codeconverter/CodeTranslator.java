package codeconverter;

import java.util.HashMap;


public interface CodeTranslator {

	public String translateCode(Block mainBlock,HashMap<CodeModule, CodePattern> relatedPatterns);
}
