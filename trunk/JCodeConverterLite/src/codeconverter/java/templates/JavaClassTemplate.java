package codeconverter.java.templates;

import java.util.ArrayList;
import java.util.List;

import codeconverter.CodeElementProfile;
import codeconverter.CodeTemplate;
import codeconverter.ElementCardinality;
import codeconverter.PatternType;
import codeconverter.java.JavaAttributeDeclaration;
import codeconverter.java.JavaBlockClose;
import codeconverter.java.JavaClassDeclaration;
import codeconverter.java.JavaPackageDeclaration;

public class JavaClassTemplate extends CodeTemplate{

	public JavaClassTemplate(){
		super("Method");

		addElement(new CodeElementProfile(
				new JavaPackageDeclaration(),ElementCardinality.ONCE
		));
		addElement(new CodeElementProfile(
				new JavaClassDeclaration(),ElementCardinality.ONCE
		));
		addElement(new CodeElementProfile(
				new JavaAttributeDeclaration(),ElementCardinality.MORE
		));
		addElement(new CodeElementProfile(
				new JavaMethodTemplate(),ElementCardinality.MORE
		));
		addElement(new CodeElementProfile(
				new JavaBlockClose(),ElementCardinality.ONCE
		));
		addCodePattern(PatternType.CONSTRUCTUR);
		addCodePattern(PatternType.METHOD);
	}
	
	@Override
	public List<PatternType> getPatternType() {
		return new ArrayList<PatternType>();
	}
}
