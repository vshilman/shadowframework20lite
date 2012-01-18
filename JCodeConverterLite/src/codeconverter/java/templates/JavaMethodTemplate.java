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
import codeconverter.java.JavaAttributeDeclaration;
import codeconverter.java.JavaBlockClose;
import codeconverter.java.JavaClassDeclaration;
import codeconverter.java.JavaMethodDeclaration;
import codeconverter.java.codelines.JavaReturnPattern;

public class JavaMethodTemplate extends CodeTemplate implements IMethod{

	private Method method=new Method();
	
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
	
	@Override
	public void setup() {
		
		for (int i=0; i < matchedElements.size(); i++) {
			ICodeTemplate temp=matchedElements.get(i).getMatcher();

			if (temp instanceof JavaMethodDeclaration) {
				method.setDeclaration(((JavaMethodDeclaration)temp).getMethodDeclaration());
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
