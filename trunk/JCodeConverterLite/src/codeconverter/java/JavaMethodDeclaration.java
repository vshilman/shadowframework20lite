package codeconverter.java;

import codeconverter.CodePattern;
import codeconverter.ICodeElement;
import codeconverter.ICodePieceSequencer;
import codeconverter.OptionalCode;
import codeconverter.PatternType;
import codeconverter.StaticKeyword;
import codeconverter.elements.IMethodDeclarator;
import codeconverter.elements.MethodDeclaration;
import codeconverter.elements.NamedElement;
import codeconverter.elements.Variable;

public class JavaMethodDeclaration extends CodePattern implements IMethodDeclarator{

	private JavaType type=new JavaType();
	private JavaName name=new JavaName();
	private JavaModifiersSet javaModifiersSet=new JavaModifiersSet();
	private JavaVariablesList javaVariablesList=new JavaVariablesList();
	private Variable variable;
	
	public JavaMethodDeclaration() {
		super("attribute declaration");
		addCodePiece(new OptionalCode(new StaticKeyword("@Override")),javaModifiersSet.getSequence(),type,name,new StaticKeyword("("),
				javaVariablesList.getSequence(),new StaticKeyword(")")/*,
				new UninterpretedEvaluation(new AlternativeCode(new StaticKeyword("{"),new StaticKeyword(";")))*/);
		addCodePattern(PatternType.METHOD_DECLARATION);
	}
	
	@Override
	public ICodeElement cloneCodePiece() {
		JavaMethodDeclaration pattern=new JavaMethodDeclaration();
		pattern.variable=new Variable(((JavaType)type.cloneCodePiece()).getType(),name.getData());
		ICodePieceSequencer sequence=((ICodePieceSequencer )this.javaModifiersSet.getSequence().cloneCodePiece());
		pattern.javaModifiersSet.loadModifiersSet(sequence);
		sequence=((ICodePieceSequencer )this.javaVariablesList.getSequence().cloneCodePiece());
		pattern.javaVariablesList.loadVariablesList(sequence);
		return pattern;
	}

	@Override
	public String toString() {
		return javaModifiersSet+variable.getType().getName()+" "+variable.getName()+"("+javaVariablesList+") {";
	}
	
	/* (non-Javadoc)
	 * @see codeconverter.java.IMethodDeclarator#getMethodDeclaration()
	 */
	@Override
	public MethodDeclaration getMethodDeclaration(){
		MethodDeclaration declaration=new MethodDeclaration();
		declaration.setSet(javaModifiersSet.getModifiers());
		declaration.setList(javaVariablesList.getVariables());
		declaration.setMethodName(new NamedElement(variable.getName()));
		return declaration;
	}
}
