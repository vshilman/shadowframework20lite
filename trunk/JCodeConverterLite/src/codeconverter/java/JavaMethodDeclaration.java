package codeconverter.java;

import codeconverter.AlternativeCode;
import codeconverter.CodePattern;
import codeconverter.CodeSequence;
import codeconverter.ICodeElement;
import codeconverter.PatternType;
import codeconverter.StaticKeyword;
import codeconverter.UninterpretedEvaluation;
import codeconverter.elements.Variable;

public class JavaMethodDeclaration extends CodePattern{

	private JavaType type=new JavaType();
	private JavaName name=new JavaName();
	private JavaModifiersSet javaModifiersSet=new JavaModifiersSet();
	private JavaVariablesList javaVariablesList=new JavaVariablesList();
	private Variable variable;
	
	public JavaMethodDeclaration() {
		super("attribute declaration");
		addCodePiece(javaModifiersSet.getSequence(),type,name,new StaticKeyword("("),
				javaVariablesList.getSequence(),new StaticKeyword(")"),
				new UninterpretedEvaluation(new AlternativeCode(new StaticKeyword("{"),new StaticKeyword(";"))),
				new AlternativeCode(new StaticKeyword("{"),new StaticKeyword(";")));
		addCodePattern(PatternType.METHOD_DECLARATION);
	}
	
	@Override
	public ICodeElement cloneCodePiece() {
		JavaMethodDeclaration pattern=new JavaMethodDeclaration();
		pattern.variable=new Variable(((JavaType)type.cloneCodePiece()).getType(),name.getData());
		CodeSequence sequence=((CodeSequence )this.javaModifiersSet.getSequence().cloneCodePiece());
		pattern.javaModifiersSet.loadModifiersSet(sequence);
		sequence=((CodeSequence )this.javaVariablesList.getSequence().cloneCodePiece());
		pattern.javaVariablesList.loadVariablesList(sequence);
		return pattern;
	}

	@Override
	public String toString() {
		return javaModifiersSet+variable.getType().getName()+" "+variable.getName()+"("+javaVariablesList+") {";
	}
}
