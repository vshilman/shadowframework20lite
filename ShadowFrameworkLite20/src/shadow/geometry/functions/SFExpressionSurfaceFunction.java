package shadow.geometry.functions;

import shadow.math.SFValuenf;
import shadow.math.SFVertex3f;
import shadow.pipeline.expression.SFExpressionElement;
import shadow.pipeline.expression.SFExpressionValuesList;
import shadow.pipeline.parameters.SFParameter;
import shadow.pipeline.parameters.SFParameteri;

public class SFExpressionSurfaceFunction extends SFUnoptimizedSurfaceFunction{

	private class UVValueList implements SFExpressionValuesList{
		@Override
		public SFValuenf generateValue() {
			return new SFVertex3f(0,0,0);
		}
		@Override
		public SFValuenf getValue(int index) {
			if(index==0)
				return new SFVertex3f(u,u,u);
			return new SFVertex3f(v,v,v);
		}
	}
	
	private SFExpressionElement expression;
	private SFValuenf value;
	private float u,v;
	private UVValueList valueList=new UVValueList();
	
	public SFExpressionSurfaceFunction(SFExpressionElement expression) {
		super();
		SFParameteri[] parameters={new SFParameter("u", SFParameteri.GLOBAL_FLOAT),
				new SFParameter("v", SFParameteri.GLOBAL_FLOAT)};
		this.expression=expression.cloneAsIndexed(parameters);
	}

	@Override
	public float getX(float u, float v) {
		this.u=u;
		this.v=v;
		this.value=this.expression.evaluate(valueList);
		return value.get()[0];
	}
	
	@Override
	public float getY(float u, float v) {
		return value.get()[1];
	}
	
	@Override
	public float getZ(float u, float v) {
		return value.get()[2];
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void init() {
		// TODO Auto-generated method stub	
	}
	
}
