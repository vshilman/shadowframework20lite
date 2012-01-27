

SFGL20RenderedTexture.prototype = {

	initShadowTexture:function(data){
	GL2 gl;
	SFBufferData depthData;
	if(depthData!=null)			((SFGL20ImageObject)depthData).build();//Warning: Not well Identified 
	SFBufferData stencilData;
	if(stencilData!=null)			((SFGL20ImageObject)stencilData).build();//Warning: Not well Identified 
	//Step 5: generate frame buffer. Bind to buffer		int nfbo[]=new int[1];//Warning: Not well Identified 
	gl.glGenFramebuffers(1, nfbo,0);//Warning: Not well Identified 
	fbo=nfbo[0];//Warning: Not well Identified 
	gl.glBindFramebuffer(GL.GL_FRAMEBUFFER,fbo);//Warning: Not well Identified 
	//Step 6: Viewport storing		float vp[]=new float[4];//Warning: Not well Identified 
	gl.glGetFloatv(GL.GL_VIEWPORT,vp,0);//Warning: Not well Identified 
	//Step 7: New Viewport for texture generation				gl.glViewport(0, 0, data.getColorsData().get(0).getWidth(),data.getColorsData().get(0).getHeight());//Warning: Not well Identified 
	int buffers[] = new int[data.getColorsData().size()];//Warning: Not well Identified 
	gl.glDrawBuffers(data.getColorsData().size(), buffers,0);//Warning: Not well Identified 
	int index=0;//Warning: Not well Identified 
	//		gl.glFramebufferTexture2D(GL.GL_FRAMEBUFFER,//				GL.GL_COLOR_ATTACHMENT0,//				GL2.GL_TEXTURE_2D,texture_object,0);//Warning: Not well Identified 
	//Step 8: texture bindings (txo and txo4) 	//		gl.glActiveTexture(GL.GL_TEXTURE0);//Warning: Not well Identified 
	//		gl.glBindTexture(GL.GL_TEXTURE_2D, depthBuffer);//Warning: Not well Identified 
	//		gl.glTexParameteri(GL.GL_TEXTURE_2D, GL2.GL_TEXTURE_COMPARE_MODE, GL2.GL_COMPARE_R_TO_TEXTURE);//Warning: Not well Identified 
	//		gl.glTexParameteri(GL.GL_TEXTURE_2D, GL2.GL_GENERATE_MIPMAP, GL.GL_TRUE);//Warning: Not well Identified 
	//		gl.glTexImage2D(GL.GL_TEXTURE_2D, 0, GL2.GL_DEPTH_COMPONENT,data.getWidth(), data.getHeight(), 0,//	             GL2.GL_DEPTH_COMPONENT,GL.GL_FLOAT, null);//Warning: Not well Identified 
	//		//		gl.glFramebufferTexture2D(GL.GL_FRAMEBUFFER,//				GL.GL_DEPTH_ATTACHMENT,//				GL.GL_TEXTURE_2D,depthBuffer,0);//Warning: Not well Identified 
	gl.glEnable(GL2.GL_DEPTH_TEST);//Warning: Not well Identified 
	gl.glDisable(GL.GL_TEXTURE_2D);//Warning: Not well Identified 
	gl.glClearColor(1,1,1,0);//Warning: Not well Identified 
	gl.glClearDepth(2);//Warning: Not well Identified 
	gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);//Warning: Not well Identified 
	gl.glEnable(GL.GL_DEPTH_TEST);//Warning: Not well Identified 
	},

	closeShadowTexture:function(){
	GL2 gl;
	gl.glDisable(GL.GL_DEPTH_TEST);//Warning: Not well Identified 
	gl.glBindFramebuffer(GL2.GL_FRAMEBUFFER,0);//Warning: Not well Identified 
	//		/*//		 * FIXME : actually i can't use mipmapping on texture, must be solved, which is too important.//		 *///		gl.glActiveTexture(GL.GL_TEXTURE0);//Warning: Not well Identified 
	//		gl.glBindTexture(GL.GL_TEXTURE_2D, texture_object);//Warning: Not well Identified 
	//		gl.glGenerateMipmap(GL.GL_TEXTURE_2D);//Warning: Not well Identified 
	//restoring of the viewport		gl.glViewport((int)vp[0], (int)vp[1], (int)vp[2], (int)vp[3]);//Warning: Not well Identified 
	},

	getFbo:function(){
		return this.fbo;
	}

};