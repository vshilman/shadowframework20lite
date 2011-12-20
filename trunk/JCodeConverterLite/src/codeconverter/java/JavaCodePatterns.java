package codeconverter.java;

import java.util.ArrayList;
import java.util.List;

import codeconverter.CodePattern;
import codeconverter.java.codelines.JavaAssignment;
import codeconverter.java.codelines.JavaAttributeAssignmentPattern;
import codeconverter.java.codelines.JavaReturnPattern;

public class JavaCodePatterns {

	private static List<CodePattern> patterns=new ArrayList<CodePattern>();
	
	public static List<CodePattern> getPatterns(){
		patterns.add(new JavaImportDeclaration());
		patterns.add(new JavaReturnPattern());
		patterns.add(new JavaConstructorDeclaration());
		patterns.add(new JavaPackageDeclaration());
		patterns.add(new JavaAttributeAssignmentPattern());
		patterns.add(new JavaAttributeDeclaration());
		patterns.add(new JavaMethodDeclaration());
		patterns.add(new JavaBlockClose());
		patterns.add(new JavaClassDeclaration());
		patterns.add(new JavaAssignment());
		
		return patterns;
	}

}