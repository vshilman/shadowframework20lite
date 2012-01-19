package codeconverter.java;

import codeconverter.CodePattern;
import codeconverter.ICodeElement;
import codeconverter.ICodePieceSequencer;
import codeconverter.PatternType;
import codeconverter.StaticKeyword;
import codeconverter.elements.IMethodDeclarator;
import codeconverter.elements.MethodDeclaration;
import codeconverter.elements.NamedElement;

public class JavaConstructorDeclaration extends CodePattern implements IMethodDeclarator{

	private JavaName name=new JavaName();
	private JavaModifiersSet javaModifiersSet=new JavaModifiersSet();
	private JavaVariablesList javaVariablesList=new JavaVariablesList();
	//private Variable variable;
	
	public JavaConstructorDeclaration() {
		super("attribute declaration");
		addCodePiece(javaModifiersSet.getSequence(),name,new StaticKeyword("("),
				javaVariablesList.getSequence(),new StaticKeyword(")"));
		addCodePattern(PatternType.CONSTRUCTOR_DECLARATION);
	}
	
	@Override
	public ICodeElement cloneCodePiece() {
		JavaConstructorDeclaration pattern=new JavaConstructorDeclaration();
		pattern.name.data=name.getData();
		ICodePieceSequencer sequence=((ICodePieceSequencer )this.javaModifiersSet.getSequence().cloneCodePiece());
		pattern.javaModifiersSet.loadModifiersSet(sequence);
		sequence=((ICodePieceSequencer )this.javaVariablesList.getSequence().cloneCodePiece());
		pattern.javaVariablesList.loadVariablesList(sequence);
		return pattern;
	}

	@Override
	public String toString() {
		return javaModifiersSet+" "+name.getData()+"("+javaVariablesList+") {";
	}
	

	public MethodDeclaration getMethodDeclaration(){
		MethodDeclaration declaration=new MethodDeclaration();
		declaration.setSet(javaModifiersSet.getModifiers());
		declaration.setList(javaVariablesList.getVariables());
		declaration.setMethodName(new NamedElement(name.data));
		return declaration;
	}
}
