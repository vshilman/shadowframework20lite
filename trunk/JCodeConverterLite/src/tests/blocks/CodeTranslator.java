package tests.blocks;

import java.util.HashMap;

import codeconverter.CodePattern;

public interface CodeTranslator {

	public String translateCode(Block mainBlock,HashMap<CodeModule, CodePattern> relatedPatterns);
}
