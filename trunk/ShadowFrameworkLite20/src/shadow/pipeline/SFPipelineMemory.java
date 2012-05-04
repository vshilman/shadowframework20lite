package shadow.pipeline;

import shadow.math.SFTransform3f;


public interface SFPipelineMemory {

	
	public SFRigidTransform3fArray generateRigidTransformsArray();

	public SFStructureArray generateStructureData(SFPipelineStructure structure);

	public SFPrimitiveArray generatePrimitiveArray(SFPrimitive primitive);

}
