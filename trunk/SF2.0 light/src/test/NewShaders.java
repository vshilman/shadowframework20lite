package test;
/*
 * creo diversi shader e li provo qui, primo passo verso Deferred
 * 1.shader delle normali
 * 2.shader del colore diffuso
 * 3.shader del colore speculare
 */
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import shadow.geometry.geometries.SFMeshGeometry;
import shadow.math.SFVertex3f;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPipelineModuleWrongException;
import shadow.pipeline.SFPipelineStructure;
import shadow.pipeline.SFPipelineStructureInstance;
import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFPrimitive.PrimitiveBlock;
import shadow.pipeline.SFProgram;
import shadow.pipeline.SFProgramComponent;
import shadow.pipeline.SFStructureArray;
import shadow.pipeline.SFStructureData;
import shadow.pipeline.builder.SFPipelineBuilder;
import shadow.pipeline.loader.SFProgramComponentLoader;
import shadow.pipeline.openGL20.SFGL20Pipeline;
import shadow.pipeline.openGL20.tutorials.geometriesExample.Cone;
import shadow.pipeline.openGL20.tutorials.geometriesExample.LateralTube;
import shadow.pipeline.openGL20.tutorials.geometriesExample.Mushroom;
import shadow.pipeline.openGL20.tutorials.geometriesExample.StrangeCylinder;
import shadow.pipeline.openGL20.tutorials.geometriesExample.StrangeGlass;
import shadow.pipeline.openGL20.tutorials.utils.SFTutorial;
import shadow.pipeline.openGL20.tutorials.utils.SFTutorialsUtilities;
import shadow.pipeline.parameters.SFParameter;
import shadow.pipeline.parameters.SFParameteri;
import shadow.pipeline.parameters.SFPipelineRegister;
import shadow.renderer.SFStructureReference;
import shadow.system.SFArrayElementException;
import shadow.system.SFException;

public class NewShaders extends SFTutorial {
	
	private static SFProgram programShow;
	
	private SFMeshGeometry geometry;
	private SFMeshGeometry[] geometries;
	private int geometriesIndex=0;
	
	private static SFStructureReference materialReference;
	private static SFStructureArray materialArray;
	
	private SFStructureReference lightReference;
	private SFStructureArray lightArray;
	
	
	
	public static void main(String[] args) {

		SFGL20Pipeline.setup();
		
		NewShaders test=new NewShaders();
		String[] materials={"ColorsMat"}; //legge ambColor, ma nn lo usa

		try {
			SFProgramComponentLoader.loadComponents(new File("data/primitive"), new SFPipelineBuilder());
			
			//recupero info di normali, posizione e coord.text
			SFPrimitive primitive=new SFPrimitive();
			primitive.addPrimitiveElement(PrimitiveBlock.NORMAL, (SFProgramComponent)(SFPipeline.getModule("Triangle2")));
			primitive.addPrimitiveElement(PrimitiveBlock.POSITION, (SFProgramComponent)(SFPipeline.getModule("Triangle2")));
			primitive.addPrimitiveElement(PrimitiveBlock.TXO, (SFProgramComponent)(SFPipeline.getModule("Triangle2")));
			primitive.setAdaptingTessellator((SFProgramComponent)(SFPipeline.getModule("BasicTess")));
			
			//programma con shader di 'basicMat' per i materiali e 'basicLSPN' per le luci
			test.programShow=SFPipeline.getStaticProgram(primitive,materials, "Specular");
			
			//info sulle geometrie da disegnare
			test.geometries=generateGeometries(primitive);
			test.geometry = (new Mushroom()).generateGeometry(primitive);

			//Material: aggiunta di un parametro
			SFPipelineStructure materialStructure=SFPipeline.getStructure("Mat02");
			List<SFParameteri> parameters=new ArrayList<SFParameteri>();
			parameters.add(new SFParameter("mat01",SFParameteri.GLOBAL_FLOAT3));
			parameters.add(new SFParameter("mat02",SFParameteri.GLOBAL_FLOAT3));
			parameters.add(new SFParameter("mat03",SFParameteri.GLOBAL_FLOAT3));
			SFPipelineStructureInstance materialStructureInstance=new SFPipelineStructureInstance(materialStructure,parameters);
			materialArray=SFPipeline.getSfPipelineMemory().generateStructureData(materialStructureInstance.getStructure()); 
			materialReference=new SFStructureReference(materialArray,materialArray.generateElement()); 
			SFStructureData mat=new SFStructureData(materialStructureInstance.getStructure());
			((SFVertex3f)mat.getValue(0)).set3f(1, 0, 0);
			((SFVertex3f)mat.getValue(1)).set3f(0, 1, 0);
			((SFVertex3f)mat.getValue(2)).set3f(0, 0, 1);
			try {
				materialReference.setStructureData(mat);
			} catch (SFArrayElementException e) {
				e.printStackTrace();
			}
			//creo i parametri da passar allo shader delle luci
			test.lightArray=SFTutorialsUtilities.generateLightData(test.programShow, 0);
			//in BasicLSPN si tratta di intensita' e posizione
			SFVertex3f[] lightData={new SFVertex3f(1,1,1),new SFVertex3f(-1,0,-1)};
			test.lightReference=SFTutorialsUtilities.generateStructureDataReference(test.programShow, test.lightArray, lightData);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SFPipelineModuleWrongException e) {
			e.printStackTrace();
		} catch (SFException exception) {
			exception.printStackTrace();
		}
		
		test.prepareFrame("Application of different shaders", 600, 600);	
	
	}
	
	@Override
	public void init() {
		//non c'è nulla da fare qui
	}
	@Override
	public void render() {
			
			
				SFPipeline.getSfProgramBuilder().loadProgram(programShow);
			
				SFPipeline.getSfPipelineGraphics().loadStructureData(materialArray, materialReference.getMaterialIndex());
				SFPipeline.getSfPipelineGraphics().loadStructureData(lightArray, lightReference.getMaterialIndex());
				
				SFVertex3f position = new SFVertex3f(-0.5, 0.0, 0.0);
				SFPipeline.getSfPipelineGraphics().translateModel(position);
				SFPipeline.getSfPipelineGraphics().drawPrimitives(geometries[geometriesIndex].getArray(), geometries[geometriesIndex].getFirstElement(), geometries[geometriesIndex].getElementsCount());
				
				SFVertex3f position1 = new SFVertex3f(0.5, 0.0, 0.0);
				SFPipeline.getSfPipelineGraphics().translateModel(position1);
				SFPipeline.getSfPipelineGraphics().drawPrimitives(geometry.getArray(), geometry.getFirstElement(), geometry.getElementsCount());
	}

	
	public static SFMeshGeometry[] generateGeometries(SFPrimitive primitive){
		SFMeshGeometry[] geometries= {
				(new Mushroom()).generateGeometry(primitive),
				(new StrangeCylinder()).generateGeometry(primitive),
				(new Cone()).generateGeometry(primitive),
				(new LateralTube()).generateGeometry(primitive),
				(new StrangeGlass()).generateGeometry(primitive)
		};
		return geometries;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		super.keyPressed(e);
		if(e.getKeyCode()==KeyEvent.VK_PLUS){
			geometriesIndex++;
			if(geometriesIndex>=geometries.length){
				geometriesIndex=0;
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_MINUS){
			geometriesIndex--;
			if(geometriesIndex<0){
				geometriesIndex=geometries.length-1;
			}
		}
	}
}
