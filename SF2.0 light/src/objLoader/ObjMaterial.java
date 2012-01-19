package objLoader;


public class ObjMaterial {

	String name="";
	Vertex3f cAmb=new Vertex3f();
	Vertex3f cDiff=new Vertex3f();
	Vertex3f cSpec=new Vertex3f();
	float Ns;

//	public void apply(GL2 gl){
//
//		gl.glDisable(GL2.GL_COLOR_MATERIAL);
//		float []data={0,0,0,1};
//		cAmb.storeOn(data);
//		gl.glMaterialfv(GL2.GL_FRONT_AND_BACK,GL.GL_AMBIENT,data,0);
//		cDiff.storeOn(data);
//		gl.glMaterialfv(GL2.GL_FRONT_AND_BACK,GL.GL_DIFFUSE,data,0);
//		cSpec.storeOn(data);
//		gl.glMaterialfv(GL2.GL_FRONT_AND_BACK,GL.GL_SPECULAR,data,0);
//		gl.glMaterialf(GL2.GL_FRONT_AND_BACK,GL.GL_SHININESS,Ns);
//	}
}
