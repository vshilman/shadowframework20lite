package codeconverter.java.templates;

import codeconverter.CodeElementProfile;
import codeconverter.CodeTemplate;
import codeconverter.ElementCardinality;
import codeconverter.PatternType;
import codeconverter.java.JavaBlockClose;
import codeconverter.java.JavaMethodDeclaration;
import codeconverter.java.codelines.JavaReturnPattern;

public class JavaMethodTemplate extends CodeTemplate{

	public JavaMethodTemplate(){
		super("Method");
		
		addElement(new CodeElementProfile(
				new JavaMethodDeclaration(),ElementCardinality.ONCE
		));
		addElement(new CodeElementProfile(
				new JavaReturnPattern(),ElementCardinality.MORE
		));
		addElement(new CodeElementProfile(
				new JavaBlockClose(),ElementCardinality.ONCE
		));
		addCodePattern(PatternType.METHOD);
	}
	
}
