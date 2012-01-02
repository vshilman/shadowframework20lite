package codeconverter.java;

import codeconverter.CodePattern;
import codeconverter.CodeSequence;
import codeconverter.ICodeElement;
import codeconverter.PatternType;
import codeconverter.StaticKeyword;
import codeconverter.UninterpretedEvaluation;
import codeconverter.elements.Variable;

public class JavaAttributeDeclarationAndAssignemnt extends CodePattern {

	private JavaType type=new JavaType();
	private JavaName name=new JavaName();
	private JavaModifiersSet javaModifiersSet=new JavaModifiersSet();
	private Variable variable;

	public JavaAttributeDeclarationAndAssignemnt() {
		super("attribute declaration");
		addCodePiece(javaModifiersSet.getSequence(),type,name,new StaticKeyword("="),
				new UninterpretedEvaluation(new StaticKeyword(";")),
				new StaticKeyword(";"));
		addCodePattern(PatternType.ATTRIBUTE_DECLARATION);
	}

	@Override
	public ICodeElement cloneCodePiece() {
		JavaAttributeDeclarationAndAssignemnt pattern=new JavaAttributeDeclarationAndAssignemnt();
		pattern.variable=new Variable(
				((JavaType) type.cloneCodePiece()).getType(),name.getData());
		CodeSequence sequence=((CodeSequence) this.javaModifiersSet
				.getSequence().cloneCodePiece());
		pattern.javaModifiersSet.loadModifiersSet(sequence);
		return pattern;
	}

	public Variable getVariable() {
		return variable;
	}

	public void setVariable(Variable variable) {
		this.variable = variable;
	}

	@Override
	public String toString() {
		return javaModifiersSet + variable.getType().getName() + " "
				+ variable.getName() + ";";
	}
}
