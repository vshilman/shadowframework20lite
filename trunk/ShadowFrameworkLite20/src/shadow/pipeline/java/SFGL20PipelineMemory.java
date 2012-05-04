package shadow.pipeline.java;

import shadow.pipeline.SFPipelineMemory;
import shadow.pipeline.SFPipelineStructure;
import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFPrimitiveArray;
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
	
}
