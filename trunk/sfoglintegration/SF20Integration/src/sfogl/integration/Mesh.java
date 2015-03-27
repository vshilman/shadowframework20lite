package sfogl.integration;

import javax.media.opengl.GL2ES2;

import sfogl2.SFOGLBufferObject;
import sfogl2.SFOGLShader;
import shadow.system.SFInitiable;


public class Mesh implements SFInitiable{

    private SFOGLBufferObject vertices=new SFOGLBufferObject();
    private SFOGLBufferObject normals=new SFOGLBufferObject();
    private SFOGLBufferObject indices=new SFOGLBufferObject();
    private SFOGLBufferObject txCoords=new SFOGLBufferObject();

    private ArrayObject arrayObject;
    //private int size;
    
	public Mesh() {
		super();
	}

    public boolean isTxCoord(){
    	return arrayObject.isTxCoord();
    }
    
	public Mesh(ArrayObject arrayObject) {
		super();
        this.arrayObject=arrayObject;
	}

    public void draw(SFOGLShader shader) {

        //Log.e("Mesh","Draw "+vertices.getBufferObject()+" "+normals.getBufferObject()+" ");
        shader.bindAttributef(SFGL2.getGL(),ShadingProgram.VERTICES_INDEX, vertices.getVertexBufferObject(), 3);/*position*/
        //Log.e("Mesh","Get Error A "+GLES20.glGetError());
        shader.bindAttributef(SFGL2.getGL(),ShadingProgram.NORMALS_INDEX, normals.getVertexBufferObject(), 3);/*normal*/
        //Log.e("Mesh","Get Error B "+GLES20.glGetError());
        if(isTxCoord())
            shader.bindAttributef(SFGL2.getGL(),ShadingProgram.TXCOORD_INDEX, txCoords.getVertexBufferObject(), 3);/*normal*/
        SFGL2.getGL().glBindBuffer(GL2ES2.GL_ELEMENT_ARRAY_BUFFER, indices.getVertexBufferObject());
        //Log.e("Mesh","Get Error C "+GLES20.glGetError());

        SFGL2.getGL().glDrawElements(GL2ES2.GL_TRIANGLES,arrayObject.getIndicesBuffer().length,GL2ES2.GL_UNSIGNED_SHORT,0);

        //short[] indices={0,1,2};



        //byteData.rewind();
        //GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0,3);//so you
        //Log.e("Mesh","Get Error D "+GLES20.glGetError());
    }

	@Override
	public void init() {

        //Log.e("Mesh","Initialized");
        indices.loadData(SFGL2.getGL(),arrayObject.getIndicesBuffer());

        //Log.e("Mesh","Get Error "+GLES20.glGetError());
		vertices.loadData(SFGL2.getGL(),arrayObject.getVerticesBuffer());
        normals.loadData(SFGL2.getGL(),arrayObject.getNormalsBuffer());
        if(isTxCoord())
		     txCoords.loadData(SFGL2.getGL(),arrayObject.getTxCoordsBuffer());

	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public SFOGLBufferObject getVertices() {
		return vertices;
	}

	public void setVertices(SFOGLBufferObject vertices) {
		this.vertices = vertices;
	}

	public SFOGLBufferObject getNormals() {
		return normals;
	}

	public void setNormals(SFOGLBufferObject normals) {
		this.normals = normals;
	}

	
}
