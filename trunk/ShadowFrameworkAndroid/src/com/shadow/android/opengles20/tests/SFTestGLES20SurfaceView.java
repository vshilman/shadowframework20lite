package com.shadow.android.opengles20.tests;

import android.content.Context;
import android.opengl.GLSurfaceView;

public class SFTestGLES20SurfaceView  extends GLSurfaceView {

    public SFTestGLES20SurfaceView(Context context,Renderer renderer){
        super(context);
        
        // Create an OpenGL ES 2.0 context.
        setEGLContextClientVersion(2);
        // Set the Renderer for drawing on the GLSurfaceView
        setRenderer(renderer);
    }
}
