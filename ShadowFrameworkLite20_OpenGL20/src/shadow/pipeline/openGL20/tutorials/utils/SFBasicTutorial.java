package shadow.pipeline.openGL20.tutorials.utils;

import shadow.geometry.geometries.SFMeshGeometry;
import shadow.material.SFStructureReference;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPrimitiveArray;
import shadow.pipeline.SFProgram;
import shadow.pipeline.SFStructureArray;

public class SFBasicTutorial extends SFTutorial{

	private SFProgram program;

	private SFStructureArray lightData;
	private SFStructureReference lightReference;
	
	private SFStructureArray materialData;
	private SFStructureReference materialReference;

	private SFPrimitiveArray primitiveData;
	private int elementIndex;
	private int elementSize;
	
	public SFBasicTutorial(SFProgram program, SFPrimitiveArray primitiveData,
			int elementIndex, int elementSize) {
		super();
		this.program = program;
		this.primitiveData = primitiveData;
		this.elementIndex = elementIndex;
		this.elementSize = elementSize;
	}
	
	
	public SFBasicTutorial(SFProgram program, SFStructureArray lightData,
			SFStructureReference lightReference, SFStructureArray materialData,
			SFStructureReference materialReference,
			SFPrimitiveArray primitiveData, int elementIndex, int elementSize) {
		super();
		this.program = program;
		this.lightData = lightData;
		this.lightReference = lightReference;
		this.materialData = materialData;
		this.materialReference = materialReference;
		this.primitiveData = primitiveData;
		this.elementIndex = elementIndex;
		this.elementSize = elementSize;
	}
	
	public void setGeometry(SFMeshGeometry geometry) {
		this.primitiveData = geometry.getArray();
		this.elementIndex = geometry.getFirstElement();
		this.elementSize = geometry.getElementsCount();
	}
	
	public void init(){
		//init program
		SFPipeline.getSfProgramBuilder().prepareProgram(program);
	}
	
	public void render(){
		
		SFPipeline.getSfProgramBuilder().loadProgram(program);

		//load light data
		if(lightData!=null && lightReference!=null)
			SFPipeline.getSfPipelineGraphics().loadStructureData(lightData, lightReference.getMaterialIndex());
		
		//load material data
		if(materialData!=null && materialReference!=null)
			SFPipeline.getSfPipelineGraphics().loadStructureData(materialData, materialReference.getMaterialIndex());
		
		//load primitives
		SFPipeline.getSfPipelineGraphics().drawPrimitives(primitiveData, elementIndex, elementSize);
	}
}