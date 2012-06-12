package codeconverter.java;

import java.util.ArrayList;
import java.util.List;

import codeconverter.CodePattern;
import codeconverter.java.codelines.JavaAttributeAssignmentPattern;
import codeconverter.java.codelines.JavaElse;
import codeconverter.java.codelines.JavaFor;
import codeconverter.java.codelines.JavaIf;
import codeconverter.java.codelines.JavaMethodAccess;
import codeconverter.java.codelines.JavaReturnPattern;
import codeconverter.java.codelines.JavaSuperPattern;
import codeconverter.java.codelines.JavaVariableAssignment;
import codeconverter.java.codelines.JavaVariableDeclaration;
import codeconverter.java.codelines.JavaVariableDeclarationAndAssignment;
import codeconverter.java.jogl.JoglMethodAccess;

public class JavaCodePatterns {

	private static List<CodePattern> patterns=new ArrayList<CodePattern>();
	
	public static List<CodePattern> getPatterns(){

		patterns.add(new JavaClassDeclaration());
		patterns.add(new JavaMethodDeclaration());
		patterns.add(new JavaElse());
		//patterns.add(new JavaArrayDeclaration());
		patterns.add(new JavaFor());
		patterns.add(new JavaIf());
		patterns.add(new JavaAttributeDeclarationAndAssignemnt());
		patterns.add(new JavaImportDeclaration());
		patterns.add(new JavaReturnPattern());
		patterns.add(new JavaSuperPattern());
		patterns.add(new JavaPackageDeclaration());
		patterns.add(new JavaAttributeAssignmentPattern());
		patterns.add(new JavaVariableDeclaration());
		patterns.add(new JavaVariableDeclarationAndAssignment());
		patterns.add(new JavaVariableAssignment());
		patterns.add(new JavaAttributeDeclaration());
		patterns.add(new JavaInterfaceDeclaration());
		patterns.add(new JavaIsolatedKeywords());
		patterns.add(new JoglMethodAccess());
		patterns.add(new JavaMethodAccess());
		patterns.add(new JavaConstructorDeclaration());
		patterns.add(new JavaArrayContentDeclaration());
		
		return patterns;
	}

}
