package codeconverter.cpp;

import java.util.ArrayList;
import java.util.List;

import codeconverter.CodePattern;
import codeconverter.javaJsComparator.codePatterns.IsolatedKeywordsComparator;

public class CppHeaderCodePatterns {

	private static List<CodePattern> patterns=new ArrayList<CodePattern>();

	public static List<CodePattern> getPatterns(){

		patterns.add(new CppAttributeDeclaration());
		patterns.add(new CppClassDeclaration());
		patterns.add(new CppisolatedkeyWords());
		patterns.add(new CppConstructorHeaderDeclaration());
		patterns.add(new CppDestructorHeaderDeclaration());
		patterns.add(new CppFriendClassDeclaration());
		patterns.add(new CppGenericDirectiveLine());
		patterns.add(new CppIncludeDirectiveLine());
		patterns.add(new CppMethodHeaderDeclaration());
		patterns.add(new CppNamespaceDeclaration());
		patterns.add(new CppAttributeDeclaration());

		return patterns;
	}


}
