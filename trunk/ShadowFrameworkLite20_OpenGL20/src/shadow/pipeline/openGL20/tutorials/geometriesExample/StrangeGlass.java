package shadow.pipeline.openGL20.tutorials.geometriesExample;

import shadow.geometry.curves.SFBasisSpline2;
import shadow.geometry.functions.SFCurvedTubeFunction;
import shadow.geometry.geometries.SFQuadsSurfaceGeometry;
import shadow.geometry.geometries.SFSimpleTexCoordGeometryuv;
import shadow.math.SFValuenf;
import shadow.math.SFVertex3f;
import shadow.pipeline.SFPrimitive;

public class StrangeGlass {
	
	public SFQuadsSurfaceGeometry generateGeometry(SFPrimitive primitive) {
		
		SFCurvedTubeFunction function=new SFCurvedTubeFunction();

		SFBasisSpline2<SFValuenf> centralCurve=new SFBasisSpline2<SFValuenf>(false);
		SFBasisSpline2<SFValuenf> rayCurve=new SFBasisSpline2<SFValuenf>(false);
		
		centralCurve.add(new SFVertex3f(0,0,0));
		centralCurve.add(new SFVertex3f(0.0f,0.1f,0));
		centralCurve.add(new SFVertex3f(0.025f,0.2f,0));
		centralCurve.add(new SFVertex3f(0.0f,0.3f,0));
		centralCurve.add(new SFVertex3f(0.0f,0.4f,0));
		centralCurve.add(new SFVertex3f(0.0f,0.5f,0));
		centralCurve.add(new SFVertex3f(0.0f,0.6f,0));
		centralCurve.add(new SFVertex3f(0.0f,0.7f,0));
		centralCurve.add(new SFVertex3f(0.0f,0.8f,0));
		//function.Cc.add(new SFVertex3f(0.0f,0.35f,0));
		//function.Rc.add(new SFVertex3f(0.0f,0.40f,0));
		
		rayCurve.add(new SFVertex3f(0.1f,0,0));
		rayCurve.add(new SFVertex3f(0.2f,0.1f,0));
		rayCurve.add(new SFVertex3f(0.2f,0.2f,0));
		rayCurve.add(new SFVertex3f(0.05f,0.4f,0));
		rayCurve.add(new SFVertex3f(0.05f,0.4f,0));
		rayCurve.add(new SFVertex3f(0.2f,0.5f,0));
		rayCurve.add(new SFVertex3f(0.2f,0.6f,0));
		rayCurve.add(new SFVertex3f(0.50f,0.7f,0));
		rayCurve.add(new SFVertex3f(0.40f,0.80f,0));
		
		function.setCentralCurve(centralCurve);
		function.setRayCurve(rayCurve);
		
		SFQuadsSurfaceGeometry quadsSurfaceGeometry=new SFQuadsSurfaceGeometry(primitive,
				function, new SFSimpleTexCoordGeometryuv(1,1), 12, 14);
		
		quadsSurfaceGeometry.compile();
		return quadsSurfaceGeometry;
	}
}
