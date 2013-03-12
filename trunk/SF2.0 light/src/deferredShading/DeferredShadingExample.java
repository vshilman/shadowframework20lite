package deferredShading;

import shadow.math.SFVertex3f;
import shadow.pipeline.openGL20.SFGL20Pipeline;

public class DeferredShadingExample {

	private static String obj = "models/vagone.obj";
	
	private static SFVertex3f[] color = DeferredShading.setupColor(1,0,0,1,0,0,1,1,0);
	private static SFVertex3f[] light = DeferredShading.setupLight(1,1,1,1,1,-1);
	
	public static void main(String[] args) {

		SFGL20Pipeline.setup();
		DeferredShading example=new DeferredShading(obj,color,light);
		example.prepareFrame("Deferred Shading", 600, 600);
		
	}
	
	
}
