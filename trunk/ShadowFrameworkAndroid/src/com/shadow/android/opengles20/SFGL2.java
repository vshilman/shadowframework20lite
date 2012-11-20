package com.shadow.android.opengles20;

import android.opengl.GLES20;


public class SFGL2{

	private static SFGL2 sfgl2=new SFGL2();
	private GLES20 gl;
	
	private SFGL2(){
		
	}
	
	public static GLES20 getGL() {
		return sfgl2.gl;
	}

	public static void setGl(GLES20 gl) {
		sfgl2.gl = gl;
	}	
}

