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
package shadow.pipeline.openGL20;

import javax.media.opengl.GL2;

import shadow.system.SFInitiable;
import shadow.system.SFUpdatable;

//TOBESPLITTED

public abstract class SFGLSLSet implements SFInitiable,SFUpdatable{
	
	private static final boolean REPORT_SHADERS=true;
	
	private int frShader,vxShader,program = -1;
	private boolean initialized=false;
	
	public int getProgram() {
		return program;
	}

	public abstract String loadVertexShaderText();
	public abstract String loadFragmentShaderText();
	
	public SFGLSLSet() {
		super();
	}

	public void initFragmentShader(GL2 gl){

		frShader=gl.glCreateShaderObjectARB(GL2.GL_FRAGMENT_SHADER);
		
		if(frShader==0){
			System.out.println("Failed Crate Fr shader");
			System.exit(0);
		}else{
			//System.out.println("Shader Fr created");
		}
		
		updateFragmentShader(gl);
	}

	private void updateFragmentShader(GL2 gl) {
		String shaderText[]={loadFragmentShaderText()};
		
		int length[]={shaderText[0].length()};
		
		if(REPORT_SHADERS)
			System.out.println("shaderText[0] " + shaderText[0]);
		
		gl.glShaderSourceARB(frShader,1,shaderText,length,0);

		gl.glCompileShaderARB(frShader);
		
		int status[]=new int[1];
		
		gl.glGetObjectParameterivARB(frShader,GL2.GL_OBJECT_COMPILE_STATUS_ARB,status,0);
	
		if(status[0]==0){
			int len[]=new int[1];
			gl.glGetObjectParameterivARB(frShader, GL2.GL_OBJECT_COMPILE_STATUS_ARB,
		               len,0);

			byte[] b=new byte[3000];
			gl.glGetInfoLogARB(frShader,b.length,status,0,b,0);

			if(REPORT_SHADERS)
				System.out.println(new String(b));
		}else{
			//System.out.println("Shader is ok ");
		}
	}
	
	public void initVertexShader(GL2 gl){
		
		vxShader=gl.glCreateShaderObjectARB(GL2.GL_VERTEX_SHADER);
		
		if(vxShader==0){
			System.out.println("Failed Create shader");
		}
	   
		updateVertexShader(gl);
	}

	private void updateVertexShader(GL2 gl) {
		String shaderText[]={loadVertexShaderText()};

		int length[]={shaderText[0].length()};

		if(REPORT_SHADERS)
			System.out.println("shaderText[0] " + shaderText[0]);
		
		gl.glShaderSourceARB(vxShader,1,shaderText,length,0);
		
		gl.glCompileShaderARB(vxShader);
		
		int status[]=new int[1];
		
		gl.glGetObjectParameterivARB(vxShader,GL2.GL_OBJECT_COMPILE_STATUS_ARB,status,0);
	
		if(status[0]==0){
			if(REPORT_SHADERS)
				System.out.println("Shader has a problems");
			int len[]=new int[1];
			gl.glGetObjectParameterivARB(vxShader, GL2.GL_OBJECT_COMPILE_STATUS_ARB,
					len,0);

			byte[] b=new byte[20000];
			gl.glGetInfoLogARB(vxShader,b.length,status,0,b,0);

			if(REPORT_SHADERS)
				System.out.println(new String(b));
		}else{
			//System.out.println("Shader is ok ");
		}
	}
	
	public void initProgram(GL2 gl){
		
		program=gl.glCreateProgramObjectARB();
		
		if(program==0){
			System.out.println("Failed Create program");
			System.exit(0);
		}else{
			//System.out.println("Program created");
		}
		
		updateProgram(gl);
	}

	private void updateProgram(GL2 gl) {
		int[] status=new int[1];
		
		gl.glAttachObjectARB(program, vxShader);
		gl.glAttachObjectARB(program, frShader);
		
		gl.glLinkProgramARB(program);

		int[] status2=new int[1];
		gl.glGetObjectParameterivARB(program, GL2.GL_OBJECT_LINK_STATUS_ARB, status2,0);
		
		//System.out.println("Status "+status2[0]);
		if(status2[0]==0){
			if(REPORT_SHADERS)
				System.out.println("Something failed "+status2[0]);
			int len[]=new int[1];
			gl.glGetObjectParameterivARB(program, GL2.GL_OBJECT_COMPILE_STATUS_ARB,
					len,0);

			byte[] b=new byte[2000];
			gl.glGetInfoLogARB(program,b.length,status,0,b,0);

			if(REPORT_SHADERS)
				System.err.println("new String(b) " + new String(b));
		}

		gl.glValidateProgramARB(program);
		gl.glUseProgramObjectARB(program);
	}
	
	
	public void apply(){
		GL2 gl=SFGL2.getGL();
		gl.glUseProgramObjectARB(program);
	}
	public void unapply(){
		GL2 gl=SFGL2.getGL();
		gl.glUseProgramObjectARB(0);
	}
	
	@Override
	public void init() {
		GL2 gl=SFGL2.getGL();
		if(!initialized){
			initVertexShader(gl);
			initFragmentShader(gl);
			initProgram(gl);
			initialized=true;
		}
	}

	
	@Override
	public void destroy() {

		//TODO: SFGLSLSet destroy function should be deeply tested
		GL2 gl=SFGL2.getGL();
		if (this.program!=-1) {
			gl.glDeleteProgram(this.program);
			gl.glDeleteShader(this.frShader);
			gl.glDeleteShader(this.vxShader);
		}
		
	}
	
	@Override
	public void update() {
		GL2 gl=SFGL2.getGL();
		updateVertexShader(gl);
		updateFragmentShader(gl);
		updateProgram(gl);	
	}
	
}
