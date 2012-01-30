package codeconverter;


public interface BlockDataInterpreter {

	public CodePattern getBlockDeclarationPattern(String blockDeclaration);
	
	public CodePattern getLineOfCodePattern(String lineOfCode);
}
