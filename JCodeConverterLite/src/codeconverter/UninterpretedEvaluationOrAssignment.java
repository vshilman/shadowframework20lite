package codeconverter;

public class UninterpretedEvaluationOrAssignment extends UninterpretedEvaluation{

	public UninterpretedEvaluationOrAssignment(ICodePiece followingPiece) {
		super(followingPiece);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public char lastCharacter() {
		return ')';
	}
	
	@Override
	public boolean isCharacterOk(char c) {
		return (c>='a' && c<='z') ||
			(c>='A' && c<='Z') || 
			(c>='0' && c<='9') || 
			(c==',' || c==')' || c=='(' || c=='+' || c=='*' || c=='/' || c=='-' || c=='%'
				 || c=='"' || c=='.' || c==' ' || c=='\t'  || c=='!' || c=='&' || c=='|' || c=='=');
	}

}
