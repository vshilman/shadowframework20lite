package codeconverter.cpp;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.codepieces.UniqueKeyword;

public class CppFriendClassDeclaration extends CodePattern{

   public CppFriendClassDeclaration() {
	   addCodePiece(new UniqueKeyword("friend class "), new CppName());
	   addPatternType(PatternType.FRIENDSHIP);
   }

}
