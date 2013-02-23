package shadow.renderer.contents.tests;

import shadow.math.SFMatrix3f;
import shadow.math.SFVertex3f;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPipelineStructure;
import shadow.pipeline.SFProgramModule;
import shadow.pipeline.SFStructureArray;
import shadow.pipeline.SFStructureData;
import shadow.renderer.SFNode;
import shadow.renderer.SFProgramModuleStructures;
import shadow.renderer.SFReferenceNode;
import shadow.renderer.SFStructureReference;
import shadow.renderer.viewer.SFViewer;
import shadow.system.SFArrayElementException;

/**
 * 
 * @author Alessandro Martinelli
 */
public class Test0610_RainbowQuarter extends SFAbstractTest{
	
	private static final String FILENAME="test0610";
	
	public static void main(String[] args) {
		execute(new Test0610_RainbowQuarter());
	}

	@Override
	public String getFilename() {
		return FILENAME;
	}
	
	@Override
	public void viewTestData() {

		loadLibraryAsDataCenter();
		
		SFNode house1=getAlreadyAvailableDatasetResource("House01");
		SFNode house2=getAlreadyAvailableDatasetResource("House02");
		SFNode house3=getAlreadyAvailableDatasetResource("House03");
		SFNode house4=getAlreadyAvailableDatasetResource("House04");
		SFNode house5=getAlreadyAvailableDatasetResource("House05");
		SFNode house6=getAlreadyAvailableDatasetResource("House06");
		SFNode house7=getAlreadyAvailableDatasetResource("House07");
		SFNode dummy01=getAlreadyAvailableDatasetResource("Dummy01");
		SFNode dummy02=getAlreadyAvailableDatasetResource("Dummy02");
		SFNode dummy03=getAlreadyAvailableDatasetResource("Dummy03");
		SFNode dummy04=getAlreadyAvailableDatasetResource("Dummy04");
		SFNode dummy05=getAlreadyAvailableDatasetResource("Dummy05");
		SFNode dummy06=getAlreadyAvailableDatasetResource("Dummy06");
		SFNode dummy07=getAlreadyAvailableDatasetResource("Dummy07");
		
		
		//SFObjectModel redRoof=getAlreadyAvailableDatasetResource("RedRoof01");
		house1.getTransform().setPosition(new SFVertex3f(3,0,0));
		house2.getTransform().setPosition(new SFVertex3f(-3,0,0));
		house3.getTransform().setPosition(new SFVertex3f(-0.5f,0,2));
		house3.getTransform().setOrientation(SFMatrix3f.getRotationY(0.5f*(float)Math.PI));
		house4.getTransform().setPosition(new SFVertex3f(0.7f,0,-2));
		house5.getTransform().setPosition(new SFVertex3f(-2.0f,0,-3));
		house5.getTransform().setOrientation(SFMatrix3f.getRotationY(-0.5f*(float)Math.PI));
		house6.getTransform().setPosition(new SFVertex3f(3.5f,0,-3));
		house7.getTransform().setPosition(new SFVertex3f(-7.0f,0,-4));
		dummy01.getTransform().setPosition(new SFVertex3f(0,0,-3f));
		dummy02.getTransform().setPosition(new SFVertex3f(2,0,-3.5f));
		dummy03.getTransform().setPosition(new SFVertex3f(4,0,-4f));
		dummy04.getTransform().setPosition(new SFVertex3f(2,0,-4f));
		dummy05.getTransform().setPosition(new SFVertex3f(0,0,-4f));
		dummy06.getTransform().setPosition(new SFVertex3f(-2,0,-5f));
		dummy07.getTransform().setPosition(new SFVertex3f(-4,0,-7f));
		
		//house6.getTransform().setOrientation(SFMatrix3f.getRotationY(0.5f*(float)Math.PI));
		//house4.getTransform().setOrientation(SFMatrix3f.getRotationY(-1.0f*(float)Math.PI));
		
	
		//SFNode testNode=getAlreadyAvailableDatasetResource("TexturedStep");
		
		SFReferenceNode node=new SFReferenceNode();
		node.addNode(house1);
		node.addNode(house2);
		node.addNode(house3);
		node.addNode(house4);
		node.addNode(house5);
		node.addNode(house6);
		node.addNode(house7);
		node.addNode(dummy01);
		node.addNode(dummy02);
		node.addNode(dummy03);
		node.addNode(dummy04);
		node.addNode(dummy05);
		node.addNode(dummy06);
		node.addNode(dummy07);
		//node.addNode(testNode);
		
		node.getTransform().setPosition(new SFVertex3f(0,-0.25f,0));
		node.getTransform().setOrientation(SFMatrix3f.getAmpl(0.1f, 0.1f, 0.1f));
		
		SFViewer.generateFrame(node,SFViewer.getRotationController(),SFViewer.getWireframeController(),
				SFViewer.getZoomController(),SFViewer.getLightStepController());
		for(int i=0;i<10;i++)
			SFViewer.getZoomController().select(0);
		
		
		SFProgramModuleStructures sunLight = generateSunLight();
		SFViewer.getRenderer().setLight(sunLight);
	}
	
	@Override
	public void buildTestData() {
		
		String xmlFileName="test0610Input";
		//String xmlFileName="test0601Input";
		
		compileAndLoadXmlFile(xmlFileName);
		
		store(library);
	}


	private SFProgramModuleStructures generateSunLight() {
		SFPipelineStructure sunLightStructure=((SFProgramModule)(SFPipeline.getProgramModule("RainbowQuarterSunLight"))).
				getComponents()[0].getStructures().get(0).getStructure();//We miss a fast way to get this..
		SFStructureArray lightArray=SFPipeline.getSfPipelineMemory().generateStructureData(sunLightStructure);
		
		SFStructureReference sunMaterialReference=new SFStructureReference(lightArray,lightArray.generateElement()); 
		SFStructureReference backMaterialReference=new SFStructureReference(lightArray,lightArray.generateElement()); 
		SFStructureData light=new SFStructureData(sunLightStructure);
		SFStructureData backlight=new SFStructureData(sunLightStructure);
		try {
			light.getValue(0).set(new SFVertex3f(1,1,1));
			light.getValue(1).set(new SFVertex3f(-0.707f,-0.707f,-0.707f));
			sunMaterialReference.setStructureData(light);
			backlight.getValue(0).set(new SFVertex3f(0.3,0.3,0.8));
			backlight.getValue(1).set(new SFVertex3f(0.55f,0.55f,-0.55f));
			backMaterialReference.setStructureData(backlight);
		} catch (SFArrayElementException e) {
			e.printStackTrace();
		}
		
		SFProgramModuleStructures sunLight=new SFProgramModuleStructures("RainbowQuarterSunLight");
		sunLight.getData().add(sunMaterialReference);
		sunLight.getData().add(backMaterialReference);
		return sunLight;
	}
}
