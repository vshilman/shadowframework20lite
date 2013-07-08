package codeconverter.cpp;

import java.util.ArrayList;
import java.util.List;

import codeconverter.CodePattern;
import codeconverter.cpp.codelines.CppAttributeAssignmentPattern;
import codeconverter.cpp.codelines.CppCoutPattern;
import codeconverter.cpp.codelines.CppElse;
import codeconverter.cpp.codelines.CppFor;
import codeconverter.cpp.codelines.CppIf;
import codeconverter.cpp.codelines.CppMethodAccess;
import codeconverter.cpp.codelines.CppReturnPattern;
import codeconverter.cpp.codelines.CppSuperPattern;
import codeconverter.cpp.codelines.CppVariableAssignment;
import codeconverter.cpp.codelines.CppVariableDeclaration;
import codeconverter.cpp.codelines.CppVariableDeclarationAndAssignment;

public class CppCodePattern {


	private static List<CodePattern> patterns=new ArrayList<CodePattern>();

	public static List<CodePattern> getPatterns(){

		patterns.add(new CppConstrutorDeclaration());
		patterns.add(new CppSuperPattern());
		patterns.add(new CppDeleteOperation());
		patterns.add(new CppDestructorDeclaration());
		patterns.add(new CppGenericDirectiveLine());
		patterns.add(new CppIncludeDirectiveLine());
		patterns.add(new CppisolatedkeyWords());
		patterns.add(new CppMethodDeclaration());
		patterns.add(new CppNameSpaceUsing());
		patterns.add(new CppNamespaceDeclaration());
		patterns.add(new CppAttributeAssignmentPattern());
		patterns.add(new CppElse());
		patterns.add(new CppFor());
		patterns.add(new CppIf());
		patterns.add(new CppMethodAccess());
		patterns.add(new CppReturnPattern());
		patterns.add(new CppVariableAssignment());
		patterns.add(new CppVariableDeclaration());
		patterns.add(new CppVariableDeclarationAndAssignment());
		patterns.add(new CppCoutPattern());


		return patterns;
	}

}
