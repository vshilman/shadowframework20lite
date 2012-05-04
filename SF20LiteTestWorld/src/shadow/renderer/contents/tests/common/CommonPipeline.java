package shadow.renderer.contents.tests.common;

import java.io.File;
import java.io.IOException;

import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPipelineModuleWrongException;
import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFProgramComponent;
import shadow.pipeline.SFPrimitive.PrimitiveBlock;
import shadow.pipeline.builder.SFPipelineBuilder;
import shadow.pipeline.loader.SFProgramComponentLoader;
import shadow.pipeline.openGL20.SFGL20Pipeline;
import shadow.pipeline.parameters.SFPipelineRegister;
import shadow.system.SFException;

public class CommonPipeline {

	private SFPrimitive testPrimitive=new SFPrimitive();
	private SFPrimitive primitive=new SFPrimitive();
	private SFPrimitive primitive3=new SFPrimitive();
	private SFPrimitive texturePrimitive=new SFPrimitive();
	private SFPrimitive bumpMapPrimitive=new SFPrimitive();
	private SFPrimitive trianglesPrimitive=new SFPrimitive();
	private SFPrimitive trianglesTexturePrimitive=new SFPrimitive();
	private SFPrimitive trianglesbumpMapPrimitive=new SFPrimitive();


	public CommonPipeline() {
		prepare();
		try {
			testPrimitive.addPrimitiveElement(PrimitiveBlock.POSITION, (SFProgramComponent)(SFPipeline.getModule("Triangle2")));
			testPrimitive.setAdaptingTessellator((SFProgramComponent)(SFPipeline.getModule("BasicTess")));

			primitive.addPrimitiveElement(PrimitiveBlock.NORMAL, (SFProgramComponent)(SFPipeline.getModule("Triangle2")));
			primitive.addPrimitiveElement(PrimitiveBlock.POSITION, (SFProgramComponent)(SFPipeline.getModule("Triangle2")));
			primitive.setAdaptingTessellator((SFProgramComponent)(SFPipeline.getModule("BasicTess")));

			primitive3.addPrimitiveElement(PrimitiveBlock.NORMAL, (SFProgramComponent)(SFPipeline.getModule("Triangle3")));
			primitive3.addPrimitiveElement(PrimitiveBlock.POSITION, (SFProgramComponent)(SFPipeline.getModule("Triangle3")));
			primitive3.setAdaptingTessellator((SFProgramComponent)(SFPipeline.getModule("BasicTess")));

			texturePrimitive.addPrimitiveElement(PrimitiveBlock.NORMAL, (SFProgramComponent)(SFPipeline.getModule("Triangle2")));
			texturePrimitive.addPrimitiveElement(PrimitiveBlock.TXO, (SFProgramComponent)(SFPipeline.getModule("Triangle2")));
			texturePrimitive.addPrimitiveElement(PrimitiveBlock.POSITION, (SFProgramComponent)(SFPipeline.getModule("Triangle2")));
			texturePrimitive.setAdaptingTessellator((SFProgramComponent)(SFPipeline.getModule("BasicTess")));
			
			trianglesPrimitive.addPrimitiveElement(PrimitiveBlock.NORMAL, (SFProgramComponent)(SFPipeline.getModule("Triangle")));
			trianglesPrimitive.addPrimitiveElement(PrimitiveBlock.POSITION, (SFProgramComponent)(SFPipeline.getModule("Triangle")));
			trianglesPrimitive.setAdaptingTessellator((SFProgramComponent)(SFPipeline.getModule("BasicTess")));

			trianglesTexturePrimitive.addPrimitiveElement(PrimitiveBlock.NORMAL, (SFProgramComponent)(SFPipeline.getModule("Triangle")));
			trianglesTexturePrimitive.addPrimitiveElement(PrimitiveBlock.POSITION, (SFProgramComponent)(SFPipeline.getModule("Triangle")));
			trianglesTexturePrimitive.addPrimitiveElement(PrimitiveBlock.TXO, (SFProgramComponent)(SFPipeline.getModule("Triangle")));
			trianglesTexturePrimitive.setAdaptingTessellator((SFProgramComponent)(SFPipeline.getModule("BasicTess")));

			bumpMapPrimitive.addPrimitiveElement(PrimitiveBlock.POSITION, (SFProgramComponent)(SFPipeline.getModule("Triangle2")));
			bumpMapPrimitive.addPrimitiveElement(PrimitiveBlock.NORMAL, (SFProgramComponent)(SFPipeline.getModule("Triangle2")));
			bumpMapPrimitive.addPrimitiveElement(PrimitiveBlock.TXO, (SFProgramComponent)(SFPipeline.getModule("Triangle2")));
			bumpMapPrimitive.addPrimitiveElement(PrimitiveBlock.DU, (SFProgramComponent)(SFPipeline.getModule("Triangle2")));
			bumpMapPrimitive.addPrimitiveElement(PrimitiveBlock.DV, (SFProgramComponent)(SFPipeline.getModule("Triangle2")));
			bumpMapPrimitive.setAdaptingTessellator((SFProgramComponent)(SFPipeline.getModule("BasicTess")));

			trianglesbumpMapPrimitive.addPrimitiveElement(PrimitiveBlock.NORMAL, (SFProgramComponent)(SFPipeline.getModule("Triangle")));
			trianglesbumpMapPrimitive.addPrimitiveElement(PrimitiveBlock.POSITION, (SFProgramComponent)(SFPipeline.getModule("Triangle")));
			trianglesbumpMapPrimitive.addPrimitiveElement(PrimitiveBlock.TXO, (SFProgramComponent)(SFPipeline.getModule("Triangle")));
			trianglesbumpMapPrimitive.addPrimitiveElement(PrimitiveBlock.DU, (SFProgramComponent)(SFPipeline.getModule("Triangle")));
			trianglesbumpMapPrimitive.addPrimitiveElement(PrimitiveBlock.DV, (SFProgramComponent)(SFPipeline.getModule("Triangle")));
			trianglesbumpMapPrimitive.setAdaptingTessellator((SFProgramComponent)(SFPipeline.getModule("BasicTess")));
		} catch (SFException e) {
			e.printStackTrace();
		}
	}

	public static void prepare() {
		SFGL20Pipeline.setup();
		try {
			SFProgramComponentLoader.loadComponents(new File("data/primitive"),new SFPipelineBuilder());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SFPipelineModuleWrongException e) {
			e.printStackTrace();
		}
	}

	public SFPrimitive getPrimitive() {
		return primitive;
	}

	public SFPrimitive getTexturePrimitive() {
		return texturePrimitive;
	}

	public SFPrimitive getTrianglesPrimitive() {
		return trianglesPrimitive;
	}

	public void setTrianglesPrimitive(SFPrimitive trianglesPrimitive) {
		this.trianglesPrimitive = trianglesPrimitive;
	}

	public SFPrimitive getTrianglesTexturePrimitive() {
		return trianglesTexturePrimitive;
	}

	public void setTrianglesTexturePrimitive(SFPrimitive trianglesTexturePrimitive) {
		this.trianglesTexturePrimitive = trianglesTexturePrimitive;
	}

	public SFPrimitive getBumpMapPrimitive() {
		return bumpMapPrimitive;
	}

	public SFPrimitive getTrianglesbumpMapPrimitive() {
		return trianglesbumpMapPrimitive;
	}

	public SFPrimitive getTestPrimitive() {
		return testPrimitive;
	}

	public SFPrimitive getPrimitive3() {
		return primitive3;
	}
	
	
}
