package sfogl.integration;

import javax.media.opengl.GL2;

public class SFGL2 {

	private static SFGL2 sfgl2=new SFGL2();
	private GL2 gl;
	
	private SFGL2(){
		
	}
	
	public static GL2 getGL() {
		return sfgl2.gl;
	}

	public static void setGl(GL2 gl) {
		sfgl2.gl = gl;
	}	
}
