package shadow.pipeline.java;

import shadow.pipeline.SFPipelineMemory;
import shadow.pipeline.SFPipelineStructure;
import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFPrimitiveArray;
import shadow.pipeline.SFRigidTransform3fArray;
import shadow.pipeline.SFStructureArray;

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
	public SFGL20RigidTransforms3fArray generateRigidTransformsArray() {
		return new SFGL20RigidTransforms3fArray();
	}
	
	@Override
	public SFPrimitiveArray generatePrimitiveArrayView(SFPrimitiveArray array, SFPrimitive primitive) {
		return ((SFGL20PrimitiveArray)array).getView(primitive);
	}
	
	@Override
	public SFPrimitiveArray getMixArray(SFPrimitiveArray[] arrays,SFPrimitive mixPrimitive) {
		return SFGL20PrimitiveArray.mixArrays(arrays,mixPrimitive);
	}
	
	@Override
	public void destroyPrimitiveArray(SFPrimitiveArray array) {
		//Do nothing, this is java, GC will do that
	}
	
	@Override
	public void destroyRigidTransformsArray(SFRigidTransform3fArray array) {
		//Do nothing, this is java, GC will do that
	}
	
	@Override
	public void destroyStructureData(SFStructureArray array) {
		//Do nothing, this is java, GC will do that
	}
}
