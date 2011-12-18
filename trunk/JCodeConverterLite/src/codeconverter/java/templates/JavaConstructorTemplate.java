package codeconverter.java.templates;

import java.util.ArrayList;
import java.util.List;

import codeconverter.CodeElementProfile;
import codeconverter.CodeTemplate;
import codeconverter.ElementCardinality;
import codeconverter.PatternType;
import codeconverter.java.JavaBlockClose;
import codeconverter.java.JavaConstructorDeclaration;
import codeconverter.java.codelines.JavaReturnPattern;

public class JavaConstructorTemplate extends CodeTemplate{

	public JavaConstructorTemplate(){
		super("Method");
		
		addElement(new CodeElementProfile(
				new JavaConstructorDeclaration(),ElementCardinality.ONCE
		));
		addElement(new CodeElementProfile(
				new JavaReturnPattern(),ElementCardinality.MORE
		));
		addElement(new CodeElementProfile(
				new JavaBlockClose(),ElementCardinality.ONCE
		));
		addCodePattern(PatternType.CONSTRUCTUR);
		addCodePattern(PatternType.METHOD);
	}
	
}
