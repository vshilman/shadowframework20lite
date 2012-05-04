package shadow.pipeline.expression;

public class SFExpressionGeneratorKeeper {

	private static SFExpressionGeneratorKeeper keeper=new SFExpressionGeneratorKeeper();
	
	private SFExpressionGeneratorKeeper(){
		
	}

	public static SFExpressionGeneratorKeeper getKeeper() {
		return keeper;
	}

	private SFIExpressionGenerator generator;

	public SFIExpressionGenerator getGenerator() {
		return generator;
	}

	public void setGenerator(SFIExpressionGenerator generator) {
		this.generator = generator;
	}
	
}
