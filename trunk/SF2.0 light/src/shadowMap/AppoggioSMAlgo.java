package shadowMap;

/*
 * todo:
 * applicare diffferenza di immagini(da aggiustare per visualizzare zone in ombra e zone no) a mondo 3D
 * cioè moltiplica texture ottenuta per basicLSPN
 * 
 * prossimo passo:
 * unisci questo con le immagini del deferred e crea algoritmo unificato
 */
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import oldversion.ShadowImage;

import objLoader.SimpleObjFile;
import shadow.geometry.SFGeometry;
import shadow.image.SFImageFormat;
import shadow.image.SFPipelineTexture;
import shadow.image.SFRenderedTexture;
import shadow.image.SFPipelineTexture.Filter;
import shadow.image.SFPipelineTexture.WrapMode;
import shadow.math.SFVertex3f;
import shadow.objloader.ShadowObjLoader;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPipelineGraphics.Module;
import shadow.pipeline.SFPipelineModuleWrongException;
import shadow.pipeline.SFProgram;
import shadow.pipeline.SFStructureArray;
import shadow.pipeline.builder.SFPipelineBuilder;
import shadow.pipeline.loader.SFProgramComponentLoader;
import shadow.pipeline.openGL20.SFGL20Pipeline;
import shadow.renderer.SFStructureReference;
import shadow.utils.SFTutorial;
import shadow.utils.SFTutorialsUtilities;

public class AppoggioSMAlgo extends SFTutorial{

	private static ArrayList<SFGeometry> geometries;
	private static SFProgram program;
	private static SFProgram finalprogram;
	
	private float[] projection1={1,0,0,0,  
			0,1,0,0,	
			0,0,1,0,
			0,0,0,1};
	
	private float[] projection={0,-1,0,0,  
			1,0,0,0,	
			0,0,1,0,
			0,0,0,1};
	
	private float[] transform={0.6f,0,0,0,  
			0,0.6f,0,0,	
			0,0,0.6f,0,
			0,0,0,1};
	
	private SFPipelineTexture texture0;
	private SFPipelineTexture texture1;
	
	private static SFStructureArray materialData;
	private static SFStructureReference materialReference;
	private static SFStructureArray lightData;
	private static SFStructureReference lightReference;
	
	public static void main(String[] args) {

		SFGL20Pipeline.setup();

		AppoggioSMAlgo tut03Bitmap=new AppoggioSMAlgo();
		//String[] materials={"BasicMat"};
		
		SimpleObjFile file=SimpleObjFile.getFromFile("models/vagone.obj");
		
		ShadowObjLoader shadowObjLoader=new ShadowObjLoader();
		geometries=shadowObjLoader.extractGeometries(file);
		
		System.err.println("Number of geometries is "+geometries.size());
		
		try {
			SFProgramComponentLoader.loadComponents(new File("data/pipeline"),new SFPipelineBuilder());

			AppoggioSMAlgo.program=SFPipeline.getStaticProgram(shadowObjLoader.getPrimitive(),"BasicPNTransform", "TwoPosition", "FirstStepTwo");
			AppoggioSMAlgo.finalprogram=SFPipeline.getStaticImageProgram("MoreTexturedTwo", "SecondStepTwo");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SFPipelineModuleWrongException e) {
			e.printStackTrace();
		}
		
		/*
		//material
		AppoggioDSAlgo.materialData=SFTutorialsUtilities.generateMaterialData("ColorDFMat",0);
		SFVertex3f[] materialData1={new SFVertex3f(1,0,0), new SFVertex3f(1,0,0), new SFVertex3f(1,1,1)};
		materialReference=SFTutorialsUtilities.generateStructureDataReference(program, AppoggioDSAlgo.materialData, materialData1);
		*/
		//Light
		AppoggioSMAlgo.lightData=SFTutorialsUtilities.generateLightData(finalprogram, 0);
		SFVertex3f[] lightData={new SFVertex3f(1, 1, 1),new SFVertex3f(1, 1, -1)};
		lightReference=SFTutorialsUtilities.generateStructureDataReference(finalprogram, AppoggioSMAlgo.lightData, lightData);
		
		tut03Bitmap.prepareFrame("Rendered Texture", 600, 600);
	}
	
	@Override
	public void init() {
		
		texture0 = SFPipeline.getSfTexturePipeline().getRenderedTextureFactory().generateTextureBuffer(600, 600, SFImageFormat.RGB8,  Filter.LINEAR,
				WrapMode.REPEAT, WrapMode.REPEAT);
		texture1 = SFPipeline.getSfTexturePipeline().getRenderedTextureFactory().generateTextureBuffer(600, 600, SFImageFormat.RGB8,  Filter.LINEAR,
				WrapMode.REPEAT, WrapMode.REPEAT);
		
		SFRenderedTexture renderedTexture=new SFRenderedTexture();
		renderedTexture.addColorData(texture0); //diffColor e ambColor
		renderedTexture.addColorData(texture1);
		
		SFPipeline.getSfTexturePipeline().beginNewRenderedTexture(renderedTexture);			
		
			SFPipeline.getSfPipelineGraphics().setupProjection(projection);
			SFPipeline.getSfPipelineGraphics().setupTransform(transform);
			SFPipeline.getSfProgramBuilder().loadProgram(program);
			/*
			//load material data
			if(materialData!=null)
				SFPipeline.getSfPipelineGraphics().loadStructureData(Module.MATERIAL, materialData,0, materialReference.getIndex());
			*/
		
			
			for (int i = 0; i < geometries.size(); i++) {
				geometries.get(i).drawGeometry(0);
			}
			
		SFPipeline.getSfTexturePipeline().endRenderedTexture(renderedTexture);
	}
	
	
	@Override
	public void render() {
		
		
		
		texture0.apply(0);
		texture1.apply(1);
		
		SFPipeline.getSfProgramBuilder().loadProgram(finalprogram);
		
		//load light data
		if(lightData!=null)
			SFPipeline.getSfPipelineGraphics().loadStructureData(Module.LIGHT, lightData,0, lightReference.getIndex());
		
		SFPipeline.getSfPipelineGraphics().drawBaseQuad();
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		super.keyPressed(e);
	}
}
