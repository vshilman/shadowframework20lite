package shadow.pipeline;

public class SFPipelineRenderingState {

	public enum StencilFunction{
        NEVER,
        LESS,
        LEQUAL,
        GREATER,
        GEQUAL,
        EQUAL,
        NOTEQUAL,
        ALWAYS
	}
	
	public enum StencilOperation{
        KEEP,
        ZERO,
        REPLACE,
        INCR,
        INCR_WRAP,
        DECR,
        DECR_WRAP,
        INVERT
	}
	
	public enum AccumulatorOperation{
		ACCUM,
        LOAD,
        ADD,
        MULT,
        RETURN
	}
	
	private boolean depthTest=true;
	private boolean stencilTest=false;
	
	private StencilFunction function;
	private int functionValue;
	private int mask;
	private StencilOperation stencilFail;
	private StencilOperation depthFail;
	private StencilOperation depthPass;
	
	
	public void setStencilTest(StencilFunction function,int functionValue,int mask,
			StencilOperation stencilFail,
			StencilOperation depthFail,StencilOperation depthPass) {
		this.stencilTest = true;
		this.function=function;
		this.functionValue=functionValue;
		this.mask=mask;
		this.stencilFail=stencilFail;
		this.depthFail=depthFail;
		this.depthPass=depthPass;
	}
	
	public void disableStencilTest() {
		this.stencilTest = false;
	}
	
	public void enableDepthTest() {
		this.depthTest = true;
	}
	
	public void disableDepthTest() {
		this.depthTest = false;
	}
	
	public boolean isDepthTest() {
		return depthTest;
	}
	
	public boolean isStencilTest() {
		return stencilTest;
	}

	public StencilFunction getFunction() {
		return function;
	}

	public int getStencilValue() {
		return functionValue;
	}

	public int getStencilMask() {
		return mask;
	}

	public StencilOperation getStencilFail() {
		return stencilFail;
	}

	public StencilOperation getDepthFail() {
		return depthFail;
	}

	public StencilOperation getDepthPass() {
		return depthPass;
	}
	
	
}
