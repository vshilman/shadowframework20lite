package shadow.pipeline.openGL20;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

import shadow.math.SFValuenf;
import shadow.pipeline.SFPipelineGraphics;
import shadow.pipeline.SFPipelineStructure;
import shadow.pipeline.SFPrimitiveArray;
import shadow.pipeline.SFPrimitiveIndices;
import shadow.pipeline.SFStructureArray;

public class SFGL20PipelineGraphics implements SFPipelineGraphics{

	
	private static SFGL20Program program;
	private static float modelX=0,modelY=0,modelZ=0;
	
	public static void setProgram(SFGL20Program program) {
		SFGL20PipelineGraphics.program=program;
	}

	@Override
	public void setModelPosition(float modelX, float modelY, float modelZ) {
		SFGL20PipelineGraphics.modelX=modelX;
		SFGL20PipelineGraphics.modelY=modelY;
		SFGL20PipelineGraphics.modelZ=modelZ;
	}
	
	@Override
	public void drawPrimitives(SFPrimitiveArray primitives,int first, int count) {
		
		program.setTransformData(modelX,modelY,modelZ);
		
		SFGL20PrimitiveArray prArray=(SFGL20PrimitiveArray)primitives;
		SFGL20ListData<SFValuenf> p=(SFGL20ListData<SFValuenf>)(primitives.getPrimitiveData(0));
		SFGL20ListData<SFValuenf> n=(SFGL20ListData<SFValuenf>)(primitives.getPrimitiveData(1));
		
		for(int i=first;i<count+first;i++){
			SFPrimitiveIndices indices=prArray.getValue(i);
			program.setPrimitiveData(indices,p,n);
			
			GL2 gl=SFGL2.getGL();
			
			int N=6;
			float step=.10f/N;
			for (int k=0; k < N; k++) {
				float v1=k*step;
				float v2=v1+step;
				gl.glBegin(GL.GL_TRIANGLE_STRIP);
					for (int j=0; j <= N-k; j++) {
						float u=j*step;
						gl.glVertex3f(u,v1,1-u-v1);
						gl.glVertex3f(u,v2,1-u-v2);
					}
				gl.glEnd();
			}
		}
		
	}

	
	@Override
	public void loadStructureData(SFStructureArray array, int indexOfData) {
		SFGL20StructureArray strArray=(SFGL20StructureArray)array;
		
		SFPipelineStructure structure=strArray.getPipelineStructure();
		
		program.setData(structure,strArray.getValue(indexOfData));
	}
	
	
}

