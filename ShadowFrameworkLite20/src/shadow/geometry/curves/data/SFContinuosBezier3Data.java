package shadow.geometry.curves.data;

import shadow.geometry.SFValuesList;
import shadow.geometry.curves.SFBezier3;
import shadow.geometry.curves.SFControlPointsCurve;
import shadow.geometry.curves.SFUniformCurvesSpline;
import shadow.math.SFValuenf;
import shadow.math.SFVertex3f;

public class SFContinuosBezier3Data extends SFCurvesVerticesData{

	protected SFControlPointsCurve buildResource() {
		
		SFUniformCurvesSpline<SFValuenf,SFBezier3<SFValuenf>>  spline
				=new SFUniformCurvesSpline<SFValuenf, SFBezier3<SFValuenf>>();
		
		SFValuesList<SFValuenf> vertices=this.vertices.getDataset().getResource();
		
		for (int i = 0; i < vertices.getSize(); i++) {
			SFVertex3f point=new SFVertex3f();
			vertices.getValue(i, point);
			spline.addControlPoint(point);
		}
		
		for (int i = 0; i < vertices.getSize()-3; i+=3) {

			SFValuenf A=spline.getControlPoint(i);
			SFValuenf B=spline.getControlPoint(i+1);
			SFValuenf C=spline.getControlPoint(i+2);
			SFValuenf D=spline.getControlPoint(i+3);

			SFBezier3<SFValuenf> values=new SFBezier3<SFValuenf>(A, B, C, D);
			spline.addCurve(values);
		}
		
		return spline;
	}
}
