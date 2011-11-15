package codeconverter.java;

import codeconverter.CodePattern;
import codeconverter.CodeSequence;
import codeconverter.ICodeElement;
import codeconverter.PatternType;
import codeconverter.StaticKeyword;

public class JavaClassDeclaration extends CodePattern{

	private JavaName name=new JavaName();
	private JavaModifiersSet javaModifiersSet=new JavaModifiersSet();
	private String className;
	
	public JavaClassDeclaration() {
		super("class declaration");
		addCodePiece(javaModifiersSet.getSequence(),new StaticKeyword("class"),name,new StaticKeyword("{"));
		addCodePattern(PatternType.CONSTRUCTOR_DECLARATION);
	}
	
	@Override
	public ICodeElement cloneCodePiece() {
		JavaClassDeclaration pattern=new JavaClassDeclaration();
		CodeSequence sequence=((CodeSequence )this.javaModifiersSet.getSequence().cloneCodePiece());
		pattern.javaModifiersSet.loadModifiersSet(sequence);
		pattern.className=new String(this.name.getData());
		return pattern;
	}
	

	public String getClassName() {
		return className;
	}

	@Override
	public String toString() {
		return javaModifiersSet+"class "+className+"{";
	}
	
}
