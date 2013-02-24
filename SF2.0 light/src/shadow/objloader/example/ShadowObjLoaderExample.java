package shadow.objloader.example;

/*
 * programma che carica vagoncino
 * 
 * todo: girare mondo su punto di vista della luce
 */
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import objLoader.SimpleObjFile;
import shadow.geometry.SFGeometry;
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

public class ShadowObjLoaderExample extends SFTutorial{

	private static ArrayList<SFGeometry> geometries;
	private static SFProgram program;
	
	static float xL= -1;
	static float yL= 1;
	static float zL= -1;
	
	/*
	 * calcolo matrice di proiezione per vedere mondo da punto di vista della luce
	 * 1. calcolo di 3 matrici differenti
	 * 		a.rescale: per posizionarmi alla stassa distanza che ha la luce
	 * 		b.firstRotation: ruoto intorno a Y per posizionarmi in asse con luce
	 * 		c.secondRotation: ruoto intorno al nuovo asseX, che è il vettore D=(cosA,sinA,0)
	 * 							per questa operazione ho bisogno di una matrice ausiliaria chiamata C
	 * 
	 * 2. calcolo della projection della luce come prodotto delle tre matrici precedenti
	 */
	static float scale = (float)(Math.sqrt(xL*xL+yL*yL+zL*zL));
	private float[] rescale ={scale,0,0,0,  //rotazione lungo Y
							0,scale,0,0,	
							0,0,scale,0,
							0,0,0,1};
	
	static double alpha= Math.atan(zL/xL); // ègià in radianti
	float cosA=(float)(Math.cos(alpha));
	float sinA=(float)(Math.sin(alpha));
	
	private float[] firstRotation ={cosA,0,sinA,0,  //rotazione lungo Y
									0,1,0,0,	
									-sinA,0,cosA,0,
									0,0,0,1};
	
	static double beta= Math.atan(yL/xL);
	float cosB=(float)(Math.cos(beta));
	float sinB=(float)(Math.sin(beta));
	
	private float[] C={0,0,sinA,
					0,0,-sinA,
					-sinA,cosA,0};
	private float[] I={1,0,0,
					0,1,0,
					0,0,1};
	
	private float[] temp= I; //+ sinB*C + (1-cosB)C^2; da usare shadow.math.SFmatrix3F
	
	private float[] secondRotation= {temp[0], temp[1], temp[2], 0, //tranformata in una 4x4
									temp[3], temp[4], temp[5], 0,
									temp[6], temp[7], temp[8], 0,
									0,0,0,1				};
	
	
	//private float[] projectionL --> rotazione du D * rotazione su Y * scaling
	
	private float[]   transform={0.6f,0,0,0,  
			0,0.6f,0,0,	
			0,0,0,-1,
			0,0,1,0};
		
	private float[] projection={1,0,0,0,  //ruoto tutto il mondo
			0,1,0,0,	
			0,0,1,0,
			0,0,0,1};
	
	private static SFStructureArray materialData;
	private static SFStructureReference materialReference;
	private static SFStructureArray lightData;
	private static SFStructureReference lightReference;
	
	public static void main(String[] args) {

		SFGL20Pipeline.setup();

		ShadowObjLoaderExample tut03Bitmap=new ShadowObjLoaderExample();
		//String[] materials={"BasicMat"};
		
		SimpleObjFile file=SimpleObjFile.getFromFile("models/vagone.obj");
		
		ShadowObjLoader shadowObjLoader=new ShadowObjLoader();
		geometries=shadowObjLoader.extractGeometries(file);
		
		System.err.println("Number of geometries is "+geometries.size());
		
		try {
			SFProgramComponentLoader.loadComponents(new File("data/pipeline"),new SFPipelineBuilder());

			ShadowObjLoaderExample.program=SFPipeline.getStaticProgram(shadowObjLoader.getPrimitive(),"BasicPNTransform", "BasicMat",/*"BasicLSPN"*/"SpecularColor");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SFPipelineModuleWrongException e) {
			e.printStackTrace();
		}
		
		//material
		ShadowObjLoaderExample.materialData=SFTutorialsUtilities.generateMaterialData("BasicMat",0/*program, 0,0*/);
		SFVertex3f[] materialData1={new SFVertex3f(1,0,0)/*, new SFVertex3f(0.1f,0.1f,0.1f)*/}; //questa riga Ã¨ sbagliata, non Ã¨ la struttura che si aspetta
		materialReference=SFTutorialsUtilities.generateStructureDataReference(program, ShadowObjLoaderExample.materialData, materialData1);
		
		//Light
		ShadowObjLoaderExample.lightData=SFTutorialsUtilities.generateLightData(program, 0);
		SFVertex3f[] lightData={new SFVertex3f(1, 1, 1),new SFVertex3f(xL,yL,zL)};
		lightReference=SFTutorialsUtilities.generateStructureDataReference(program, ShadowObjLoaderExample.lightData, lightData);
		
		
		
		tut03Bitmap.prepareFrame("Rendered Texture", 600, 600);
	}
	
	@Override
	public void init() {
		//loadImageTexture("models/Chrysanthemum.jpg");
		
		
	}
	
	
	@Override
	public void render() {
		
		SFPipeline.getSfPipelineGraphics().setupTransform(transform);
		SFPipeline.getSfPipelineGraphics().setupProjection(projection);
		
		
		
		SFPipeline.getSfProgramBuilder().loadProgram(program);
		
		//load material data
		if(materialData!=null)
			SFPipeline.getSfPipelineGraphics().loadStructureData(Module.MATERIAL, materialData,0, materialReference.getIndex());
		
		//load light data
		if(lightData!=null)
			SFPipeline.getSfPipelineGraphics().loadStructureData(Module.LIGHT, lightData,0, lightReference.getIndex());
		
		
		for (int i = 0; i < geometries.size(); i++) {
			geometries.get(i).drawGeometry(0);//Lod value is actually ignored.
		}
		
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		super.keyPressed(e);
	}
}
