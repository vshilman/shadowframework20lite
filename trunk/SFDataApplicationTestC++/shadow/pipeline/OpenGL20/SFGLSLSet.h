//
//  SFGLSLSet.h
//  
//
//  Created by Alessandro Martinelli on 22/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#ifndef SFGLSLSet__
#define SFGLSLSet__

#include "SFGraphicsHeaders.h"

//#include <windows.h>
//#include <GL/glew.h>
//#include <GL/glut.h>

#include "../../system/SFInitiable.h"
#include "../../system/SFUpdatable.h"

#include <string>

using namespace std;


namespace sf{
class SFGLSLSet : public SFInitiable,SFUpdatable{
	
private:
	static const bool REPORT_SHADERS=true;
	
	GLuint frShader,vxShader,program;
	bool initialized;
    
public:
	
	GLuint getProgram() {
		return program;
	}
    
	virtual string loadVertexShaderText()=0;
	virtual string loadFragmentShaderText()=0;
	
	SFGLSLSet() {
		initialized=false;
		frShader=0;
		vxShader=0;
		program=0;
	}
    
	void initFragmentShader(){
        
		frShader=glCreateShader(GL_FRAGMENT_SHADER);
		
        
        if(frShader==0){
            //NSLog("Failed to create Fragment Shader ");
        }
		//if(frShader==0){
		//	System.out.println("Failed Crate Fr shader");
		//	System.exit(0);
		//}else{
		//	//System.out.println("Shader Fr created");
		//}
		
		updateFragmentShader();
	}
    
	void updateFragmentShader() {
		
        string shaderText=loadFragmentShaderText();
		
		//int length[]={shaderText[0].length()};
		
		//if(REPORT_SHADERS)
		//	System.out.println("shaderText[0] " + shaderText[0]);
		
        /*void glShaderSource(	GLuint  	shader,
                            GLsizei  	count,
                            const GLchar ** 	string,
                            const GLint * 	length);*/

        GLchar* text=(char*)shaderText.c_str();
        GLint length=1;
        
        //glShaderSource (GLuint shader, GLsizei count, const GLchar** string,
        //                const GLint* length)  __OSX_AVAILABLE_STARTING(__MAC_NA,__IPHONE_3_0);

		glShaderSource(frShader,(GLsizei)1,(const GLchar**)&text,&length);
        
		glCompileShader(frShader);
		
		/*GLuint status;
		
		glGetObjectParameteriv(frShader,GL_OBJECT_COMPILE_STATUS_,&status);
        
		if(status[0]==0){
			int len[]=new int[1];
			glGetObjectParameteriv(frShader, GL_OBJECT_COMPILE_STATUS_,
                                         len,0);
            
			byte[] b=new byte[3000];
			glGetInfoLog(frShader,b.length,status,0,b,0);
            
			if(REPORT_SHADERS)
				System.out.println(new string(b));
		}else{
			//System.out.println("Shader is ok ");
		}*/
	}
	
	void initVertexShader(){
		
		vxShader=glCreateShader(GL_VERTEX_SHADER);
		
		if(vxShader==0){            
            //NSLog("Failed to create Vertex Shader ");
		}
        
		updateVertexShader();
	}
    
	void updateVertexShader() {
        
        string shaderText=loadVertexShaderText();
		
		//int length[]={shaderText[0].length()};
		
		//if(REPORT_SHADERS)
		//	System.out.println("shaderText[0] " + shaderText[0]);
		
        /*void glShaderSource(	GLuint  	shader,
         GLsizei  	count,
         const GLchar ** 	string,
         const GLint * 	length);*/
        
        GLchar* text=(char*)shaderText.c_str();
        GLint length=1;
        
        //glShaderSource (GLuint shader, GLsizei count, const GLchar** string,
        //                const GLint* length)  __OSX_AVAILABLE_STARTING(__MAC_NA,__IPHONE_3_0);
        
		glShaderSource(vxShader,(GLsizei)1,(const GLchar**)&text,&length);
        
		glCompileShader(vxShader);
        
		/*string shaderText[]={loadVertexShaderText()};
        
		int length[]={shaderText[0].length()};
        
		if(REPORT_SHADERS)
			System.out.println("shaderText[0] " + shaderText[0]);
		
		glShaderSource(vxShader,1,shaderText,length,0);
		
		glCompileShader(vxShader);
		
		int status[]=new int[1];
		
		glGetObjectParameteriv(vxShader,GL_OBJECT_COMPILE_STATUS_,status,0);
        
		if(status[0]==0){
			if(REPORT_SHADERS)
				System.out.println("Shader has a problems");
			int len[]=new int[1];
			glGetObjectParameteriv(vxShader, GL_OBJECT_COMPILE_STATUS_,
                                         len,0);
            
			byte[] b=new byte[20000];
			glGetInfoLog(vxShader,b.length,status,0,b,0);
            
			if(REPORT_SHADERS)
				System.out.println(new string(b));
		}else{
			//System.out.println("Shader is ok ");
		}*/
	}
	
	void initProgram(){
		
		program=glCreateProgram();
		
		if(program==0){
            //NSLog("Failed Create Program");
		}
		
		updateProgram();
	}
    
	void updateProgram() {
        
        
		glAttachShader(program, vxShader);
		glAttachShader(program, frShader);
		
		glLinkProgram(program);
        
        /*
		int[] status2=new int[1];
		glGetObjectParameteriv(program, GL_OBJECT_LINK_STATUS_, status2,0);
		
		//System.out.println("Status "+status2[0]);
		if(status2[0]==0){
			if(REPORT_SHADERS)
				System.out.println("Something failed "+status2[0]);
			int len[]=new int[1];
			glGetObjectParameteriv(program, GL_OBJECT_COMPILE_STATUS_,
                                         len,0);
            
			byte[] b=new byte[2000];
			glGetInfoLog(program,b.length,status,0,b,0);
            
			if(REPORT_SHADERS)
				System.err.println("new string(b) " + new string(b));
		}
        */
		glValidateProgram(program);
		glUseProgram(program);
	}
	
	
	void apply(){
		glUseProgram(program);
	}
	void unapply(){
		glUseProgram(0);
	}
	
	
	void init() {
		if(!initialized){
			initialized=true;
			initVertexShader();
			initFragmentShader();
			initProgram();
		}
	}
    
	
	
	void destroy() {
        
		glDeleteProgram(this->program);
		glDeleteShader(this->frShader);
		glDeleteShader(this->vxShader);
		
	}
	
	
	void update() {
		updateVertexShader();
		updateFragmentShader();
		updateProgram();	
	}
	
};
}


#endif /* defined(SFGLSLSet__) */
