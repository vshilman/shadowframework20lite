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

		patterns.add(new CppMethodDeclaration()); //ok
		patterns.add(new CppElse()); //ok
		patterns.add(new CppFor()); //ok
		patterns.add(new CppIf()); //ok
		patterns.add(new CppAttributeAssignmentPattern()); //ok
		patterns.add(new CppConstrutorDeclaration()); //ok
		patterns.add(new CppSuperPattern());
		patterns.add(new CppDeleteOperation()); //no need
		patterns.add(new CppDestructorDeclaration()); //no need
		patterns.add(new CppGenericDirectiveLine()); //no need
		patterns.add(new CppIncludeDirectiveLine()); //no need
		patterns.add(new CppisolatedkeyWords()); //ok
		patterns.add(new CppNameSpaceUsing()); //no need
		patterns.add(new CppNamespaceDeclaration()); //no need
		patterns.add(new CppMethodAccess()); //ok
		patterns.add(new CppReturnPattern());//ok
		patterns.add(new CppVariableAssignment()); //ok
		patterns.add(new CppVariableDeclaration()); //ok
		patterns.add(new CppVariableDeclarationAndAssignment()); //ok
		patterns.add(new CppCoutPattern());


		return patterns;
	}

}
