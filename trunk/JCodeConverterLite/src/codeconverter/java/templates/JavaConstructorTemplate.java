package codeconverter.java.templates;

import java.util.List;

import codeconverter.CodeElementProfile;
import codeconverter.CodeTemplate;
import codeconverter.ElementCardinality;
import codeconverter.ICodeElement;
import codeconverter.ICodeTemplate;
import codeconverter.IMethod;
import codeconverter.PatternType;
import codeconverter.elements.Method;
import codeconverter.java.JavaBlockClose;
import codeconverter.java.JavaConstructorDeclaration;
import codeconverter.java.codelines.JavaReturnPattern;

public class JavaConstructorTemplate extends CodeTemplate implements IMethod{

	private Method method=new Method();
	
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

	@Override
	public void setup() {
		System.err.println(" clone code piece Java Constructor Declaration Template..");
		
		for (int i=0; i < matchedElements.size(); i++) {
			ICodeTemplate temp=matchedElements.get(i).getMatcher();

			if (temp instanceof JavaConstructorDeclaration) {
				method.setDeclaration(((JavaConstructorDeclaration)temp).getMethodDeclaration());
			}
			if (temp instanceof JavaReturnPattern) {
//				template.classDefinition.getAttributoDeclarations().add(
//						(((JavaAttributeDeclaration) temp)));
			}
			if (temp instanceof JavaBlockClose) {
				//nothing to do here...
//				template.classDefinition.getMethods().add(
//						(((JavaMethodTemplate) temp)));
			}
		}
		
	}
	
	@Override
	public List<ICodeTemplate> getCodeLines() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Method getMethodDeclaration() {
		return method;
	}
	
}
