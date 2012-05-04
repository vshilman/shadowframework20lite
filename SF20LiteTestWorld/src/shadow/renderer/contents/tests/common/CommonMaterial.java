package shadow.renderer.contents.tests.common;

import shadow.math.SFVertex3f;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPipelineStructure;
import shadow.pipeline.SFProgramComponent;
import shadow.pipeline.SFStructureArray;
import shadow.pipeline.SFStructureData;
import shadow.renderer.SFObjectModel;
import shadow.renderer.data.SFStructureArrayData;
import shadow.renderer.viewer.SFFrameController;
import shadow.system.SFArrayElementException;

public class CommonMaterial {

	public static SFStructureArray generateMaterial(float[][] colours) throws ArrayIndexOutOfBoundsException{
		SFPipelineStructure materialStructure=((SFProgramComponent)(SFPipeline.getModule("BasicMat"))).getStructures().get(0).getStructure();
		SFStructureArray materialData=SFPipeline.getSfPipelineMemory().generateStructureData(materialStructure); 
		
		for (int j = 0; j < colours.length; j++) {
			SFVertex3f[] material01={new SFVertex3f(colours[j][0],colours[j][1],colours[j][2]),new SFVertex3f(colours[j][0],colours[j][1],colours[j][2])};
			SFStructureData mat=new SFStructureData(materialData.getPipelineStructure());
			for (int i = 0; i < material01.length; i++) {
				((SFVertex3f)mat.getValue(i)).set(material01[i]);
			}
			int index=materialData.generateElement();
			try {
				materialData.setElement(index,mat);
			} catch (SFArrayElementException e) {
				e.printStackTrace();
			}
		}
		
		return materialData;
	}

	public static SFStructureArrayData generateMaterialData(float[][] colours) {
		SFStructureArrayData material=new SFStructureArrayData();
			SFStructureArray materialArray=generateMaterial(colours); 
			material.setArray(materialArray);
		return material;
	}

	public static float[][] generateColours() {
		final float[][] colours={
				{0.5f,0,0},{1,0,0},
				{0,0.5f,0},{0.5f,0.5f,0},
				{0.5f,0,0.5f},{0,0,0.5f},
				{0.5f,0,1},{0.5f,1,0},
				{0.6f,1,0.4f},{0,1,1},
				{1,0.6f,0},{1,0.85f,0},
				{0,0.6f,0.2f},{0.1f,0,0.3f},
				{1,1,1},{0.6f,0.6f,0.6f},{0.0f,0.0f,1.0f}
		};
		return colours;
	}
	
	public static int getColoursSize() {
		return 17;
	}

	public static String[] generateColoursNames() {
		final String[] colours={
				"Dark Red","Red",
				"Dark Green","Yellow",
				"Dark Purple","Dark Blue",
				"Light Purple","Lime Green",
				"Apple Green","Cyan",
				"Orange","Ocra",
				"Forest Green","Navy",
				"White","Gray","Blue"
		};
		return colours;
	}
	
	public static SFFrameController generateColoursController(final SFObjectModel model){
		return new SFFrameController() {
			@Override
			public String getName() {
				return "Colour";
			}
			
			@Override
			public String[] getAlternatives() {
				return generateColoursNames();
			}
			
			@Override
			public void select(int index) {
				model.getModel().getMaterialsStructures().get(0).setRefIndex(index);
			}
		};
	}
}
