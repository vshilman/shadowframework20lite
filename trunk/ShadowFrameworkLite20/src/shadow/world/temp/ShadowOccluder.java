package shadow.world.temp;

import shadow.math.SFVertex3f;


public class ShadowOccluder {

	private int drawingColor=0xff666600;
	
	public SFVertex3f p=new SFVertex3f();
	public SFVertex3f D1=new SFVertex3f();
	public SFVertex3f D2=new SFVertex3f();
	
	public ShadowOccluder(float posx,float posy,float posz, float d1x,float d1y,float d1z,
			float d2x,float d2y,float d2z) {
		super();
		p.set3f(posx,posy,posz);
		D1.set3f(d1x,d1y,d1z);
		D2.set3f(d2x,d2y,d2z);	
	}
	
	public void draw(){
		
		//TO BE DONE
//		GLColors.color4f(gl, drawingColor);
//		
//		gl.glBegin(GL2.GL_TRIANGLE_STRIP);
//			gl.glVertex3f(p.x,p.y,p.z);
//			gl.glVertex3f(p.x+D1.x,p.y+D1.y,p.z+D1.z);
//			gl.glVertex3f(p.x+D2.x,p.y+D2.y,p.z+D2.z);
//			gl.glVertex3f(p.x+D2.x+D1.x,p.y+D2.y+D1.y,p.z+D2.z+D1.z);
//		gl.glEnd();
	}

	public int getDrawingColor() {
		return drawingColor;
	}

	public void setDrawingColor(int drawingColor) {
		this.drawingColor = drawingColor;
	}
	
	
}
