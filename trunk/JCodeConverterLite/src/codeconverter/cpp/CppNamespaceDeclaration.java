package codeconverter.cpp;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.codepieces.UniqueKeyword;

public class CppNamespaceDeclaration extends CodePattern{

	public CppNamespaceDeclaration() {
		addCodePiece(new UniqueKeyword("namespace "), new CppName());
		addPatternType(PatternType.NAMESPACE_DECLARATION);
	}

}
