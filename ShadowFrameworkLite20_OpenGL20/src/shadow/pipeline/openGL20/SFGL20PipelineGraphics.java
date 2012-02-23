package shadow.pipeline.openGL20;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

import shadow.math.SFVertex3f;
import shadow.pipeline.SFPipelineGraphics;
import shadow.pipeline.SFPipelineRenderingState;
import shadow.pipeline.SFPipelineRenderingState.StencilFunction;
import shadow.pipeline.SFPipelineRenderingState.StencilOperation;
import shadow.pipeline.SFPrimitiveArray;
import shadow.pipeline.SFPrimitiveIndices;
import shadow.pipeline.SFStructureArray;
import shadow.pipeline.SFPipelineRenderingState.AccumulatorOperation;
import shadow.pipeline.parameters.SFPipelineRegister;
import shadow.system.SFInitiable;
import shadow.system.SFInitiator;

public class SFGL20PipelineGraphics implements SFPipelineGraphics, SFInitiable {

	private static SFGL20GenericProgram program;
	private static float modelX=0, modelY=0, modelZ=0;
	private static float rotX=0, rotY=0, rotZ=0;
	private static float projection[]=null;
	
	private static int baseQuadList=-1;
	//TODO : should have all the tessellations...
	private static int baseTriangleList=-1;
	
	public SFGL20PipelineGraphics(){
		SFInitiator.addInitiable(this);
	}
	
	public static void setProgram(SFGL20GenericProgram program) {
		SFGL20PipelineGraphics.program=program;
	}

	@Override
	public void translateModel(SFVertex3f modelPosition) {
		SFGL20PipelineGraphics.modelX=modelPosition.getX();
		SFGL20PipelineGraphics.modelY=modelPosition.getY();
		SFGL20PipelineGraphics.modelZ=modelPosition.getZ();
	}

	@Override
	public void rotateModel(float rotX, float rotY, float rotZ) {
		SFGL20PipelineGraphics.rotX=rotX;
		SFGL20PipelineGraphics.rotY=rotY;
		SFGL20PipelineGraphics.rotZ=rotZ;
	}

	@Override
	public void drawPrimitives(SFPrimitiveArray primitives, int first, int count) {

		((SFGL20Program)program).setTransformData(modelX,modelY,modelZ,rotX,rotY,rotZ);

		if(projection!=null)
			((SFGL20Program)program).setupProjection(projection);
		
		SFGL20PrimitiveArray prArray=(SFGL20PrimitiveArray) primitives;

		SFPipelineRegister registers[]=primitives.getRegisters();

		Integer[][] uniforms=((SFGL20Program)program).getData().getAllPrimitiveUniforms(
				registers);

		for (int i=first; i < count + first; i++) {
			SFPrimitiveIndices indices=prArray.getValue(i);
			((SFGL20Program)program).setIndexedData(indices,prArray.getPrimitiveData(),uniforms,
					registers);

			GL2 gl=SFGL2.getGL();

			gl.glCallList(baseTriangleList);
		}
	}

	@Override
	public void loadStructureData(SFStructureArray array, int indexOfData) {
		SFGL20StructureArray strArray=(SFGL20StructureArray) array;

		program.setData(strArray.getPipelineStructure(),strArray.getValue(indexOfData));
	}
	
	@Override
	public void setupProjection(float[] projection) {
		SFGL20PipelineGraphics.projection=projection;
	}

	@Override
	public void drawBaseQuad() {

		GL2 gl=SFGL2.getGL();
			
		gl.glCallList(baseQuadList);
	}
	
	@Override
	public void init() {

		GL2 gl=SFGL2.getGL();
		
		baseTriangleList=gl.glGenLists(1);
		baseQuadList=gl.glGenLists(1);
		
		gl.glNewList(baseTriangleList, GL2.GL_COMPILE);
			int N=20;
			float step=1.0f / N;
			for (int k=0; k < N; k++) {
				float v1=k * step;
				float v2=v1 + step;
				gl.glBegin(GL.GL_TRIANGLE_STRIP);
					for (int j=0; j < N - k; j++) {
						float u=j * step;
						gl.glVertex3f(u,v1,1 - u - v1);
						gl.glVertex3f(u,v2,1 - u - v2);
					}
					float u=(N - k) * step;
					gl.glVertex3f(u,v1,1 - u - v1);
				gl.glEnd();
			}
		gl.glEndList();
		
		gl.glNewList(baseQuadList, GL2.GL_COMPILE);
			gl.glBegin(GL.GL_TRIANGLE_STRIP);
				gl.glVertex2f(-1,-1);
				gl.glVertex2f(1,-1);
				gl.glVertex2f(-1,1);
				gl.glVertex2f(1,1);
			gl.glEnd();
		gl.glEndList();
	}
	
	private static int getStencilFunc(StencilFunction function){
		switch (function) {
			case ALWAYS: return GL2.GL_ALWAYS;
			case EQUAL: return GL2.GL_EQUAL;
			case GEQUAL: return GL2.GL_GEQUAL;
			case GREATER: return GL2.GL_GREATER;
			case LEQUAL: return GL2.GL_LEQUAL;
			case LESS: return GL2.GL_LESS;
			case NEVER: return GL2.GL_NEVER;
			case NOTEQUAL: return GL2.GL_NOTEQUAL;
		}
		return GL2.GL_ALWAYS;
	}
	
	private static int getStencilOp(StencilOperation operation){
		switch (operation) {
			case DECR: return GL2.GL_DECR;
			case DECR_WRAP: return GL2.GL_DECR_WRAP;
			case INCR: return GL2.GL_INCR;
			case INCR_WRAP: return GL2.GL_INCR_WRAP;
			case INVERT: return GL2.GL_INVERT;
			case KEEP: return GL2.GL_KEEP;
			case REPLACE: return GL2.GL_REPLACE;
			case ZERO: return GL2.GL_ZERO;
		}
		return GL2.GL_ALWAYS;
	}
	
	@Override
	public void setPipelineState(SFPipelineRenderingState state) {
		GL2 gl=SFGL2.getGL();
		if(state.isDepthTest()){
			gl.glEnable(GL2.GL_DEPTH_TEST);
		}else{
			gl.glDisable(GL2.GL_DEPTH_TEST);
		}
		if(state.isStencilTest()){
			gl.glEnable(GL2.GL_STENCIL_TEST);
			gl.glStencilFunc(getStencilFunc(state.getFunction()), state.getStencilValue(), state.getStencilMask());
			gl.glStencilOp(getStencilOp(state.getStencilFail()), getStencilOp(state.getDepthFail()), getStencilOp(state.getDepthPass()));
		}else{
			gl.glDisable(GL2.GL_STENCIL_TEST);
		}
	}
	
	
	@Override
	public void executeAccumulationOperation(AccumulatorOperation operation,
			float value) {

		GL2 gl=SFGL2.getGL();
	
		int accumOperation=-1;
		
		switch (operation) {
			case ACCUM: accumOperation=GL2.GL_ACCUM;
			case ADD: accumOperation=GL2.GL_ADD;
			case LOAD: accumOperation=GL2.GL_LOAD;
			case MULT: accumOperation=GL2.GL_MULT;
			case RETURN: accumOperation=GL2.GL_RETURN;
		}
		
		gl.glAccum(accumOperation, value);
	}
}
