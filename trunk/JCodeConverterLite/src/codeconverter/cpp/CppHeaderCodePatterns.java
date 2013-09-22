package codeconverter.cpp;

import java.util.ArrayList;
import java.util.List;

import codeconverter.CodePattern;

public class CppHeaderCodePatterns {

	private static List<CodePattern> patterns=new ArrayList<CodePattern>();

	public static List<CodePattern> getPatterns(){

		patterns.add(new CppClassDeclaration()); //ok
		patterns.add(new CppNameSpaceUsing());
		patterns.add(new CppMethodHeaderDeclaration()); //ok
		patterns.add(new CppAttributeDeclaration()); //ok
		patterns.add(new CppisolatedkeyWords()); //ok
		patterns.add(new CppConstructorHeaderDeclaration()); //ok
		patterns.add(new CppDestructorHeaderDeclaration()); //no need
		patterns.add(new CppFriendClassDeclaration()); //no need
		patterns.add(new CppGenericDirectiveLine()); //no need
		patterns.add(new CppIncludeDirectiveLine()); //no need
		patterns.add(new CppNamespaceDeclaration()); //no need
		return patterns;
	}


}
