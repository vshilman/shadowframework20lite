package shadow.utils;

import shadow.geometry.geometries.SFMeshGeometry;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPipelineGraphics.Module;
import shadow.pipeline.SFPrimitiveArray;
import shadow.pipeline.SFProgram;
import shadow.pipeline.SFStructureArray;
import shadow.renderer.SFStructureReference;

public class SFBasicTutorial extends SFTutorial{

	private SFProgram program;
	private float[] projection={1,0,0,0,  
								0,1,0,0,	
								0,0,1,0,
								0,0,0,1};
	
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
		
	}
	
	public void render(){
		
		SFPipeline.getSfPipelineGraphics().setupProjection(projection);
		
		SFPipeline.getSfProgramBuilder().loadProgram(program);

		//load light data
		if(lightData!=null && lightReference!=null)
			SFPipeline.getSfPipelineGraphics().loadStructureData(Module.LIGHT, lightData, 0, lightReference.getIndex());
		//carica dati nel modulo light, dai presi da lightdata che è array, 0 è la prima struttura data, indice
		//es index 4, usa i dati per irmpire i dati della struttura 0 del programma
		//load material data
		if(materialData!=null && materialReference!=null)
			SFPipeline.getSfPipelineGraphics().loadStructureData(Module.MATERIAL, materialData, 0, materialReference.getIndex());
		
		//load primitives
		SFPipeline.getSfPipelineGraphics().drawPrimitives(primitiveData, elementIndex, elementSize);
	}
}
