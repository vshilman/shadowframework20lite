package shadow.pipeline.openGL20;

import java.util.ArrayList;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

import shadow.image.SFPipelineTexture;
import shadow.math.SFValuenf;
import shadow.math.SFVertex2f;
import shadow.pipeline.SFGridModel;
import shadow.pipeline.SFPipelineGraphics;
import shadow.pipeline.SFPipelineRenderingState;
import shadow.pipeline.SFPipelineRenderingState.AccumulatorOperation;
import shadow.pipeline.SFPipelineRenderingState.StencilFunction;
import shadow.pipeline.SFPipelineRenderingState.StencilOperation;
import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFPrimitiveArray;
import shadow.pipeline.SFPrimitiveIndices;
import shadow.pipeline.SFStructureArray;
import shadow.pipeline.java.SFGL20GenericProgram;
import shadow.pipeline.java.SFGL20PrimitiveArray;
import shadow.pipeline.java.SFGL20StructureArray;
import shadow.system.SFArray;
import shadow.system.SFInitiable;
import shadow.system.SFInitiator;

public class SFGL20PipelineGraphics implements SFPipelineGraphics, SFInitiable {

	private static final int N=20;
	private static final int LINES_WIDTH=1;
	
	private ArrayList<SFVertex2f> notExactlyCompiledPrimitives=new ArrayList<SFVertex2f>();
	private ArrayList<SFVertex2f> notExactlyCompiledPointsCloud=new ArrayList<SFVertex2f>();
	
	private static SFGL20GenericProgram program;
	private static float projection[]=null;
	
	private static float[] transform;
	
	private static int baseQuadList=-1;
	//TODO : should have all the tessellations...
	private static int baseTriangleList=-1;
	private static int baseLineList=-1;
	private static int baseQuadsList=-1;
	
	public SFGL20PipelineGraphics(){
		SFInitiator.addInitiable(this);
	}
	
	public static void setProgram(SFGL20GenericProgram program) {
		SFGL20PipelineGraphics.program=program;
	}

	@Override
	public void setupTransform(float[] transform) {
		staticSetupTransform(transform);
	}

	@Override
	public float[] getTransform() {
		return SFGL20PipelineGraphics.transform;
	}
	
	@Override
	public float[] getpProjection() {
		return SFGL20PipelineGraphics.projection;
	}
	
	public static void staticSetupTransform(float[] transform) {
		SFGL20PipelineGraphics.transform=transform;
	}
	
	@Override
	public int compilePointsCloud(SFPrimitiveArray array, int firstElement, int lastElement) {
		notExactlyCompiledPointsCloud.add(new SFVertex2f(firstElement, lastElement));
		return notExactlyCompiledPointsCloud.size()-1;
	}
	
	@Override
	public int compilePrimitiveArray(SFPrimitiveArray array, int firstElement, int lastElement) {
		notExactlyCompiledPrimitives.add(new SFVertex2f(firstElement, lastElement));
		return notExactlyCompiledPrimitives.size()-1;
	}
	
	@Override
	public void drawCompiledPointsCloud(SFPrimitiveArray primitives, int compiledGeometry) {
		SFVertex2f v=notExactlyCompiledPointsCloud.get(compiledGeometry);
		drawPointsCloud(primitives, (int)v.getX(), (int)v.getY());
	}

	@Override
	public void drawCompiledPrimitives(SFPrimitiveArray primitives, int compiledGeometry) {
		SFVertex2f v=notExactlyCompiledPrimitives.get(compiledGeometry);
		drawPrimitives(primitives, (int)v.getX(), (int)v.getY());
	}
	
	@Override
	public void updateCompiledPointsCloud(SFPrimitiveArray array, int compiled) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void updateCompiledPrimitive(SFPrimitiveArray array, int compiled) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void drawPrimitives(SFPrimitiveArray primitives, int first, int count) {

		//long time=System.nanoTime();
		
		updateTransforms();
		
		SFGL20PrimitiveArray prArray=(SFGL20PrimitiveArray) primitives;

		SFPrimitive primitive=primitives.getPrimitive();
		
		int list = getList(primitive);
		
		for (int i=first; i < count + first; i++) {

			SFPrimitiveIndices indices=prArray.getValue(i);
			
			((SFGL20Program)program).setIndexedData(indices,prArray.getPrimitiveData(),primitive);

			GL2 gl=SFGL2.getGL();
			gl.glCallList(list);
		}
		
		//long time02=System.nanoTime();
		
		//System.err.println("Time "+((time02-time)*0.001*0.001)+" ms");
	}

	private int getList(SFPrimitive primitive) {
		int list=baseTriangleList;
		if(primitive.getGridModel()==SFGridModel.Quad)
			list=baseQuadsList;
		else if(primitive.getGridModel()==SFGridModel.Line)
			list=baseLineList;
		return list;
	}

	private void updateTransforms() {
		((SFGL20Program)program).setTransformData(transform);

		if(projection!=null)
			((SFGL20Program)program).setupProjection(projection);
	}
	
	@Override
	public void drawPointsCloud(SFPrimitiveArray primitives, int first,
			int count) {
		updateTransforms();
		
		SFGL20PrimitiveArray prArray=(SFGL20PrimitiveArray) primitives;

		SFPrimitive primitive=primitives.getPrimitive();
		
		int list = getList(primitive);
		
		SFArray<SFValuenf> positions=primitives.getPrimitiveData(0);
		
		SFValuenf value=positions.generateSample();
		
		for (int i=first; i < count + first; i++) {

			positions.getElement(i, value);
			
			((SFGL20Program)program).sendVertex(value);

			GL2 gl=SFGL2.getGL();
			gl.glCallList(list);
		}
	}

	@Override
	public void loadStructureData(Module module, SFStructureArray array, int inProgramIndex,
			int indexOfData) {
		SFGL20StructureArray strArray=(SFGL20StructureArray) array;
		
		program.setData(module,inProgramIndex,strArray.getValue(indexOfData));
	}
	
	@Override
	public void loadTexture(Module module, SFPipelineTexture texture, int indexOfTexture) {
		program.setData(module,indexOfTexture,texture);
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
		baseQuadsList=gl.glGenLists(1);
		baseQuadList=gl.glGenLists(1);
		baseLineList=gl.glGenLists(1);
		
		int N=SFGL20PipelineGraphics.N;
		float step=1.0f / N;
		
		gl.glNewList(baseTriangleList, GL2.GL_COMPILE);
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

		gl.glNewList(baseQuadsList, GL2.GL_COMPILE);
			for (int k=0; k < N; k++) {
				
				float v1=k * step;
				float v2=v1 + step;
				gl.glBegin(GL.GL_TRIANGLE_STRIP);
					for (int j=0; j <= N ; j++) {
						float u=j * step;
						gl.glVertex3f(u,v1,1 - u - v1);
						gl.glVertex3f(u,v2,1 - u - v2);
					}
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


		N=N*N;
		step=1.0f / N;
		gl.glNewList(baseLineList, GL2.GL_COMPILE);
			gl.glLineWidth(LINES_WIDTH);
			gl.glBegin(GL.GL_LINE_STRIP);
			for (int k=0; k <= N; k++) {
				float t=k * step;
				gl.glVertex3f(t,0,0);
			}
			gl.glEnd();
			gl.glLineWidth(1);
		gl.glEndList();
		
	}
	
	@Override
	public void destroy() {
		
		//TODO SFGL20PipelineGraphics destroy should be tested
		GL2 gl=SFGL2.getGL();
		gl.glDeleteLists(baseQuadsList, 1);
		gl.glDeleteLists(baseQuadList, 1);
		gl.glDeleteLists(baseTriangleList, 1);
		gl.glDeleteLists(baseLineList, 1);
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
