package shadow.pipeline.openGL20.tutorials.geometriesExample;

import shadow.geometry.editing.SFConcreteTriangleExtractor;
import shadow.geometry.functions.SFCurvedTubeFunction;
import shadow.geometry.geometries.SFQuadsSurfaceGeometry;
import shadow.pipeline.SFPrimitive;
import shadow.system.data.objects.SFVertex3fData;

public class StrangeGlass {
	
	public SFQuadsSurfaceGeometry generateGeometry(SFPrimitive primitive) {
		
		SFCurvedTubeFunction function=new SFCurvedTubeFunction();
		
		function.getData().getCc().add(new SFVertex3fData(0,0,0));
		function.getData().getCc().add(new SFVertex3fData(0.0f,0.1f,0));
		function.getData().getCc().add(new SFVertex3fData(0.025f,0.2f,0));
		function.getData().getCc().add(new SFVertex3fData(0.0f,0.3f,0));
		function.getData().getCc().add(new SFVertex3fData(0.0f,0.4f,0));
		function.getData().getCc().add(new SFVertex3fData(0.0f,0.5f,0));
		function.getData().getCc().add(new SFVertex3fData(0.0f,0.6f,0));
		function.getData().getCc().add(new SFVertex3fData(0.0f,0.7f,0));
		function.getData().getCc().add(new SFVertex3fData(0.0f,0.8f,0));
		//function.Cc.add(new SFVertex3f(0.0f,0.35f,0));
		//function.Rc.add(new SFVertex3f(0.0f,0.40f,0));
		
		function.getData().getRc().add(new SFVertex3fData(0.1f,0,0));
		function.getData().getRc().add(new SFVertex3fData(0.2f,0.1f,0));
		function.getData().getRc().add(new SFVertex3fData(0.2f,0.2f,0));
		function.getData().getRc().add(new SFVertex3fData(0.05f,0.4f,0));
		function.getData().getRc().add(new SFVertex3fData(0.05f,0.4f,0));
		function.getData().getRc().add(new SFVertex3fData(0.2f,0.5f,0));
		function.getData().getRc().add(new SFVertex3fData(0.2f,0.6f,0));
		function.getData().getRc().add(new SFVertex3fData(0.50f,0.7f,0));
		function.getData().getRc().add(new SFVertex3fData(0.40f,0.80f,0));
		
		SFConcreteTriangleExtractor trianglesExtractor=new SFConcreteTriangleExtractor();
		SFQuadsSurfaceGeometry quadsSurfaceGeometry=new SFQuadsSurfaceGeometry(primitive,
				function, new TexCoordFunction(), trianglesExtractor, 12, 14);
		
		quadsSurfaceGeometry.compile();
		return quadsSurfaceGeometry;
	}
}
