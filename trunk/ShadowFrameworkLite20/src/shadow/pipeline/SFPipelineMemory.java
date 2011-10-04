package shadow.pipeline;

import shadow.math.SFMatrix2f;
import shadow.math.SFMatrix3f;
import shadow.math.SFMatrix4f;
import shadow.math.SFVertex2f;
import shadow.math.SFVertex3f;
import shadow.math.SFVertex4f;
import shadow.system.SFArray;

public interface SFPipelineMemory {

	public SFArray<SFVertex2f> generateVertices2f();

	public SFArray<SFMatrix2f> generateTransforms2f();

	public SFArray<SFVertex3f> generateVertices3f();

	public SFArray<SFMatrix3f> generateTransforms3f();

	public SFArray<SFVertex4f> generateVertices4f();

	public SFArray<SFMatrix4f> generateTransforms4f();

	public SFStructureArray generateStructureData(SFPipelineStructure structure);

	public SFPrimitiveArray generatePrimitiveArray(SFPrimitive primitive);

}
