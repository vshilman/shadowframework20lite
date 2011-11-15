package codeconverter.js;

import codeconverter.CodePattern;
import codeconverter.ICodeElement;
import codeconverter.PatternType;
import codeconverter.StaticKeyword;
import codeconverter.elements.Variable;

public class JsReturnPattern extends CodePattern{

	private JsName variableName=new JsName();
	private Variable variable;
	
	public JsReturnPattern() {
		super("return");
		addCodePiece(new StaticKeyword("return"),variableName,new StaticKeyword(";"));
		addCodePattern(PatternType.RETURN,PatternType.LINE_OF_CODE);
	}

	@Override
	public ICodeElement cloneCodePiece() {
		JsReturnPattern pattern=new JsReturnPattern();
		pattern.variable=new Variable(null,variableName.getData());
		return pattern;
	}
	
	@Override
	public String toString() {
		return "\t return "+variable.getName()+";";
	}
}
