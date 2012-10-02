package shadow.pipeline;



public interface SFPipelineMemory {

	public SFRigidTransform3fArray generateRigidTransformsArray();

	public SFStructureArray generateStructureData(SFPipelineStructure structure);

	public SFPrimitiveArray generatePrimitiveArray(SFPrimitive primitive);

	public SFPrimitiveArray generatePrimitiveArrayView(SFPrimitiveArray array,SFPrimitive primitive);

	public SFPrimitiveArray getMixArray(SFPrimitiveArray[] primitive,SFPrimitive mixPrimitive);

	public void destroyRigidTransformsArray(SFRigidTransform3fArray array);

	public void destroyStructureData(SFStructureArray array);

	public void destroyPrimitiveArray(SFPrimitiveArray array);

}
