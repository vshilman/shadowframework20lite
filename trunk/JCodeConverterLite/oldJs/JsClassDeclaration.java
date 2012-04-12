package codeconverter.js.oldJs;

import codeconverter.CodePattern;
import codeconverter.ICodeElement;
import codeconverter.PatternType;
import codeconverter.codepieces.UniqueKeyword;

public class JsClassDeclaration extends CodePattern{

	private JsName name=new JsName();
	private String className;
	
	public JsClassDeclaration() {
		super("class declaration");
		addCodePiece(name,new UniqueKeyword("."),new UniqueKeyword("prototype"),
				new UniqueKeyword("="),new UniqueKeyword("{"));
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
