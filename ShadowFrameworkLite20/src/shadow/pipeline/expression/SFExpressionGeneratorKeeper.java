package shadow.pipeline.expression;

public class SFExpressionGeneratorKeeper {

	private static SFExpressionGeneratorKeeper keeper=new SFExpressionGeneratorKeeper();
	
	private SFExpressionGeneratorKeeper(){
		
	}

	public static SFExpressionGeneratorKeeper getKeeper() {
		return keeper;
	}

	private SFExpressionGeneratori generator;

	public SFExpressionGeneratori getGenerator() {
		return generator;
	}

	public void setGenerator(SFExpressionGeneratori generator) {
		this.generator = generator;
	}
	
}
