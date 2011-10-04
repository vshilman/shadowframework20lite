package shadow.pipeline.openGL20;

import shadow.math.SFMatrix2f;
import shadow.math.SFMatrix3f;
import shadow.math.SFMatrix4f;
import shadow.math.SFVertex2f;
import shadow.math.SFVertex3f;
import shadow.math.SFVertex4f;
import shadow.pipeline.SFPipelineMemory;
import shadow.pipeline.SFPipelineStructure;
import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFPrimitiveArray;
import shadow.pipeline.SFStructureArray;
import shadow.system.SFArray;

public class SFGL20PipelineMemory implements SFPipelineMemory{

	@Override
	public SFPrimitiveArray generatePrimitiveArray(SFPrimitive primitive) {
		return new SFGL20PrimitiveArray(primitive);
	}
	
	@Override
	public SFStructureArray generateStructureData(SFPipelineStructure structure) {
		return new SFGL20StructureArray(structure);
	}
	
	
	@Override
	public SFArray<SFMatrix2f> generateTransforms2f() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public SFArray<SFMatrix3f> generateTransforms3f() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public SFArray<SFMatrix4f> generateTransforms4f() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public SFArray<SFVertex2f> generateVertices2f() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public SFArray<SFVertex3f> generateVertices3f() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public SFArray<SFVertex4f> generateVertices4f() {
		// TODO Auto-generated method stub
		return null;
	}
}
