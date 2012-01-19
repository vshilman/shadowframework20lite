package codeconverter.java;

import java.util.ArrayList;
import java.util.List;

import codeconverter.CodePattern;
import codeconverter.java.codelines.JavaArrayDeclaration;
import codeconverter.java.codelines.JavaAssignment;
import codeconverter.java.codelines.JavaAttributeAssignmentPattern;
import codeconverter.java.codelines.JavaElse;
import codeconverter.java.codelines.JavaFor;
import codeconverter.java.codelines.JavaIf;
import codeconverter.java.codelines.JavaMethodAccess;
import codeconverter.java.codelines.JavaReturnPattern;
import codeconverter.java.codelines.JavaSuperPattern;

public class JavaCodePatterns {

	private static List<CodePattern> patterns=new ArrayList<CodePattern>();
	
	public static List<CodePattern> getPatterns(){
		patterns.add(new JavaElse());
		patterns.add(new JavaArrayDeclaration());
		patterns.add(new JavaFor());
		patterns.add(new JavaIf());
		patterns.add(new JavaAttributeDeclarationAndAssignemnt());
		patterns.add(new JavaAssignment());
		patterns.add(new JavaImportDeclaration());
		patterns.add(new JavaReturnPattern());
		patterns.add(new JavaSuperPattern());
		patterns.add(new JavaConstructorDeclaration());
		patterns.add(new JavaPackageDeclaration());
		patterns.add(new JavaAttributeAssignmentPattern());
		patterns.add(new JavaAttributeDeclaration());
		patterns.add(new JavaMethodDeclaration());
		patterns.add(new JavaBlockClose());
		patterns.add(new JavaClassDeclaration());
		patterns.add(new JavaInterfaceDeclaration());
		patterns.add(new JavaIsolatedKeywords());
		patterns.add(new JavaMethodAccess());
		
		return patterns;
	}

}