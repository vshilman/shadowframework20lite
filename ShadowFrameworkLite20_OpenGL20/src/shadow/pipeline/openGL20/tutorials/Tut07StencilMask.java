package shadow.pipeline.openGL20.tutorials;

import java.awt.event.KeyEvent;

import shadow.geometry.geometries.SFMeshGeometry;
import shadow.image.SFBitmap;
import shadow.image.SFPipelineTexture;
import shadow.image.SFPipelineTexture.Filter;
import shadow.image.SFPipelineTexture.WrapMode;
import shadow.image.bitmaps.SFSpecialPerlinNoise;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPipelineRenderingState;
import shadow.pipeline.SFPipelineRenderingState.StencilFunction;
import shadow.pipeline.SFPipelineRenderingState.StencilOperation;
import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFPrimitiveArray;
import shadow.pipeline.SFProgram;
import shadow.pipeline.openGL20.SFGL20Pipeline;
import shadow.pipeline.openGL20.tutorials.geometriesExample.Cone;
import shadow.pipeline.openGL20.tutorials.geometriesExample.LateralTube;
import shadow.pipeline.openGL20.tutorials.geometriesExample.Mushroom;
import shadow.pipeline.openGL20.tutorials.geometriesExample.StrangeCylinder;
import shadow.pipeline.openGL20.tutorials.geometriesExample.StrangeGlass;
import shadow.pipeline.openGL20.tutorials.utils.SFBasicTutorial;
import shadow.pipeline.openGL20.tutorials.utils.SFTutorial;
import shadow.pipeline.openGL20.tutorials.utils.SFTutorialsUtilities;

public class Tut07StencilMask extends SFTutorial{

	private static final long serialVersionUID=0;
	private SFProgram program;
	private SFProgram programTexture;
	private float[] projection={1,0,0,0,
								0,1,0,0,	
								0,0,1,0,
								0,0,0,1};
	
	private static SFMeshGeometry[] geometries;
	private int geometriesIndex=0;

	private SFPipelineTexture texture;
	
	private SFPipelineRenderingState maskGeneration=new SFPipelineRenderingState();
	private SFPipelineRenderingState maskApplication=new SFPipelineRenderingState();
	private SFPipelineRenderingState noEffects=new SFPipelineRenderingState();
	private boolean maskEffect=true;
	
	public Tut07StencilMask(SFProgram program,
			SFPrimitiveArray primitiveData, int elementIndex, int elementSize) {

		this.program = program;
		this.primitiveData = primitiveData;
		this.elementIndex = elementIndex;
		this.elementSize = elementSize;
	
		maskGeneration.disableDepthTest();
		maskGeneration.setStencilTest(StencilFunction.ALWAYS, 0, 0xffffffff, StencilOperation.INCR, StencilOperation.INCR, StencilOperation.INCR);
		maskApplication.disableDepthTest();
		maskApplication.setStencilTest(StencilFunction.LESS, 0, 0xffffffff, StencilOperation.KEEP, StencilOperation.KEEP, StencilOperation.KEEP);
	}
	
	
	private SFPrimitiveArray primitiveData;
	private int elementIndex;
	private int elementSize;

	public static void main(String[] args) {

		SFGL20Pipeline.setup();
		
		SFPrimitive primitive=new SFPrimitive();
		String[] materials={"BlackMat"};
		SFProgram program=SFTutorialsUtilities.generateProgram("data/pipeline/primitive", materials, 
				"NoLights", primitive, "Triangle2", "Triangle2", "BasicTess");

		geometries=generateGeometries(primitive);
		
		Tut07StencilMask tut07stencilMask=new Tut07StencilMask(program, 
				geometries[0].getArray(), geometries[0].getFirstElement(),geometries[0].getElementsCount());
		
		String[] materials02={"TexturedMat"};
		tut07stencilMask.programTexture=SFTutorialsUtilities.generateImageProgram("data/pipeline/primitive", materials02, "BasicYellowColor");

		
		tut07stencilMask.prepareFrame("Curved Tube Function", 600, 600);
		
	}


	public static SFMeshGeometry[] generateGeometries(SFPrimitive primitive){
		SFMeshGeometry[] geometries= {
				(new StrangeCylinder()).generateGeometry(primitive),
				(new Cone()).generateGeometry(primitive),
				(new LateralTube()).generateGeometry(primitive),
				(new StrangeGlass()).generateGeometry(primitive),
				(new Mushroom()).generateGeometry(primitive)
		};
		return geometries;
	}
	
	public void setGeometry(SFMeshGeometry geometry) {
		this.primitiveData = geometry.getArray();
		this.elementIndex = geometry.getFirstElement();
		this.elementSize = geometry.getElementsCount();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		super.keyPressed(e);
		if(e.getKeyCode()==KeyEvent.VK_PLUS){
			geometriesIndex++;
			if(geometriesIndex>=geometries.length){
				geometriesIndex=0;
			}
			setGeometry(geometries[geometriesIndex]);
		}
		if(e.getKeyCode()==KeyEvent.VK_MINUS){
			geometriesIndex--;
			if(geometriesIndex<0){
				geometriesIndex=geometries.length-1;
			}
			setGeometry(geometries[geometriesIndex]);
		}
		if(e.getKeyCode()==KeyEvent.VK_A){
			maskEffect=!maskEffect;
		}
	}
	
	@Override
	public void init() {
		float[] weights={0.1f,0.4f,0.3f};
		float[] colors={1,1,0,
						1,0,0,
						1,0,1};
		SFSpecialPerlinNoise.randomizeFunction();
		SFBitmap bitmap=new SFSpecialPerlinNoise(200, 200, weights, colors);
		
		texture=SFPipeline.getSfTexturePipeline().getRenderedTextureFactory().generateBitmapTexture(bitmap, Filter.LINEAR_MIPMAP,
				WrapMode.REPEAT, WrapMode.REPEAT);
	}
	
	@Override
	public void render() {

		SFPipeline.getSfPipelineGraphics().setupProjection(SFBasicTutorial.projection);
		
		if(maskEffect)
			SFPipeline.getSfPipelineGraphics().setPipelineState(maskGeneration);
		else
			SFPipeline.getSfPipelineGraphics().setPipelineState(noEffects);
		
		SFPipeline.getSfPipelineGraphics().setupProjection(projection);
		
		SFPipeline.getSfProgramBuilder().loadProgram(program);
		
		//load primitives
		SFPipeline.getSfPipelineGraphics().drawPrimitives(primitiveData, elementIndex, elementSize);

		if(maskEffect){
		
			SFPipeline.getSfPipelineGraphics().setPipelineState(maskApplication);
			
			texture.apply(0);
			
			SFPipeline.getSfProgramBuilder().loadProgram(programTexture);
	
			SFPipeline.getSfPipelineGraphics().drawBaseQuad();
		}
	}
}
