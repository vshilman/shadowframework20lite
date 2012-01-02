package codeconverter.java.codelines;

import codeconverter.CodePattern;
import codeconverter.CodeSequence;
import codeconverter.ICodeElement;
import codeconverter.PatternType;
import codeconverter.StaticKeyword;
import codeconverter.UninterpretedEvaluation;
import codeconverter.elements.Variable;
import codeconverter.java.JavaName;

public class JavaMethodAccess  extends CodePattern{

	private JavaName variableName=new JavaName();
	private JavaName methodName=new JavaName();
	private Variable attribute;
	private UninterpretedEvaluation code;
	
	public JavaMethodAccess() {
		super("assignment");
		
		StaticKeyword staticKeyword=new StaticKeyword(";");
		code=new UninterpretedEvaluation(staticKeyword);
		addCodePiece(variableName,new StaticKeyword("."),
				code,staticKeyword);
				//new CodeSequence(code,", "),staticKeyword);
		addCodePattern(PatternType.LINE_OF_CODE);
	}
	
	@Override
	public ICodeElement cloneCodePiece() {
		JavaMethodAccess pattern=new JavaMethodAccess();
		pattern.attribute=new Variable(null,variableName.getData());
//		pattern.expressionCloned=(Expression)(expression.cloneCodePiece());
		return pattern;
	}
	
	@Override
	public String toString() {
		return "\t	"+attribute.getName()+"."+";";
	}
}
