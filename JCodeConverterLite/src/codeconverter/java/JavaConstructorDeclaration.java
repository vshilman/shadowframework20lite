package codeconverter.java;

import codeconverter.CodePattern;
import codeconverter.CodeSequence;
import codeconverter.ICodeElement;
import codeconverter.PatternType;
import codeconverter.StaticKeyword;
import codeconverter.elements.Variable;

public class JavaConstructorDeclaration extends CodePattern{

	private JavaName name=new JavaName();
	private JavaModifiersSet javaModifiersSet=new JavaModifiersSet();
	private JavaVariablesList javaVariablesList=new JavaVariablesList();
	private Variable variable;
	
	public JavaConstructorDeclaration() {
		super("attribute declaration");
		addCodePiece(javaModifiersSet.getSequence(),name,new StaticKeyword("("),
				javaVariablesList.getSequence(),new StaticKeyword(")"),new StaticKeyword("{"));
		addCodePattern(PatternType.CONSTRUCTOR_DECLARATION);
	}
	
	@Override
	public ICodeElement cloneCodePiece() {
		JavaConstructorDeclaration pattern=new JavaConstructorDeclaration();
		pattern.variable=new Variable(null,name.getData());
		CodeSequence sequence=((CodeSequence )this.javaModifiersSet.getSequence().cloneCodePiece());
		pattern.javaModifiersSet.loadModifiersSet(sequence);
		sequence=((CodeSequence )this.javaVariablesList.getSequence().cloneCodePiece());
		pattern.javaVariablesList.loadVariablesList(sequence);
		return pattern;
	}

	@Override
	public String toString() {
		return javaModifiersSet+" "+variable.getName()+"("+javaVariablesList+") {";
	}
}
