package codeconverter.java.templates;

import java.util.ArrayList;
import java.util.List;

import codeconverter.CodeElementProfile;
import codeconverter.CodeTemplate;
import codeconverter.ElementCardinality;
import codeconverter.ICodeElement;
import codeconverter.ICodeTemplate;
import codeconverter.PatternType;
import codeconverter.elements.ClassDefinition;
import codeconverter.java.JavaAttributeDeclaration;
import codeconverter.java.JavaBlockClose;
import codeconverter.java.JavaClassDeclaration;
import codeconverter.java.JavaPackageDeclaration;

public class JavaClassTemplate extends CodeTemplate {

	private ClassDefinition classDefinition=new ClassDefinition();

	public JavaClassTemplate() {
		super("Class");

		addElement(new CodeElementProfile(new JavaPackageDeclaration(),
				ElementCardinality.ONCE));
		addElement(new CodeElementProfile(new JavaClassDeclaration(),
				ElementCardinality.ONCE));
		addElement(new CodeElementProfile(new JavaAttributeDeclaration(),
				ElementCardinality.MORE));
		addElement(new CodeElementProfile(new JavaMethodTemplate(),
				ElementCardinality.MORE));
		addElement(new CodeElementProfile(new JavaBlockClose(),
				ElementCardinality.ONCE));
		addCodePattern(PatternType.CONSTRUCTUR);
		addCodePattern(PatternType.METHOD);
	}

	public ClassDefinition getClassDefinition() {
		return classDefinition;
	}


	public void setup() {
		System.err.println(" clone code piece Java Class Template..");
		//JavaClassTemplate template=new JavaClassTemplate();
		//template.matchedElements.addAll(cloneMatchedElements());

		for (int i=0; i < matchedElements.size(); i++) {
			ICodeTemplate temp=matchedElements.get(i).getMatcher();

			if (temp instanceof JavaClassDeclaration) {
				classDefinition
						.setName(((JavaClassDeclaration) temp).getClassName());
			}
			if (temp instanceof JavaAttributeDeclaration) {
				classDefinition.getAttributoDeclarations().add(
						(((JavaAttributeDeclaration) temp)));
			}
			if (temp instanceof JavaMethodTemplate) {
				classDefinition.getMethods().add(
						(((JavaMethodTemplate) temp)));
			}
			if (temp instanceof JavaConstructorTemplate) {
				classDefinition.getConstructors().add(
						(((JavaConstructorTemplate) temp)));
			}
		}

	}

	@Override
	public List<PatternType> getPatternType() {
		return new ArrayList<PatternType>();
	}
}
