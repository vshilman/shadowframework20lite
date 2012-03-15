package shadowMap;
/*
 * shadow mapping: algoritmo a 2 step
 * 1.salvo z.position con vista da luce
 * 2.IF (z.position)<lPosition-position --> pixel in ombra
 * 
 * primo materiale= semplicemente position
 * prima luce=nessuna, fColor=position
 * 
 * secondo materiale=
 * seconda luce=
 * 
 * TODO
 * modificare vista in modo tale da vedere position da luce
 */
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import objLoader.SimpleObjFile;
import shadow.geometry.SFGeometry;
import shadow.image.SFBitmap;
import shadow.image.SFFormat;
import shadow.image.SFRenderedTexture;
import shadow.image.SFTextureData;
import shadow.image.SFTextureData.Filter;
import shadow.image.SFTextureData.WrapMode;
import shadow.math.SFVertex3f;
import shadow.objloader.ShadowObjLoader;
import shadow.objloader.example.ShadowObjLoaderExample;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPipelineModuleWrongException;
import shadow.pipeline.SFPipelineStructure;
import shadow.pipeline.SFPipelineStructureInstance;
import shadow.pipeline.SFProgram;
import shadow.pipeline.SFStructureArray;
import shadow.pipeline.SFStructureData;
import shadow.pipeline.loader.SFProgramComponentLoader;
import shadow.pipeline.openGL20.SFGL20Pipeline;
import shadow.pipeline.openGL20.tutorials.utils.SFTutorial;
import shadow.pipeline.openGL20.tutorials.utils.SFTutorialsUtilities;
import shadow.pipeline.parameters.SFParameter;
import shadow.pipeline.parameters.SFParameteri;
import shadow.renderer.data.SFStructureReference;
import shadow.system.SFArrayElementException;

public class ShadowImage extends SFTutorial {
	
	private float[] projection={1,0,0,0,
								0,1,0,0,
								0,0,1,0,	
								0,0,0,1};
	
	private SFTextureData texture0;
	
	private static ArrayList<SFGeometry> geometries;
	private static SFProgram program;
	private static SFProgram finalprogram;
	
	private static SFStructureArray materialArray;
	private static SFStructureReference materialReference;
	private static SFStructureArray lightArray;
	private static SFStructureReference lightReference;
	
	public static void main(String[] args) {

		SFGL20Pipeline.setup();

		ShadowImage test=new ShadowImage();
		String[] materials={"Position"};
		String[] materials2={"TexturedMat"};
		
		SimpleObjFile file=SimpleObjFile.getFromFile("models/vagone.obj");
		
		//geometric pass: carico oggetto e ne calcolo i vettori di posizione, normali e coord texture
		ShadowObjLoader shadowObjLoader=new ShadowObjLoader();
		geometries=shadowObjLoader.extractGeometries(file);
		
		System.err.println("Number of geometries is "+geometries.size());
		
		try {
			SFProgramComponentLoader.loadComponents(new File("data/primitive"));
			
			ShadowImage.program=SFPipeline.getStaticProgram(shadowObjLoader.getPrimitive(), materials, "NoLights");
			//da modificare, solo per verificare correttezza primo passo che salvo immagine
			ShadowImage.finalprogram=SFPipeline.getStaticImageProgram(materials2, "BasicColor");
			
			
/*			//Material pass: salvataggio delle componenti di colore
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
			((SFVertex3f)mat.getValue(1)).set3f(1, 0, 0);
			((SFVertex3f)mat.getValue(2)).set3f(0, 1, 0);
			try {
				materialReference.setStructureData(mat);
			} catch (SFArrayElementException e) {
				e.printStackTrace();
			}
			
		//light pass: intensità e posizione della luce
		test.lightArray=SFTutorialsUtilities.generateLightData(test.finalprogram, 0);
		SFVertex3f[] lightData={new SFVertex3f(1,1,1),new SFVertex3f(1,0.5f,1)};
		test.lightReference=SFTutorialsUtilities.generateStructureDataReference(test.finalprogram, test.lightArray, lightData);
*/		
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SFPipelineModuleWrongException e) {
			e.printStackTrace();
		}
		
		
		
		test.prepareFrame("Shadow Mapping", 600, 600);
	}
	@Override
	public void init() {
	
		//primo passo del deferred: passaggio da 3D a 2D
		
		texture0 = SFPipeline.getSfTexturePipeline().getRenderedTextureFactory().generateTextureBuffer(600, 600, SFFormat.RGB8,  Filter.LINEAR,
				WrapMode.REPEAT, WrapMode.REPEAT);
		
		SFRenderedTexture renderedTexture=new SFRenderedTexture();
		renderedTexture.addColorData(texture0);
		
		SFPipeline.getSfTexturePipeline().beginNewRenderedTexture(renderedTexture);			
		
			SFPipeline.getSfPipelineGraphics().setupProjection(projection);
			SFPipeline.getSfProgramBuilder().loadProgram(program);
			
			for (int i = 0; i < geometries.size(); i++) {
				geometries.get(i).drawGeometry(0);
			}
			
		SFPipeline.getSfTexturePipeline().endRenderedTexture(renderedTexture);
	}
	
	
	@Override
	public void render() {
	
		texture0.apply(0);
		
		SFPipeline.getSfProgramBuilder().loadProgram(finalprogram);
		
		//SFPipeline.getSfPipelineGraphics().loadStructureData(materialArray, materialReference.getMaterialIndex());
		//SFPipeline.getSfPipelineGraphics().loadStructureData(lightArray, lightReference.getMaterialIndex());
		
		SFPipeline.getSfPipelineGraphics().drawBaseQuad();
		
		
	}
}
