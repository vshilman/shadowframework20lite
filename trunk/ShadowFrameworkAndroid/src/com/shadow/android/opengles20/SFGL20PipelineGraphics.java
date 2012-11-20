package com.shadow.android.opengles20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;

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
import android.opengl.GLES20;

public class SFGL20PipelineGraphics implements SFPipelineGraphics, SFInitiable {

	private static final int N=4;
	private static final int LINES_WIDTH=2;
	
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
	
	//private float[] static_base_triangle;
	private FloatBuffer static_base_triangle;
	private FloatBuffer static_base_quad;
	
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
	public float[] getProjection() {
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
		
		SFGL20Program program=(SFGL20Program)(this.program);
		
		program.load();
		
		updateTransforms();
		
		SFGL20PrimitiveArray prArray=(SFGL20PrimitiveArray) primitives;

		SFPrimitive primitive=primitives.getPrimitive();
		
		for (int i=first; i < count + first; i++) {

			SFPrimitiveIndices indices=prArray.getValue(i);
			
			((SFGL20Program)program).setIndexedData(indices,prArray.getPrimitiveData(),primitive);

			int location=program.getAttributeLocation("vertex");

			GLES20.glEnableVertexAttribArray(location);

		    GLES20.glVertexAttribPointer(location,2,GLES20.GL_FLOAT,false,0,static_base_triangle);

		    GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0,  12);
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

			//NOTHING TO DO FOR NOW
			
//			GL2 gl=SFGLES20.getGL();
//			GLES20.glCallList(list);
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
		program.setTextureData(module,indexOfTexture,texture);
	}
	
	@Override
	public void setupProjection(float[] projection) {
		SFGL20PipelineGraphics.projection=projection;
	}

	@Override
	public void drawBaseQuad() {

		SFGL20ImageProgram program=(SFGL20ImageProgram)(SFGL20PipelineGraphics.program);
		
		program.load();
		
		int location=program.getAttributeLocation("vertex");

		GLES20.glEnableVertexAttribArray(location);
		
	    GLES20.glVertexAttribPointer(location,2,GLES20.GL_FLOAT,false,0,static_base_quad);

	    GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0,  4);
	}
	
	@Override
	public void init() {
		
		float[] static_base_triangle=new float[24];
	    static_base_triangle[0]=0;
	    static_base_triangle[1]=0;
	    static_base_triangle[2]=0.5f;
	    static_base_triangle[3]=0;
	    static_base_triangle[4]=0;
	    static_base_triangle[5]=0.5f;

	    static_base_triangle[6]=0.5f;
	    static_base_triangle[7]=0;
	    static_base_triangle[8]=0;
	    static_base_triangle[9]=0.5f;
	    static_base_triangle[10]=0.5f;
	    static_base_triangle[11]=0.5f;

	    static_base_triangle[12]=0.5f;
	    static_base_triangle[13]=0;
	    static_base_triangle[14]=1.0f;
	    static_base_triangle[15]=0;
	    static_base_triangle[16]=0.5f;
	    static_base_triangle[17]=0.5f;

	    static_base_triangle[18]=0;
	    static_base_triangle[19]=0.5f;
	    static_base_triangle[20]=0.5f;
	    static_base_triangle[21]=0.5f;
	    static_base_triangle[22]=0;
	    static_base_triangle[23]=1.0f;

	    ByteBuffer vbb = ByteBuffer.allocateDirect(
	            // (# of coordinate values * 4 bytes per float)
	    		static_base_triangle.length * 4); 
	    vbb.order(ByteOrder.nativeOrder());// use the device hardware's native byte order
	    this.static_base_triangle = vbb.asFloatBuffer();  // create a floating point buffer from the ByteBuffer
	    this.static_base_triangle.put(static_base_triangle);    // add the coordinates to the FloatBuffer
	    this.static_base_triangle.position(0);            // set the buffer to read the first coordinate
	
		float[] static_base_quad=new float[8];
		static_base_quad[0]=-1;
		static_base_quad[1]=-1;
	    static_base_quad[2]=1;
	    static_base_quad[3]=-1;

	    static_base_quad[4]=-1;
	    static_base_quad[5]=1;
	    static_base_quad[6]=1;
	    static_base_quad[7]=1;

	    ByteBuffer vbq = ByteBuffer.allocateDirect(
	            // (# of coordinate values * 4 bytes per float)
	    		static_base_quad.length * 4); 
	    vbq.order(ByteOrder.nativeOrder());// use the device hardware's native byte order
	    this.static_base_quad = vbq.asFloatBuffer();  // create a floating point buffer from the ByteBuffer
	    this.static_base_quad.put(static_base_quad);    // add the coordinates to the FloatBuffer
	    this.static_base_quad.position(0);            // set the buffer to read the first coordinate
	    
//		baseTriangleList=GLES20.glGenLists(1);
//		baseQuadsList=GLES20.glGenLists(1);
//		baseQuadList=GLES20.glGenLists(1);
//		baseLineList=GLES20.glGenLists(1);
//		
//		int N=SFGL20PipelineGraphics.N;
//		float step=1.0f / N;
//		
//		GLES20.glNewList(baseTriangleList, GLES20.GL_COMPILE);
//			for (int k=0; k < N; k++) {
//				float v1=k * step;
//				float v2=v1 + step;
//				GLES20.glBegin(GLES20.GL_TRIANGLE_STRIP);
//					for (int j=0; j < N - k; j++) {
//						float u=j * step;
//						GLES20.glVertex3f(u,v1,1 - u - v1);
//						GLES20.glVertex3f(u,v2,1 - u - v2);
//					}
//					float u=(N - k) * step;
//					GLES20.glVertex3f(u,v1,1 - u - v1);
//				GLES20.glEnd();
//			}
//		GLES20.glEndList();
//
//		GLES20.glNewList(baseQuadsList, GLES20.GL_COMPILE);
//			for (int k=0; k < N; k++) {
//				
//				float v1=k * step;
//				float v2=v1 + step;
//				GLES20.glBegin(GLES20.GL_TRIANGLE_STRIP);
//					for (int j=0; j <= N ; j++) {
//						float u=j * step;
//						GLES20.glVertex3f(u,v1,1 - u - v1);
//						GLES20.glVertex3f(u,v2,1 - u - v2);
//					}
//				GLES20.glEnd();
//			}
//		GLES20.glEndList();
//		
//		GLES20.glNewList(baseQuadList, GLES20.GL_COMPILE);
//			GLES20.glBegin(GLES20.GL_TRIANGLE_STRIP);
//				GLES20.glVertex2f(-1,-1);
//				GLES20.glVertex2f(1,-1);
//				GLES20.glVertex2f(-1,1);
//				GLES20.glVertex2f(1,1);
//			GLES20.glEnd();
//		GLES20.glEndList();
//
//
//		N=N*N;
//		step=1.0f / N;
//		GLES20.glNewList(baseLineList, GLES20.GL_COMPILE);
//			GLES20.glLineWidth(LINES_WIDTH);
//			GLES20.glBegin(GLES20.GL_LINE_STRIP);
//			for (int k=0; k <= N; k++) {
//				float t=k * step;
//				GLES20.glVertex3f(t,0,0);
//			}
//			GLES20.glEnd();
//			GLES20.glLineWidth(1);
//		GLES20.glEndList();
//		
	}
	
	@Override
	public void destroy() {
		
		//TODO SFGL20PipelineGraphics destroy should be tested
		
//		GLES20.glDeleteLists(baseQuadsList, 1);
//		GLES20.glDeleteLists(baseQuadList, 1);
//		GLES20.glDeleteLists(baseTriangleList, 1);
//		GLES20.glDeleteLists(baseLineList, 1);
	}
	

	private static int getStencilFunc(StencilFunction function){
		switch (function) {
			case ALWAYS: return GLES20.GL_ALWAYS;
			case EQUAL: return GLES20.GL_EQUAL;
			case GEQUAL: return GLES20.GL_GEQUAL;
			case GREATER: return GLES20.GL_GREATER;
			case LEQUAL: return GLES20.GL_LEQUAL;
			case LESS: return GLES20.GL_LESS;
			case NEVER: return GLES20.GL_NEVER;
			case NOTEQUAL: return GLES20.GL_NOTEQUAL;
		}
		return GLES20.GL_ALWAYS;
	}
	
	private static int getStencilOp(StencilOperation operation){
		switch (operation) {
			case DECR: return GLES20.GL_DECR;
			case DECR_WRAP: return GLES20.GL_DECR_WRAP;
			case INCR: return GLES20.GL_INCR;
			case INCR_WRAP: return GLES20.GL_INCR_WRAP;
			case INVERT: return GLES20.GL_INVERT;
			case KEEP: return GLES20.GL_KEEP;
			case REPLACE: return GLES20.GL_REPLACE;
			case ZERO: return GLES20.GL_ZERO;
		}
		return GLES20.GL_ALWAYS;
	}
	
	@Override
	public void setPipelineState(SFPipelineRenderingState state) {
		if(state.isDepthTest()){
			GLES20.glEnable(GLES20.GL_DEPTH_TEST);
		}else{
			GLES20.glDisable(GLES20.GL_DEPTH_TEST);
		}
		if(state.isStencilTest()){
			GLES20.glEnable(GLES20.GL_STENCIL_TEST);
			GLES20.glStencilFunc(getStencilFunc(state.getFunction()), state.getStencilValue(), state.getStencilMask());
			GLES20.glStencilOp(getStencilOp(state.getStencilFail()), getStencilOp(state.getDepthFail()), getStencilOp(state.getDepthPass()));
		}else{
			GLES20.glDisable(GLES20.GL_STENCIL_TEST);
		}
	}
	
	
	@Override
	public void executeAccumulationOperation(AccumulatorOperation operation,
			float value) {
		
//Nothing to do, accumulation is not available in OpenGLES 20!!!
//		int accumOperation=-1;
//		
//		switch (operation) {
//			case ACCUM: accumOperation=GLES20.GL_ACCUM;
//			case ADD: accumOperation=GLES20.GL_ADD;
//			case LOAD: accumOperation=GLES20.GL_LOAD;
//			case MULT: accumOperation=GLES20.GL_MULT;
//			case RETURN: accumOperation=GLES20.GL_RETURN;
//		}
//		
//		GLES20.glA
//		
//		GLES20.glAccum(accumOperation, value);
	
	}
}
