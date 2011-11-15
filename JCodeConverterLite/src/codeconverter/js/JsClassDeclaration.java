package codeconverter.js;

import codeconverter.CodePattern;
import codeconverter.ICodeElement;
import codeconverter.PatternType;
import codeconverter.StaticKeyword;

public class JsClassDeclaration extends CodePattern{

	private JsName name=new JsName();
	private String className;
	
	public JsClassDeclaration() {
		super("class declaration");
		addCodePiece(name,new StaticKeyword("."),new StaticKeyword("prototype"),
				new StaticKeyword("="),new StaticKeyword("{"));
		addCodePattern(PatternType.CONSTRUCTOR_DECLARATION);
	}
	
	@Override
	public ICodeElement cloneCodePiece() {
		JsClassDeclaration pattern=new JsClassDeclaration();
		className=new String(name.getData());
		return pattern;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	@Override
	public String toString() {
		return name.getData()+".prototype = {";
	}
	
}
