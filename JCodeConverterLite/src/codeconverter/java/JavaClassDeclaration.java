package codeconverter.java;

import codeconverter.CodePattern;
import codeconverter.CodeSequence;
import codeconverter.ICodeElement;
import codeconverter.OptionalCode;
import codeconverter.PatternType;
import codeconverter.StaticKeyword;

public class JavaClassDeclaration extends CodePattern{

	private JavaName name=new JavaName();
	private JavaModifiersSet javaModifiersSet=new JavaModifiersSet();
	private JavaClassKeywords javaClassExtends;
	private JavaClassKeywords javaClassImplements;
	private String className;

	public JavaClassDeclaration() {
		
		super("class declaration");
		javaClassExtends = new JavaClassKeywords(new StaticKeyword("extends"));
		javaClassImplements = new JavaClassKeywords(new StaticKeyword("implements"));
		addCodePiece(javaModifiersSet.getSequence(),new StaticKeyword("class"),name,javaClassExtends,javaClassImplements,new StaticKeyword("{"));
		addCodePattern(PatternType.CONSTRUCTOR_DECLARATION);
	}
	
	@Override
	public ICodeElement cloneCodePiece() {
		JavaClassDeclaration pattern=new JavaClassDeclaration();
		
		CodeSequence sequence=((CodeSequence )this.javaModifiersSet.getSequence().cloneCodePiece());
		pattern.javaModifiersSet.loadModifiersSet(sequence);
		pattern.className=new String(this.name.getData());
		sequence=((CodeSequence )this.javaClassExtends.getSequence().cloneCodePiece());
        pattern.javaClassExtends.loadSet(sequence);
        sequence=((CodeSequence )this.javaClassImplements.getSequence().cloneCodePiece());
        pattern.javaClassImplements.loadSet(sequence);
		return pattern;
	}
	

	public String getClassName() {
		return className;
	}

	@Override
	public String toString() {
		return javaModifiersSet+"class "+className+ javaClassExtends.getNames()+ javaClassImplements.getNames()+"{";
	}
	
}
