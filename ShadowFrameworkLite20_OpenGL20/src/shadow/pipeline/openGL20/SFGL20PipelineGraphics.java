package shadow.pipeline.openGL20;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

import shadow.pipeline.SFPipelineGraphics;
import shadow.pipeline.SFPipelineStructureInstance;
import shadow.pipeline.SFPrimitiveArray;
import shadow.pipeline.SFPrimitiveIndices;
import shadow.pipeline.SFProgram;
import shadow.pipeline.SFStructureArray;
import shadow.pipeline.parameters.SFPipelineRegister;

public class SFGL20PipelineGraphics implements SFPipelineGraphics {

	private static SFGL20GenericProgram program;
	private static float modelX=0, modelY=0, modelZ=0;
	private static float rotX=0, rotY=0, rotZ=0;

	public static void setProgram(SFGL20GenericProgram program) {
		SFGL20PipelineGraphics.program=program;
	}

	@Override
	public void translateModel(float modelX, float modelY, float modelZ) {
		SFGL20PipelineGraphics.modelX=modelX;
		SFGL20PipelineGraphics.modelY=modelY;
		SFGL20PipelineGraphics.modelZ=modelZ;
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

		SFGL20PrimitiveArray prArray=(SFGL20PrimitiveArray) primitives;

		SFPipelineRegister registers[]=primitives.getRegisters();

		Integer[][] uniforms=((SFGL20Program)program).getData().getAllPrimitiveUniforms(
				registers);

		for (int i=first; i < count + first; i++) {
			SFPrimitiveIndices indices=prArray.getValue(i);
			((SFGL20Program)program).setIndexedData(indices,prArray.getPrimitiveData(),uniforms,
					registers);

			GL2 gl=SFGL2.getGL();

			int N=6;
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
		}

	}

	@Override
	public void loadStructureData(SFStructureArray array, int indexOfData) {
		SFGL20StructureArray strArray=(SFGL20StructureArray) array;

		SFPipelineStructureInstance structure=strArray.getPipelineStructure();

		program.setData(structure,strArray.getValue(indexOfData));
	}

	
	
	@Override
	public void drawBaseQuad() {

		GL2 gl=SFGL2.getGL();
		
		gl.glBegin(GL.GL_TRIANGLE_STRIP);
			gl.glVertex2f(-1,-1);
			gl.glVertex2f(1,-1);
			gl.glVertex2f(-1,1);
			gl.glVertex2f(1,1);
		gl.glEnd();
	}
}
