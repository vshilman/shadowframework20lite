package shadow.geometry;

import shadow.pipeline.SFPrimitive;
import shadow.pipeline.parameters.SFPipelineRegister;
import shadow.system.data.SFDataset;

public class GLSurfaceFunctionGeometry extends GLSurfaceGeometry{

	GLSurfaceFunction function;
	SFPrimitive primitive;

	public GLSurfaceFunctionGeometry(GLSurfaceFunction function,SFPrimitive primitive) {
		super();
		this.function = function;
		this.primitive = primitive;
	}
	
	@Override
	public SFPipelineRegister[] getGeometricRegisters() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public SFPrimitive getPrimitive() {
		return primitive;
	}
	
	@Override
	public String getTessellator() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public String getCode() {
		return "SurfaceFunctionGeometry";
	}
	
	@Override
	public void build() {
		// TODO Auto-generated method stub	
		//USE function TO EVALUATE AND FILL MATH DATA
		
	}
	
	@Override
	public SFDataset generateNewDatasetInstance() {
		return null;
	}
	
}
