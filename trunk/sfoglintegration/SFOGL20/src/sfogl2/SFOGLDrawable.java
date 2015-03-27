package sfogl2;

import javax.media.opengl.GL2ES2;

public interface SFOGLDrawable {

	public void init(GL2ES2 gl);
	
	public void draw(GL2ES2 gl);
	
	public void dispose(GL2ES2 gl);
}
