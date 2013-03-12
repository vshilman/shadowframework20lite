package deferredShading;

import shadow.image.SFPipelineTexture;
import shadow.math.SFVertex3f;
import shadow.pipeline.openGL20.SFGL20Pipeline;
import shadow.utils.SFTutorial;
import deferredShading.DSAlgorithm;

public class DeferredShadingExample {
private static String obj = "models/vagone.obj";
	
	private static SFVertex3f diffColor = new SFVertex3f(1,1,0);
	private static SFVertex3f ambColor = new SFVertex3f(1,1,0);
	private static SFVertex3f specColor= new SFVertex3f(1,1,0);
	
	private static SFVertex3f intensity = new SFVertex3f(1, 1, 1);
	private static SFVertex3f lPosition = new SFVertex3f(1, -1, -1);
	
	public static void main(String[] args) {

		SFGL20Pipeline.setup();
		DeferredShading example=new DeferredShading(obj,diffColor,ambColor,specColor,intensity,lPosition);
		example.prepareFrame("Deferred Shading", 600, 600);
		
	}
	
	
}
