/*
	The Shadow Framework 1.0 Lead Version - a complete framework for Real Time Graphics based on OpenGL 2.0
    Copyright (C) 2010  Alessandro Martinelli  <alessandro.martinelli@unipv.it>

    This file is part of The Shadow Framework.

    The Shadow Framework is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    The Shadow Framework is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Shadow Framework.  If not, see <http://www.gnu.org/licenses/>.
*/
package com.shadow.android.opengles20;

import shadow.system.SFInitiable;
import shadow.system.SFUpdatable;
import android.opengl.GLES20;
import android.util.Log;

//TOBESPLITTED

public abstract class SFGLSLSet implements SFInitiable,SFUpdatable{
	
	private static final boolean REPORT_SHADERS=true;
	
	private int frShader,vxShader,program;
	private boolean initialized=false;
	
	public int getProgram() {
		return program;
	}

	public abstract String loadVertexShaderText();
	public abstract String loadFragmentShaderText();
	
	public SFGLSLSet() {
		super();
	}

	public void initFragmentShader(){

		frShader=GLES20.glCreateShader(GLES20.GL_FRAGMENT_SHADER);
		//frShader=gl.glCreateShaderObject(GLES20.GL_FRAGMENT_SHADER);
		
		if(frShader==0){
			System.out.println("Failed Crate Fr shader");
			System.exit(0);
		}else{
			//System.out.println("Shader Fr created");
		}
		
		updateFragmentShader();
	}

	private void updateFragmentShader() {
		String shaderText[]={loadFragmentShaderText()};
		
		if(REPORT_SHADERS)
			System.out.println("shaderText[0] " + shaderText[0]);
		
		GLES20.glShaderSource(frShader, shaderText[0]);
		GLES20.glCompileShader(frShader);
		
		//Don't care status; errors will be reported to Logcat
		
//		int status[]=new int[1];
//		gl.glGetObjectParameterivARB(frShader,GL2.GL_OBJECT_COMPILE_STATUS_ARB,status,0);
//	
//		if(status[0]==0){
//			int len[]=new int[1];
//			gl.glGetObjectParameterivARB(frShader, GL2.GL_OBJECT_COMPILE_STATUS_ARB,
//		               len,0);
//
//			byte[] b=new byte[3000];
//			gl.glGetInfoLogARB(frShader,b.length,status,0,b,0);
//
//			if(REPORT_SHADERS)
//				System.out.println(new String(b));
//		}else{
//			//System.out.println("Shader is ok ");
//		}
	}
	
	public void initVertexShader(){

		vxShader=GLES20.glCreateShader(GLES20.GL_VERTEX_SHADER);
		
		if(vxShader==0){
			System.out.println("Failed Create shader");
		}
	   
		updateVertexShader();
	}

	private void updateVertexShader() {
		String shaderText[]={loadVertexShaderText()};

		int length[]={shaderText[0].length()};

		if(REPORT_SHADERS)
			System.out.println("shaderText[0] " + shaderText[0]);

		GLES20.glShaderSource(vxShader, shaderText[0]);
		GLES20.glCompileShader(vxShader);
		
		//Don't care status; errors will be reported to Logcat
		
//		int status[]=new int[1];
//		
//		gl.glGetObjectParameterivARB(vxShader,GL2.GL_OBJECT_COMPILE_STATUS_ARB,status,0);
//	
//		if(status[0]==0){
//			if(REPORT_SHADERS)
//				System.out.println("Shader has a problems");
//			int len[]=new int[1];
//			gl.glGetObjectParameterivARB(vxShader, GL2.GL_OBJECT_COMPILE_STATUS_ARB,
//					len,0);
//
//			byte[] b=new byte[20000];
//			gl.glGetInfoLogARB(vxShader,b.length,status,0,b,0);
//
//			if(REPORT_SHADERS)
//				System.out.println(new String(b));
//		}else{
//			//System.out.println("Shader is ok ");
//		}
	}
	
	public void initProgram(){
		
		program=GLES20.glCreateProgram();//gl.glCreateProgramObjectARB();
		
		if(program==0){
			System.out.println("Failed Create program");
			System.exit(0);
		}else{
			//System.out.println("Program created");
		}
		
		updateProgram();
	}

	private void updateProgram() {
		int[] status=new int[1];
		
		GLES20.glAttachShader(program, vxShader);
		GLES20.glAttachShader(program, frShader);
		
		GLES20.glLinkProgram(program);

//		int[] status2=new int[1];
//		gl.glGetObjectParameterivARB(program, GL2.GL_OBJECT_LINK_STATUS_ARB, status2,0);
//		
//		//System.out.println("Status "+status2[0]);
//		if(status2[0]==0){
//			if(REPORT_SHADERS)
//				System.out.println("Something failed "+status2[0]);
//			int len[]=new int[1];
//			gl.glGetObjectParameterivARB(program, GL2.GL_OBJECT_COMPILE_STATUS_ARB,
//					len,0);
//
//			byte[] b=new byte[2000];
//			gl.glGetInfoLogARB(program,b.length,status,0,b,0);
//
//			if(REPORT_SHADERS)
//				System.err.println("new String(b) " + new String(b));
//		}

		GLES20.glValidateProgram(program);
		GLES20.glUseProgram(program);
	}
	
	public int getAttributeLocation(String name){
		return GLES20.glGetAttribLocation(this.program, name);
	}
	
	public void apply(){
		GLES20.glUseProgram(program);
	}
	public void unapply(){
		GLES20.glUseProgram(0);
	}
	
	@Override
	public void init() {
		if(!initialized){
			initialized=true;
			initVertexShader();
			initFragmentShader();
			initProgram();
		}
	}

	
	@Override
	public void destroy() {

		//TODO: SFGLSLSet destroy function should be deeply tested
		
		GLES20.glDeleteProgram(this.program);
		GLES20.glDeleteShader(this.frShader);
		GLES20.glDeleteShader(this.vxShader);
		
	}
	
	@Override
	public void update() {
		updateVertexShader();
		updateFragmentShader();
		updateProgram();	
	}
	
}
