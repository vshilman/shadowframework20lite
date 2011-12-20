package shadow.pipeline.openGL20.tutorials.geometriesExample;

import shadow.geometry.editing.SFConcreteTriangleExtractor;
import shadow.geometry.functions.SFCurvedTubeFunction;
import shadow.geometry.geometries.SFQuadsSurfaceGeometry;
import shadow.math.SFVertex3f;
import shadow.pipeline.SFPrimitive;

public class LateralTube {
	
	public SFQuadsSurfaceGeometry generateGeometry(SFPrimitive primitive) {
		
		SFCurvedTubeFunction function=new SFCurvedTubeFunction();

		//Primo Pezzo
		function.Cc.add(new SFVertex3f(0,0,0));
		function.Cc.add(new SFVertex3f(0.0f,0.1f,0));
		function.Cc.add(new SFVertex3f(0.0f,0.2f,0));
		function.Cc.add(new SFVertex3f(0.0f,0.3f,0));
		function.Cc.add(new SFVertex3f(0.0f,0.4f,0));

		function.Rc.add(new SFVertex3f(0.1f,0,0));
		function.Rc.add(new SFVertex3f(0.2f,0.1f,0));
		function.Rc.add(new SFVertex3f(0.1f,0.2f,0));
		function.Rc.add(new SFVertex3f(0.1f,0.3f,0));
		function.Rc.add(new SFVertex3f(0.1f,0.4f,0));

		//Secondo Pezzo
		function.Cc.add(new SFVertex3f(0.0f,0.5f,0));
		function.Cc.add(new SFVertex3f(0.1f,0.5f,0));
		function.Cc.add(new SFVertex3f(0.2f,0.5f,0));

		function.Rc.add(new SFVertex3f(0.1f,0.4f,0));
		function.Rc.add(new SFVertex3f(0.1f,0.4f,0));
		function.Rc.add(new SFVertex3f(0.2f,0.4f,0));
		
		//Terzo Pezzo
		function.Cc.add(new SFVertex3f(0.3f,0.5f,0));
		function.Rc.add(new SFVertex3f(0.2f,0.4f,0));
		
		SFConcreteTriangleExtractor trianglesExtractor=new SFConcreteTriangleExtractor();
		SFQuadsSurfaceGeometry quadsSurfaceGeometry=new SFQuadsSurfaceGeometry(primitive,
				function, new TexCoordFunction(), trianglesExtractor, 6, 8);
		
		quadsSurfaceGeometry.compile();
		return quadsSurfaceGeometry;
	}
}
